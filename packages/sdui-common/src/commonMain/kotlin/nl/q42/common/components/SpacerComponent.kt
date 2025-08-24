package nl.q42.common.components

import kotlinx.serialization.*
import nl.q42.common.VersionDependable

@VersionDependable(until = 2)
@Serializable
@SerialName(ComponentTypes.SPACER)
class SpacerComponent(
    val size: Int,
    override val contentId: String
) :
    Component(ComponentTypes.SPACER)
