package nl.q42.core

import nl.q42.common.Action
import nl.q42.common.ActionTypes
import nl.q42.common.NavigationAction
import nl.q42.common.core.AppIdentity
import nl.q42.common.core.Locale

class AppInstance(
    val version: Int = 1,
    val locale: Locale = Locale.NL_NL,

    ) {
    val identity: String = AppIdentity.calculate(locale, version);

    fun invokeActions(events: List<Action>) {
        events.forEach { when (it.entityType) {
            ActionTypes.NAVIGATION -> {
                println("Navigation action invoked to path: ${(it as NavigationAction).path}");
            }
        } }
    }
}
