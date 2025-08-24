package nl.q42.sdui

import com.fasterxml.jackson.annotation.JsonInclude
import kotlinx.serialization.Serializable
import nl.q42.common.MetadataType
import nl.q42.common.actions.Action
import nl.q42.common.analytics.AnalyticEvent
import nl.q42.common.screen.Screen
import nl.q42.common.screen.ScreenTab

@JsonInclude(JsonInclude.Include.NON_NULL)
@Serializable
class SDUIApplication(
    val screen: Screen,
    var onLoadActions: MutableList<Action?>? = null,
    var onLoadAnalyticEvents: MutableList<AnalyticEvent?>? = null,
    var tabs: List<ScreenTab>? = null,
    var metadata: MutableMap<MetadataType, Any>? = null,
) {
    companion object {
        const val MINIMUM_APP_VERSION: Int = 1
        const val MAXIMUM_APP_VERSION: Int = 1
    }
}
