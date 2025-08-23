package nl.q42.common.actions

class NavigationAction(val path: String?, var cachePrevious: Boolean) :
    Action(ActionTypes.NAVIGATION)
