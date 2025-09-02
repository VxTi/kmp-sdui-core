package nl.q42.common.screen

import kotlinx.serialization.Serializable

@Serializable
data class ScreenTab(val title: String, val imageUrl: String?, val screenId: String)
