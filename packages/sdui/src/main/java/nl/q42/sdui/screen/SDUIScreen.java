package nl.q42.sdui.screen;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nl.q42.core.RequestContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
public class SDUIScreen
{

  private final         Class<? extends Screen> screen;
  private final @Getter String                  screenIdentifier;
  private final         boolean                 tabScreen;

  public static final SDUIScreen[] screens = new SDUIScreen[]{
      new SDUIScreen(HomeScreen.class, "home", true),
      new SDUIScreen(SearchScreen.class, "search", true),
      new SDUIScreen(ProfileScreen.class, "profile", true),
      new SDUIScreen(SettingsScreen.class, "settings", true)
  };

  public static final SDUIScreen[] tabScreens = Stream
      .of(screens)
      .filter(screen -> screen.tabScreen)
      .toArray(SDUIScreen[]::new);

  private SDUIScreen(Class<? extends Screen> component, String screenIdentifier, boolean tabScreen)
  {
    this.screen           = component;
    this.screenIdentifier = screenIdentifier;
    this.tabScreen        = tabScreen;
  }

  /**
   * Constructor for non-tab screens
   */
  private SDUIScreen(Class<? extends Screen> component, String screenIdentifier)
  {
    this(component, screenIdentifier, false);
  }

  public static Optional<SDUIScreen> tryGetById(String screenIdentifier)
  {
    return Stream
        .of(screens)
        .filter(screen -> screen.getScreenIdentifier().equals(screenIdentifier))
        .findFirst();
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
}
