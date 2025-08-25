package nl.q42.common.components

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ComponentTypes.BUTTON)
class ButtonComponent(val text: String, override val contentId: String) : Component(ComponentTypes.BUTTON){
}