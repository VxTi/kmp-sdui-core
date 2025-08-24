package nl.q42.common.screen

import java.io.Serializable

@kotlin.jvm.JvmRecord
data class ScreenTab(val title: String?, val imageUrl: String?, val screenName: String?) :
    Serializable
