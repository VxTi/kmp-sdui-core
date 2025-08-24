package nl.q42.sdui.screen

import nl.q42.common.screen.Screen
import nl.q42.common.components.Component

@org.springframework.stereotype.Component
class ProfileScreen : Screen {


    constructor(vararg content: Component) : super(SCREEN_NAME, *content)

    companion object {
        const val SCREEN_NAME: String = "profile"
    }
}
