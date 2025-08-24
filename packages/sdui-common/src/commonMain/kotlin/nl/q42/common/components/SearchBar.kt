package nl.q42.common.components

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName(ComponentTypes.SEARCH_BAR)
class SearchBar(val placeholder: String?, override val contentId: String) :
    Component(ComponentTypes.SEARCH_BAR) {
}