package nl.q42.sdui;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import nl.q42.common.screen.Screen;
import nl.q42.common.screen.ScreenTab;
import nl.q42.sdui.screen.HomeScreen;
import nl.q42.sdui.screen.ProfileScreen;
import nl.q42.sdui.screen.SearchScreen;
import nl.q42.sdui.screen.SettingsScreen;

@Service

public class ScreenRegistry {
    private final List<Screen> screens;

    public static final ScreenTab[] SCREEN_TABS = new ScreenTab[]{
            new ScreenTab("tab.home", "home.png", HomeScreen.SCREEN_NAME),
            new ScreenTab("tab.search", "search.png", SearchScreen.SCREEN_NAME),
            new ScreenTab("tab.profile", "profile.png", ProfileScreen.SCREEN_NAME),
            new ScreenTab("tab.settings", "settings.png", SettingsScreen.SCREEN_NAME)
    };

    public ScreenRegistry(List<Screen> screens) {
        this.screens = screens;
    }

    public List<Screen> getAll() {
        return this.screens;
    }

    public Screen getByIdentifier(String screenIdentifier) {
        return this.screens.stream()
                .filter(screen -> screen.name.equals(screenIdentifier))
                .findFirst()
                .orElse(null);
    }
}
