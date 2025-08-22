package nl.q42.core.contentful;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import nl.q42.core.contentful.carousel.CFCarouselContent;
import nl.q42.core.contentful.interfaces.CFContentItem;
import org.springframework.lang.Nullable;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class CFHeroCampaignEventContent implements CFContentItem
{
  public @Builder.Default String               type = "hero-campaign-event";
  public                  String               id;
  public                  CFCampaignEventColor color;
  public                  String               imageUrl;
  public                  String               displayTitle;
  public @Nullable        String               displaySubtitle;
  public                  String               accessibilityTitle;
  public                  String               url;
  public @Nullable        String               imageOverlayTopLeftUrl;
  public @Nullable        String               imageOverlayTopRightUrl;
  public @Nullable        String               imageOverlayBottomLeftUrl;
  public @Nullable        String               imageOverlayBottomRightUrl;
  public @Nullable        CFCarouselContent    carousel;
}
