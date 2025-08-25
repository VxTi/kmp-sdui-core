package nl.q42.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import nl.q42.common.components.Component

abstract class ComponentDrawable<T : Component>(component: T) {
    @Composable
    abstract fun compose(): @Composable ColumnScope.() -> Unit
}