package nl.q42.common.components

import kotlinx.serialization.*
import kotlinx.serialization.json.JsonClassDiscriminator

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@JsonClassDiscriminator("type")
sealed class Component(val type: String) {
    abstract val contentId: String
}
