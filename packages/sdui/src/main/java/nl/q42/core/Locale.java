package nl.q42.core;

public enum Locale
{
  NL_NL("nl-NL"),
  EN_US("en-US"),
  EN_UK("en-UK");

  public final String value;

  Locale(String value)
  {
    this.value = value;
  }

  public static Locale from(String value, Locale defaultLocale)
  {
    for (Locale locale : Locale.values())
    {
      if (locale.value.equals(value))
        return locale;
    }

    return defaultLocale;
  }
}
