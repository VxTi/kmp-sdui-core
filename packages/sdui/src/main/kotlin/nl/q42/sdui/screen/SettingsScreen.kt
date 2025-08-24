package nl.q42.sdui.screen

import nl.q42.common.screen.Screen
import nl.q42.core.RequestContext

@org.springframework.stereotype.Component
class SettingsScreen : ScreenInstance {

    override fun create(context: RequestContext): Screen {
        return Screen(name())
    }

    override fun name(): String {
        return SCREEN_NAME
    }

    companion object {
        const val SCREEN_NAME: kotlin.String = "settings"
    }
}
