package nl.q42.common.components

import nl.q42.common.VersionDependable

@VersionDependable(until = 2)
class SpacerComponent(val size: Int, contentId: String?) :
    Component(ComponentTypes.SPACER, contentId)
