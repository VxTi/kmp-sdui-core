package nl.q42.common.screen

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable
import nl.q42.common.ServerComponent

@Serializable
class Screen {
    val identifier: String;
    val hash: Int;
    val content: List<@Polymorphic ServerComponent>;

    constructor(name: String, content: List<@Polymorphic ServerComponent>) {
        this.identifier = name
        this.content = content
        this.hash = content.hashCode();
    }

    constructor(name: String, vararg content: ServerComponent) : this(name, content.toList())

    fun getComponentById(contentId: String): ServerComponent? {
        for (component in content) {
            if (component.contentId == contentId) {
                return component
            }
        }
        return null
    }
}
