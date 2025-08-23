package nl.q42.common.screen;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import nl.q42.common.components.Component;
import nl.q42.common.components.ComponentTypes;

public abstract class Screen implements Serializable {

    public final String name;
    public final String type = ComponentTypes.SCREEN;
    public final List<Component> content;

    public Screen(String screenName, List<Component> content) {
        this.name = screenName;
        this.content = content;
    }

    public Screen(String screenName, Component... content) {
        this(screenName, List.of(content));
    }

    public final Optional<Component> getComponentById(String contentId) {
        return content.stream()
                .filter(component -> component.contentId.equals(contentId))
                .findFirst();
    }
}
