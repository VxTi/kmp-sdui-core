package nl.q42.sdui.screen.common;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nl.q42.core.RequestContext;
import nl.q42.sdui.screen.HomeScreen;
import nl.q42.sdui.screen.ProfileScreen;
import nl.q42.sdui.screen.SearchScreen;
import nl.q42.sdui.screen.SettingsScreen;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
public class SDUIScreen
{

  private final         Class<? extends Screen> screen;
  private final @Getter String                  screenIdentifier;

  public static final SDUIScreen[] screens = new SDUIScreen[]{
      new SDUIScreen(HomeScreen.class, "home"),
      new SDUIScreen(SearchScreen.class, "search"),
      new SDUIScreen(ProfileScreen.class, "profile"),
      new SDUIScreen(SettingsScreen.class, "settings")
  };

  public static final SDUIScreenTab[] screenTabs = new SDUIScreenTab[]{
      new SDUIScreenTab("tab.home", "home.png", "home"),
      new SDUIScreenTab("tab.search", "search.png", "search"),
      new SDUIScreenTab("tab.profile", "profile.png", "profile"),
      new SDUIScreenTab("tab.settings", "settings.png", "settings")
  };

  private SDUIScreen(Class<? extends Screen> component, String screenIdentifier)
  {
    this.screen           = component;
    this.screenIdentifier = screenIdentifier;
  }

  public static Optional<SDUIScreen> tryGetById(String screenIdentifier)
  {
    return Stream
        .of(screens)
        .filter(screen -> screen.getScreenIdentifier().equals(screenIdentifier))
        .findFirst();
  }

  public static ScreenTab[] tryInstantiateTabs(RequestContext context)
  {
    return Stream.of(SDUIScreen.screenTabs)
                 .map(sduiTabScreen -> new ScreenTab(
                     sduiTabScreen.titleLabelKey,
                     sduiTabScreen.iconUrl, sduiTabScreen.screenName
                 ))
                 .toArray(ScreenTab[]::new);
  }

  /**
   * Creates a new instance of a Screen associated with the ApplicationTab.
   * The method attempts to instantiate the Screen using the provided RequestContext.
   * If an error occurs during instantiation (e.g., reflective operation exception),
   * an empty Optional is returned.
   *
   * @param context the RequestContext containing information needed to instantiate the Screen,
   *                such as locale, app version, and revalidation flag
   * @return an Optional containing the created Screen instance if successful,
   * or an empty Optional if instance creation fails
   */
  public Optional<Screen> tryInstantiate(RequestContext context)
  {
    try
    {
      Constructor<? extends Screen> constructor = this.screen.getDeclaredConstructor(
          RequestContext.class,
          String.class
      );
      constructor.setAccessible(true);
      return Optional.of(constructor.newInstance(context, this.screenIdentifier));
    }
    catch (
        NoSuchMethodException
        | InstantiationException
        | IllegalAccessException
        | InvocationTargetException exception)
    {

      log.error(
          "Error instantiating {}: {}",
          this.screen.getName(),
          exception.getMessage(),
          exception
      );
      return Optional.empty();
    }
  }

  public record SDUIScreenTab(
      String titleLabelKey,
      String iconUrl,
      String screenName
  )
  {

  }
}
