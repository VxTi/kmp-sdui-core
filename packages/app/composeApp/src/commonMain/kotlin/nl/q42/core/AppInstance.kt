package nl.q42.core

import kmp_sdui_core.app.composeapp.generated.resources.Res
import kmp_sdui_core.app.composeapp.generated.resources.app_locale
import kmp_sdui_core.app.composeapp.generated.resources.app_version
import kotlinx.coroutines.runBlocking
import nl.q42.common.Event
import nl.q42.common.NavigationEvent
import nl.q42.common.core.AppIdentity
import nl.q42.common.core.Locale
import org.jetbrains.compose.resources.getString

class AppInstance(val version: Int, val locale: Locale) {
    val identity: String = AppIdentity.calculate(locale, version);

    companion object {
        fun fromConfig() = runBlocking {
            AppInstance(
                version = getString(Res.string.app_version).toIntOrNull() ?: 1,
                locale = Locale.from(getString(Res.string.app_locale)),
            )
        }
    }
}
