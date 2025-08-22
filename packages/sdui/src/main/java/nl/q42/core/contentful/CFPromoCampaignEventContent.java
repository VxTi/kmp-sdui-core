package nl.q42.core.contentful;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import nl.q42.core.contentful.carousel.CFCarousel;
import nl.q42.core.contentful.interfaces.CFContentItem;
import org.springframework.lang.Nullable;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CFPromoCampaignEventContent implements CFContentItem
{
  public @Builder.Default String               type = "promo-campaign-event";
  public                  String               id;
  public                  CFCampaignEventColor color;
  public                  String               imageUrl;
  public                  String               displayTitle;
  public @Nullable        String               displaySubtitle;
  public                  String               accessibilityTitle;
  public                  String               url;
  public @Nullable        CFCarousel           carousel;
}
