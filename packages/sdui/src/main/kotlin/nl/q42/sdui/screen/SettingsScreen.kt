package nl.q42.sdui.screen

import nl.q42.common.ServerComponent
import nl.q42.common.TextComponent
import nl.q42.core.AppRequestContext

@org.springframework.stereotype.Component
class SettingsScreen : ScreenInstance {

    override fun content(context: AppRequestContext): List<ServerComponent> {
        return listOf(
            TextComponent(text = "Settings", contentId = "settings-text-1")
        )
    }

    override fun name(): String {
        return SCREEN_IDENTIFIER
    }

    companion object {
        const val SCREEN_IDENTIFIER: String = "settings"
    }
}
