package nl.q42.core.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import nl.q42.core.config.CoreConfig;
import nl.q42.core.support.SupportedCountry;
import nl.q42.core.support.SupportedLocale;
import nl.q42.core.support.SupportedPlatform;
import nl.q42.sdui.RequiresAppVersion;
import org.springframework.lang.Nullable;

import java.util.Map;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class AppContext
{
  public           AppFeatures          features;
  public           Map<String, Boolean> experiments;
  public           SupportedPlatform    platform;
  public           SupportedLocale      locale;
  public           SupportedCountry     country;
  public           int                  version = -1;
  public           CoreConfig           config;
  public           boolean              authenticated;
  public @Nullable String               cookieId;
  public           boolean              previewContentEnabled;
  public @Nullable String               forwardedHeaderValue;

  /**
   * Check if the component is supported by the given context.
   *
   * @param component The component to check support for.
   * @return True if the component is supported, false otherwise.
   */
  public boolean isSupported(Class<?> component)
  {
    var annotations = component.getAnnotation(RequiresAppVersion.class);
    if (annotations == null)
      return true;

    return this.version < 0 || this.version >= annotations.value();
  }

  /**
   * Filter the object based on the version of the app.
   *
   * @param object The object to filter.
   * @param <T>    The type of the object.
   * @return The object if it is supported, null otherwise.
   */
  public <T> T filterForVersion(T object)
  {
    if (object == null)
    {
      return null;
    }
    if (isSupported(object.getClass()))
    {
      return object;
    }
    return null;
  }
}
