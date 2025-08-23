package nl.q42.common.screen

import nl.q42.common.components.Component
import nl.q42.common.components.ComponentTypes
import java.io.Serializable
import java.util.List
import java.util.Optional

abstract class Screen(val name: String?, val content: MutableList<Component?>) : Serializable {
    val type: String = ComponentTypes.SCREEN

    constructor(screenName: String?, vararg content: Component) : this(
        screenName,
        List.of<Component?>(*content)
    )

    fun getComponentById(contentId: String?): Optional<Component?> {
        return content.stream()
            .filter { component: Component? -> component!!.contentId == contentId }
            .findFirst()
    }
}
