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
public class CFRegularCampaignEventContent implements CFContentItem
{

    public @Builder.Default String               type = "regular-campaign-event";
    public @Nullable String     displaySubtitle;
    public @Nullable CFCarousel carousel;
    public           String     id;
    public                  CFCampaignEventColor color;
    public                  String               displayTitle;
    public                  String               accessibilityTitle;
    public                  String               url;
}