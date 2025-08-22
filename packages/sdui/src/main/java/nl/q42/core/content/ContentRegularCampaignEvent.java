package nl.q42.core.content;

import nl.q42.core.content.carousel.Carousel;
import nl.q42.core.contentful.CFCampaignEventColor;
import nl.q42.core.uri.LookupDestination;
import org.springframework.lang.Nullable;

public class ContentRegularCampaignEvent implements ContentItem
{
    public final     String               type = "regular-campaign-event";
    public           String               id;
    public           CFCampaignEventColor color;
    public           String               displayTitle;
    public @Nullable String               displaySubtitle;
    public           String               accessibilityTitle;
    public @Nullable LookupDestination    destination;
    public @Nullable Carousel             carousel;

}
