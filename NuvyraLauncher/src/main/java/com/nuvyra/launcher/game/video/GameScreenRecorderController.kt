/*
 * Nuvyra Launcher 2
 * Controller for the optional in-game screen recorder.
 */
package com.nuvyra.launcher.game.video

import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.io.File

object GameScreenRecorderController {
    private var recorder: GameScreenRecorder? = null
    private var source: View? = null
    var isRecording by mutableStateOf(false)
        private set
    var lastVideo by mutableStateOf<File?>(null)
        private set

    fun attach(view: View) {
        source = view
    }

    fun start(): Boolean {
        val view = source ?: return false
        if (isRecording) return true
        val next = GameScreenRecorder(view)
        val file = next.start() ?: return false
        recorder = next
        lastVideo = file
        isRecording = true
        return true
    }

    fun stop() {
        val saved = recorder?.stop()
        if (saved != null) lastVideo = saved
        recorder = null
        isRecording = false
    }

    fun clear() {
        stop()
        source = null
    }
}
