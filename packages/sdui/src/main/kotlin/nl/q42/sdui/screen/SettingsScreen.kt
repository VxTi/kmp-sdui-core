package nl.q42.sdui.screen

import nl.q42.common.screen.Screen

@org.springframework.stereotype.Component
class SettingsScreen : Screen {

    constructor() : super(
        SCREEN_NAME
    )

    companion object {
        const val SCREEN_NAME: kotlin.String = "settings"
    }
}
