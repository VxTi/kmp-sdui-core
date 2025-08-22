package nl.q42.core.uri;

import org.springframework.lang.Nullable;

public class URIParameters
{
  /**
   * Get the value of a query parameter from a URL.
   *
   * @param url The URL to get the query parameter value from.
   * @param key The key of the query parameter to get the value of.
   * @return The value of the query parameter with the given key, or null if the key is not present in the URL.
   */
  public static @Nullable String getQueryParameterValue(String url, String key)
  {
    int queryIndex = url.indexOf('?');
    if (queryIndex == -1)
    {
      return null;
    }
    return getQueryParameterValue(url.substring(queryIndex + 1).split("&"), key);
  }

  public static @Nullable String getQueryParameterValue(String[] urlPairs, String key)
  {
    for (String urlPair : urlPairs)
    {
      String[] keyValue = urlPair.split("=");
      if (keyValue.length == 2 && keyValue[0].equals(key))
      {
        return keyValue[1];
      }
    }
    return null;
  }

  public static String[] getQueryParameterSegments(String url)
  {
    int queryIndex = url.indexOf('?');
    if (queryIndex == -1)
    {
      return new String[0];
    }
    return url.substring(queryIndex + 1).split("&");
  }
}
