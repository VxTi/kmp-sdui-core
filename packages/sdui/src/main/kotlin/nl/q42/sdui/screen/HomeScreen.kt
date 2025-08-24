package nl.q42.sdui.screen

import nl.q42.common.components.SearchBar
import nl.q42.common.components.SpacerComponent
import nl.q42.common.components.TextComponent
import nl.q42.common.screen.Screen
import nl.q42.core.RequestContext

@org.springframework.stereotype.Component
class HomeScreen : ScreenInstance {

    override fun create(context: RequestContext): Screen {
        return Screen(
            name(),
            SpacerComponent(size = 10, contentId = "spacer-1"),
            TextComponent(text = "Hello world!", contentId = "text-1"),
            SpacerComponent(size = 10, contentId = "spacer-2"),
            SearchBar(placeholder = "Search...", contentId = "search-bar-1"),
            SearchBar(placeholder = "Search...", contentId = "search-2")
        )
    }

    override fun name(): String {
        return SCREEN_NAME
    }

    companion object {
        const val SCREEN_NAME: String = "home"
    }
}
