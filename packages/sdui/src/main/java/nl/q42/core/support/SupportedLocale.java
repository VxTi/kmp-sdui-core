package nl.q42.core.support;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

import java.net.URL;

import static nl.q42.core.util.UrlUtils.createUrl;

@Getter
@RequiredArgsConstructor
public enum SupportedLocale
{
  NL_NL("nl-nl", "nl"),
  NL_BE("nl-be", "be"),
  FR_BE("fr-be", "fr");

  public static final SupportedLocale DEFAULT = NL_NL;

  private final String locale;
  private final String language;

  public static @Nullable SupportedLocale fromString(String locale)
  {
    for (SupportedLocale supportedLocale : SupportedLocale.values())
    {
      if (supportedLocale.locale.equals(locale) || supportedLocale.language.equals(locale))
      {
        return supportedLocale;
      }
    }
    return null;
  }

  public static boolean is(String locale)
  {
    for (SupportedLocale supportedLocale : SupportedLocale.values())
    {
      if (supportedLocale.locale.equals(locale))
      {
        return true;
      }
    }
    return false;
  }

  public static boolean isSupported(String locale)
  {
    for (SupportedLocale supportedLocale : SupportedLocale.values())
    {
      if (supportedLocale.locale.equalsIgnoreCase(locale) ||
          supportedLocale.language.equalsIgnoreCase(locale))
        return true;
    }
    return false;
  }

  /**
   * Returns the locale based on the URL.
   *
   * @param url The URL to check.
   * @return The locale or null if the URL does not contain a supported locale.
   */
  public static @Nullable SupportedLocale fromUrl(String url)
  {
    URL parsedUrl = createUrl(url);

    assert parsedUrl != null;

    /* We use includes() to support www. and direct links */
    if (parsedUrl.getHost().contains("hema.nl"))
    {
      return SupportedLocale.NL_NL;
    }

    if (parsedUrl.getHost().contains("hema.com"))
    {
      if (url.contains("fr-be"))
        return SupportedLocale.FR_BE;

      if (url.contains("nl-be"))
        return SupportedLocale.NL_BE;
    }

    return null;
  }
}
