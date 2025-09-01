package nl.q42.sdui.screen

import kotlinx.serialization.Serializable
import nl.q42.common.ServerComponent
import nl.q42.common.TextComponent
import nl.q42.core.AppRequestContext

@org.springframework.stereotype.Component
@Serializable
class ProfileScreen : ScreenInstance {

    override fun content(context: AppRequestContext): List<ServerComponent> {
        return listOf(
            TextComponent("test", contentId = "text-comp")
        )
    }

    override fun name(): String {
        return SCREEN_IDENTIFIER
    }

    companion object {
        const val SCREEN_IDENTIFIER: String = "profile"
    }
}
