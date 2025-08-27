package nl.q42.sdui.screen

import nl.q42.common.ButtonComponent
import nl.q42.common.ButtonVariant
import nl.q42.common.ImageComponent
import nl.q42.common.NavigationEvent
import nl.q42.common.SearchBarComponent
import nl.q42.common.SpacerComponent
import nl.q42.common.TextComponent
import nl.q42.common.screen.Screen
import nl.q42.core.RequestContext

@org.springframework.stereotype.Component
class HomeScreen : ScreenInstance {

    override fun create(context: RequestContext): Screen {
        return Screen(
            name(),
            SpacerComponent(1, "0"),
            SearchBarComponent(placeholder = "Search...", contentId = "search-2"),
            SpacerComponent(size = 2, contentId = "spacer-1"),
            TextComponent(text = "Hello world!", contentId = "text-1"),
            SpacerComponent(size = 10, contentId = "spacer-2"),
            ButtonComponent(
                "Hello world from SDUI",
                ButtonVariant.NORMAL,
                listOf(NavigationEvent("/screen?id=home")),
                "test"
            ),
            ImageComponent(
                url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMCmWeNm9_PMjP0-FDjhC-WFnm8xL7CzcmAg&s",
                alt ="test",
                contentId = "image"
            ),
        )
    }

    override fun name(): String {
        return SCREEN_IDENTIFIER
    }

    companion object {
        const val SCREEN_IDENTIFIER: String = "home"
    }
}
