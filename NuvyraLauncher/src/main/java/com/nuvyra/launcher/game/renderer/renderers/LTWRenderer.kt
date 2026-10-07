/*
 * Nuvyra Launcher 2
 * OpenLTW renderer, built from the official MojoLauncher/LTW project.
 */
package com.nuvyra.launcher.game.renderer.renderers

import com.nuvyra.launcher.game.renderer.RendererInterface

/**
 * Large Thin Wrapper: a desktop OpenGL core to OpenGL ES 3 wrapper.
 * It is intended for Minecraft 1.17 and newer on devices that can create
 * a real OpenGL ES 3 context.
 */
object LTWRenderer : RendererInterface {
    override fun getRendererId(): String = "opengles3_ltw"

    override fun getUniqueIdentifier(): String = "f27d2a1a-7d5d-4f4e-9e59-7f67a3c4b8f1"

    override fun getRendererName(): String = "LTW (OpenGL ES 3)"

    override fun getRendererSummary(): String = "Fast OpenGL ES 3 renderer for Minecraft 1.17+"

    override fun getMinMCVersion(): String = "1.17"

    override fun getMaxMCVersion(): String = "26.3-snapshot-3"

    override fun getRendererEnv(): Lazy<Map<String, String>> = lazy {
        mapOf(
            "LIBGL_ES" to "3",
            "LIBGL_NOERROR" to "1",
            "force_glsl_extensions_warn" to "true",
            "allow_higher_compat_version" to "true",
            "allow_glsl_extension_directive_midshader" to "true"
        )
    }

    override fun getDlopenLibrary(): Lazy<List<String>> = lazy { emptyList() }

    override fun getRendererLibrary(): String = "libltw.so"
}
