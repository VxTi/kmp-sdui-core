package nl.q42.sdui.screen;

import org.springframework.stereotype.Component;

import nl.q42.common.components.SpacerComponent;
import nl.q42.common.components.TextColor;
import nl.q42.common.components.TextFormatting;
import nl.q42.core.RequestContext;
import nl.q42.common.components.TextComponent;
import nl.q42.common.screen.Screen;

@Component
public class HomeScreen extends Screen {

    public static final String SCREEN_NAME = "home";

    public HomeScreen() {
        super(SCREEN_NAME,
                new TextComponent("Hello world!", TextColor.INFO, TextFormatting.BOLD, "test-1"),
                new SpacerComponent(3, "test-spacer")
        );
    }

}
