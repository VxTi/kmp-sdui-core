package nl.q42.core.util;

import org.springframework.lang.Nullable;

import java.net.URI;
import java.net.URL;
import java.util.Objects;

public class UrlUtils
{

  /**
   * Create a URL from a string, return null if a parsing exception occurs
   *
   * @param url the url to create
   * @return the URL or null if the url is invalid
   */
  public static URL createUrl(String url)
  {
    try
    {
      return URI.create(url).toURL();
    }
    catch (Exception e)
    {
      return null;
    }
  }

  /**
   * Create a URL from a string, return null if the url is invalid
   *
   * @param url the url to create
   * @return the URL or null if the url is invalid
   */
  public static @Nullable URL createHttpUrl(String url)
  {
    try
    {
      // url must contain a scheme
      if (url.startsWith("http"))
        return createUrl(url);

      // other schema
      if (url.matches("^\\w+://") || url.startsWith("/"))
        return null;

      return createUrl("https://" + url);
    }
    catch (Exception e)
    {
      return null;
    }
  }

  /**
   * Clean the pathname of a URL
   * This will remove any trailing slashes, and return just the pathname
   *
   * @param url the url to clean
   * @return the cleaned pathname or null if the url is invalid
   */
  public static String cleanUrlPathname(String url)
  {
    try
    {
      return Objects.requireNonNull(createUrl(url)).getPath().replaceFirst("/+$", "");
    }
    catch (Exception e)
    {
      return null;
    }
  }
}
