package nl.q42.core.contentful.source;

import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

@SuperBuilder
@RequiredArgsConstructor
public class CFProductGroupValues implements CFProductCarouselSource
{
    public final     String   type = "product-group-values";
    public           String   id;
    public           String[] productIds;
    public @Nullable String   title;
    public           int      maxProducts;
}
