package nl.q42.sdui.screen;

import org.springframework.stereotype.Component;

import nl.q42.common.screen.Screen;

@Component
public class SettingsScreen extends Screen {

    public static final String SCREEN_NAME = "settings";

    public SettingsScreen() {
        super(SCREEN_NAME);
    }
}
