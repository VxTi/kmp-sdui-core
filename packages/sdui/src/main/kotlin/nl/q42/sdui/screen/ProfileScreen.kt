package nl.q42.sdui.screen

import kotlinx.serialization.Serializable
import nl.q42.common.screen.Screen
import nl.q42.core.RequestContext

@org.springframework.stereotype.Component
@Serializable
class ProfileScreen : ScreenInstance {

    override fun create(context: RequestContext): Screen {
        return Screen(SCREEN_IDENTIFIER)
    }

    override fun name(): String {
        return SCREEN_IDENTIFIER
    }

    companion object {
        const val SCREEN_IDENTIFIER: String = "profile"
    }
}
