package nl.q42.common.screen

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable
import nl.q42.common.Component

@Serializable
class Screen {
    val identifier: String;
    val hash: Int;
    val content: MutableList<@Polymorphic Component>;

    constructor(name: String, content: MutableList<@Polymorphic Component>) {
        this.identifier = name
        this.content = content
        this.hash = content.hashCode();
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
