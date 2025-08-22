package nl.q42.core;

public class RequestContext
{
  public boolean revalidateRequest;
  public Locale  locale;
  public int appVersion;

  public RequestContext(
      Locale locale,
      int appVersion,
      boolean revalidateRequest
  ) {
    this.revalidateRequest = revalidateRequest;
    this.locale            = locale;
    this.appVersion = appVersion;
  }
}
