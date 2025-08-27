package nl.q42.core

import nl.q42.common.Event
import nl.q42.common.NavigationEvent
import nl.q42.common.core.AppIdentity
import nl.q42.common.core.Locale

class AppInstance(
    val version: Int = 1,
    val locale: Locale = Locale.NL_NL,
    ) {
    val identity: String = AppIdentity.calculate(locale, version);

    fun emitEvents(events: List<Event>) {
        events.forEach { emitEvent(it) }
    }

    fun emitEvent(event: Event) {
        when (event) {
            is NavigationEvent ->
                println("Navigation action invoked to path: ${event.path}");
        }
    }
}

