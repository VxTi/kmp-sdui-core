package nl.q42.core.contentful;

import lombok.RequiredArgsConstructor;
import nl.q42.core.contentful.interfaces.CFContentItem;

@RequiredArgsConstructor
public class CFDiscountOverviewMenuContent implements CFContentItem
{
  public final String type = "discount-overview-menu";
  public       String id;
}
