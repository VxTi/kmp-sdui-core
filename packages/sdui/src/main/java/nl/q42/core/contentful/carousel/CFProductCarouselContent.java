package nl.q42.core.contentful.carousel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import nl.q42.core.contentful.interfaces.CFContentItem;
import nl.q42.core.contentful.source.CFProductCarouselSource;
import org.springframework.lang.Nullable;

@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class CFProductCarouselContent implements CFContentItem, CFCarousel
{
  @Builder.Default
  public           String                  type = "product-carousel";
  public           String                  id;
  public           String                  title;
  public @Nullable String                  subtitle;
  public @Nullable String                  viewAllUrl;
  public @Nullable String                  viewAllDescription;
  public           CFProductCarouselSource source;
}
