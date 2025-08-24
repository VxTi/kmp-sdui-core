package nl.q42.common.screen

import nl.q42.common.components.Component
import nl.q42.common.components.ComponentTypes
import kotlinx.serialization.Serializable

@Serializable
class Screen {
    val type: String = ComponentTypes.SCREEN

    val name: String;
    val content: MutableList<Component>;

    constructor(name: String, content: MutableList<Component>) {
        this.name = name
        this.content = content
    }

    constructor(name: String, vararg content: Component) : this(
        name,
        content.toMutableList()
    )

    fun getComponentById(contentId: String): Component? {
        for (component in content) {
            if (component.contentId == contentId) {
                return component
            }
        }
        return null
    }
}
