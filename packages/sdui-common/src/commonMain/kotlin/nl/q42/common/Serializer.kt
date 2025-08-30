package nl.q42.common

import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val SDUIPolymorphicSerializer = SerializersModule {
    polymorphic(Component::class) {
        subclass(SpacerComponent::class, SpacerComponent.serializer())
        subclass(ButtonComponent::class, ButtonComponent.serializer())
        subclass(TextComponent::class, TextComponent.serializer())
        subclass(SearchBarComponent::class, SearchBarComponent.serializer())
        subclass(ImageComponent::class, ImageComponent.serializer())
        subclass(ScrollableContainer::class, ScrollableContainer.serializer())
    }
    polymorphic(Event::class) {
        subclass(NavigationEvent::class, NavigationEvent.serializer())
    }
}