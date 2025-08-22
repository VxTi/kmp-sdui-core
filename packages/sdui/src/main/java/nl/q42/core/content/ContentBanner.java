package nl.q42.core.content;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import nl.q42.core.content.overlay.BannerOverlayImages;
import nl.q42.core.content.overlay.Overlay;
import nl.q42.core.contentful.CFBannerContent;
import nl.q42.core.uri.LookupDestination;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@RequiredArgsConstructor
public class ContentBanner implements ContentItem
{

  public final     String               type = "banner";
  public final     String               id;
  public final     CFBannerContent.Size size;
  public final     String               imageUrl;
  public @Nullable String               imageTitle;
  public @Nullable Caption              caption;
  public @Nullable String               accessibilityTitle;
  public @Nullable LookupDestination    destination;
  public @Nullable String[]             visibleForSegments;
  public @Nullable BannerOverlayImages  overlayImages;
  public @Nullable Overlay              overlay;

  public record Caption(String title, @Nullable String subtitle)
  {
  }

}
