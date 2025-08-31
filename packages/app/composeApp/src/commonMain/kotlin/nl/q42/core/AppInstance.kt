package nl.q42.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.input.key.Key.Companion.R
import kmp_sdui_core.app.composeapp.generated.resources.Res
import kmp_sdui_core.app.composeapp.generated.resources.app_locale
import kmp_sdui_core.app.composeapp.generated.resources.app_version
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import nl.q42.common.Event
import nl.q42.common.NavigationEvent
import nl.q42.common.core.AppIdentity
import nl.q42.common.core.Locale
import org.jetbrains.compose.resources.getString

class AppInstance(val version: Int, val locale: Locale) {
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

    companion object {
        fun fromConfig() = runBlocking {
            AppInstance(
                version = getString(Res.string.app_version).toIntOrNull() ?: 1,
                locale = Locale.from(getString(Res.string.app_locale)),
            )
        }
    }
}
