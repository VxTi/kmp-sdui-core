package nl.q42.core.content.carousel;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import nl.q42.core.content.ContentBanner;
import nl.q42.core.uri.LookupDestination;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@RequiredArgsConstructor
public class ContentCarousel implements Carousel
{
    public final     String            type = "carousel";
    public           String            id;
    public @Nullable String            title;
    public @Nullable String            subtitle;
    public @Nullable LookupDestination destination;
    public @Nullable String          destinationDescription;
    public           ContentBanner[] items;
}
