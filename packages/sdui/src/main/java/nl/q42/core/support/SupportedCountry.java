package nl.q42.core.support;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SupportedCountry
{
  NL("NL"),
  BE("BE");

  private final String countryCode;

  public static boolean isSupportedCountry(String countryCode)
  {
    for (SupportedCountry supportedCountry : SupportedCountry.values())
    {
      if (supportedCountry.getCountryCode().equals(countryCode))
      {
        return true;
      }
    }
    return false;
  }

  public static SupportedCountry fromCountryCode(String countryCode)
  {
    for (SupportedCountry supportedCountry : SupportedCountry.values())
    {
      if (supportedCountry.getCountryCode().equals(countryCode))
      {
        return supportedCountry;
      }
    }
    throw new IllegalArgumentException("Country code " + countryCode + " is not supported");
  }
}
