package nl.q42.core.contentful;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import nl.q42.core.contentful.interfaces.CFContentItem;
import org.springframework.lang.Nullable;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CFProductListContent implements CFContentItem
{

  public @Builder.Default String   type = "product-list";
  public @Nullable        String   productsUrl;
  public @Nullable        String   buttonTitle;
  public @Nullable        String   buttonUrl;
  public                  String[] productIds;
  public                  int      maxProducts;
  public                  String   id;
}
