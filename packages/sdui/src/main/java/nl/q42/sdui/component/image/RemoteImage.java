package nl.q42.sdui.component.image;

import org.springframework.lang.Nullable;

public class RemoteImage extends AbstractImage
{

  public           String url;
  public @Nullable String darkModeUrl;
  public           int    width;
  public           int    height;

  public RemoteImage(String url)
  {
    this(url, null, 0, 0);
  }

  public RemoteImage(String url, @Nullable String darkModeUrl, int width, int height)
  {
    super("REMOTE", null);
    this.url         = url;
    this.darkModeUrl = darkModeUrl;
    this.width       = width;
    this.height      = height;
  }
}
