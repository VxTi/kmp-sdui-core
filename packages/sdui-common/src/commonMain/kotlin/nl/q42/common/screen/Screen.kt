package nl.q42.common.screen

import nl.q42.common.components.Component
import nl.q42.common.components.ComponentTypes
import java.io.Serializable
import java.util.List

abstract class Screen(val name: String?, val content: MutableList<Component?>) : Serializable {
    val type: String = ComponentTypes.SCREEN

    constructor(screenName: String?, vararg content: Component) : this(
        screenName,
        List.of<Component?>(*content)
    )

    fun getComponentById(contentId: String?): Component? {
        for (component in content) {
            if (component?.contentId == contentId) {
                return component
            }
        }
        return null
    }
}
