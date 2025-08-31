package nl.q42.sdui.screen

import kotlinx.serialization.Serializable
import nl.q42.common.Component
import nl.q42.common.TextComponent
import nl.q42.common.screen.Screen
import nl.q42.core.AppRequestContext

@org.springframework.stereotype.Component
@Serializable
class SearchScreen : ScreenInstance {

    override fun content(context: AppRequestContext): List<Component> {
        return listOf(
            TextComponent("Search screen", contentId = "Test")
        )
    }

    override fun name(): String {
        return SCREEN_IDENTIFIER
    }

    companion object {
        const val SCREEN_IDENTIFIER: String = "search"
    }
}
