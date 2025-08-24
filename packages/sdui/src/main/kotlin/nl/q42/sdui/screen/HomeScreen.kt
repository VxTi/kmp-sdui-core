package nl.q42.sdui.screen

import nl.q42.common.components.SpacerComponent
import nl.q42.common.components.TextComponent
import nl.q42.common.screen.Screen

@org.springframework.stereotype.Component
class HomeScreen : Screen {

    constructor()
            : super(SCREEN_NAME,
        TextComponent(text = "Hello world!", contentId = "text-1"),
        SpacerComponent(size = 2, contentId = "spacer-1")
    )

    companion object {
        const val SCREEN_NAME: String = "home"
    }
}
