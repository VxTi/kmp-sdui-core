package nl.q42.sdui.screen

import nl.q42.common.screen.Screen
import nl.q42.core.RequestContext

interface ScreenInstance {
    fun create(context: RequestContext): Screen

    fun name(): String
}