package nl.q42.core.content.carousel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.q42.core.BaseProduct;
import nl.q42.core.data.RecommendationData;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@RequiredArgsConstructor
public class ContentProductCarousel implements Carousel
{
    public final     String             type = "product-carousel";
    public           String             id;
    public           String             title;
    public @Nullable String             subtitle;
    public @Nullable String             viewAllUrl;
    public @Nullable String             viewAllDescription;
    public           BaseProduct[]      products;
    public @Nullable RecommendationData data;
    public           SourceName         source;

    @AllArgsConstructor
    @Getter
    public enum SourceName
    {
        PRODUCT_GROUP_VALUES("product-group-values"),
        EINSTEIN_RECOMMENDATIONS("einstein-recommendations"),
        APP_BFF("appBff");

        private final String value;
    }
}
