package nl.q42.sdui.screen

import nl.q42.common.Component
import nl.q42.common.screen.Screen
import nl.q42.core.AppRequestContext

interface ScreenInstance {

    fun name(): String

    fun content(context: AppRequestContext): List<Component>;

    fun createScreen(context: AppRequestContext): Screen {
        val filteredContent =this.content(context).filter { component ->
            component.isAvailable(context.appVersion)
        }.toMutableList()

        return Screen(
            this.name(),
            filteredContent
        );
    }
}

