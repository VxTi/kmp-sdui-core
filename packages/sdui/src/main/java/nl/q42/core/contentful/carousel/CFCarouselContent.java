package nl.q42.core.contentful.carousel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import nl.q42.core.contentful.CFBannerContent;
import nl.q42.core.contentful.interfaces.CFContentItem;
import org.springframework.lang.Nullable;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CFCarouselContent implements CFContentItem, CFCarousel
{
    @Builder.Default
    public           String            type = "carousel";
    public           String            id;
    public @Nullable String            title;
    public @Nullable String            subtitle;
    public @Nullable String            url;
    public @Nullable String            destinationDescription;
    public @Nullable CFBannerContent[] items;
}
