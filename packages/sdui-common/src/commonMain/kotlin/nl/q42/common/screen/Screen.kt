package nl.q42.common.screen

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable
import nl.q42.common.Component

@Serializable
class Screen {
    val name: String;
    val content: MutableList<@Polymorphic Component>;

    constructor(name: String, content: MutableList<@Polymorphic Component>) {
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
