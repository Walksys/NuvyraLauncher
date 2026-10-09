/*
 * Nuvyra Launcher 2
 * Game-only screen recorder.
 */
package com.nuvyra.launcher.game.video

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.media.MediaCodec
import android.media.MediaCodecInfo
import android.media.MediaFormat
import android.media.MediaMuxer
import android.os.Handler
import android.os.HandlerThread
import android.view.Surface
import android.view.SurfaceView
import android.view.TextureView
import android.view.View
import android.view.PixelCopy
import com.nuvyra.launcher.path.PathManager
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.atomic.AtomicBoolean

class GameScreenRecorder(private val source: View) {
    private val running = AtomicBoolean(false)
    private val worker = HandlerThread("nuvyra-video-recorder").apply { start() }
    private val handler = Handler(worker.looper)
    private var codec: MediaCodec? = null
    private var inputSurface: Surface? = null
    private var muxer: MediaMuxer? = null
    private var track = -1
    private var output: File? = null
    private var bitmap: Bitmap? = null
    private var width = 0
    private var height = 0

    fun start(): File? {
        if (!running.compareAndSet(false, true)) return output
        track = -1
        val sourceWidth = source.width.coerceAtLeast(2)
        val sourceHeight = source.height.coerceAtLeast(2)
        val scale = minOf(1f, 1280f / sourceWidth, 720f / sourceHeight)
        width = ((sourceWidth * scale).toInt() / 2) * 2
        height = ((sourceHeight * scale).toInt() / 2) * 2
        val directory = PathManager.DIR_SCREEN_VIDEOS.apply { mkdirs() }
        output = File(directory, "Nuvyra-${SimpleDateFormat("yyyyMMdd-HHmmss", Locale.US).format(Date())}.mp4")
        return try {
            val format = MediaFormat.createVideoFormat("video/avc", width, height).apply {
                setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface)
                setInteger(MediaFormat.KEY_BIT_RATE, 2_000_000)
                setInteger(MediaFormat.KEY_FRAME_RATE, 24)
                setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, 1)
            }
            codec = MediaCodec.createEncoderByType("video/avc").also {
                it.configure(format, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE)
                inputSurface = it.createInputSurface()
                it.start()
            }
            muxer = MediaMuxer(output!!.absolutePath, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4)
            bitmap = Bitmap.createBitmap(source.width.coerceAtLeast(2), source.height.coerceAtLeast(2), Bitmap.Config.ARGB_8888)
            handler.post { captureFrame() }
            output
        } catch (_: Throwable) {
            stop()
            null
        }
    }

    fun stop(): File? {
        if (!running.getAndSet(false)) return output
        handler.post {
            try {
                codec?.signalEndOfInputStream()
                drain(true)
                codec?.stop()
                codec?.release()
                muxer?.stop()
                muxer?.release()
            } catch (_: Throwable) {
                output?.delete()
            } finally {
                codec = null
                track = -1
                muxer = null
                inputSurface?.release()
                inputSurface = null
                bitmap?.recycle()
                bitmap = null
            }
        }
        return output
    }

    fun isRecording(): Boolean = running.get()

    private fun captureFrame() {
        if (!running.get()) return
        val target = bitmap ?: return
        if (source is TextureView) {
            source.getBitmap(target)
            drawBitmap(target)
            handler.postDelayed({ captureFrame() }, 42L)
        } else if (source is SurfaceView) {
            PixelCopy.request(source, target, { result ->
                if (result == PixelCopy.SUCCESS && running.get()) drawBitmap(target)
                if (running.get()) handler.postDelayed({ captureFrame() }, 42L)
            }, handler)
        } else {
            source.draw(Canvas(target))
            drawBitmap(target)
            handler.postDelayed({ captureFrame() }, 42L)
        }
    }

    private fun drawBitmap(frame: Bitmap) {
        val surface = inputSurface ?: return
        try {
            val canvas = surface.lockCanvas(null)
            canvas.drawBitmap(frame, null, Rect(0, 0, width, height), null)
            surface.unlockCanvasAndPost(canvas)
            drain(false)
        } catch (_: Throwable) {
            running.set(false)
        }
    }

    private fun drain(end: Boolean) {
        val encoder = codec ?: return
        val info = MediaCodec.BufferInfo()
        while (true) {
            val index = encoder.dequeueOutputBuffer(info, 0)
            when {
                index == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED -> {
                    if (track >= 0) continue
                    track = muxer!!.addTrack(encoder.outputFormat)
                    muxer!!.start()
                }
                index == MediaCodec.INFO_TRY_AGAIN_LATER -> if (!end) return else break
                index >= 0 -> {
                    val data = encoder.getOutputBuffer(index) ?: continue
                    if (info.size > 0 && track >= 0) {
                        data.position(info.offset)
                        data.limit(info.offset + info.size)
                        muxer!!.writeSampleData(track, data, info)
                    }
                    encoder.releaseOutputBuffer(index, false)
                    if ((info.flags and MediaCodec.BUFFER_FLAG_END_OF_STREAM) != 0) break
                }
            }
        }
    }
}
