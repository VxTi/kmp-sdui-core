package nl.q42.common.components

import kotlinx.serialization.Serializable

@Serializable
abstract class Component(val type: String?, val contentId: String?)
