package nl.q42.core.content;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import nl.q42.core.content.carousel.Carousel;
import nl.q42.core.content.overlay.Overlay;
import nl.q42.core.contentful.CFCampaignEventColor;
import nl.q42.core.uri.LookupDestination;
import org.springframework.lang.Nullable;

@RequiredArgsConstructor
@AllArgsConstructor
public class ContentHeroCampaignEvent implements ContentItem
{
  public final     String               type = "hero-campaign-event";
  public final     String               id;
  public final     CFCampaignEventColor color;
  public final     String               displayTitle;
  public final     String               accessibilityTitle;
  public final     String               imageUrl;
  public @Nullable String               displaySubtitle;
  public @Nullable LookupDestination    destination;
  public @Nullable Carousel             carousel;
  public @Nullable Overlay              overlay;
}
