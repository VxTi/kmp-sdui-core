package nl.q42.sdui.screen;

import org.springframework.stereotype.Component;

import nl.q42.common.screen.Screen;

@Component
public class SearchScreen extends Screen {

    public static final String SCREEN_NAME = "search";

    public SearchScreen() {
        super(SCREEN_NAME);
    }
}
