package nl.q42.common

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import nl.q42.common.analytics.AnalyticEvent
import nl.q42.common.screen.Screen
import nl.q42.common.screen.ScreenTab

@Serializable
data class ScreenResponse(
    val screen: Screen,
    var onLoadEvents: MutableList<Event?>? = null,
    var onLoadAnalyticEvents: MutableList<AnalyticEvent?>? = null,
    var tabs: List<ScreenTab?>? = null,
    var metadata: MutableMap<MetadataType, @Contextual Any>? = null
)
