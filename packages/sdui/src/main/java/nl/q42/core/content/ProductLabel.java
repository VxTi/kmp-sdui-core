package nl.q42.core.content;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import nl.q42.core.content.overlay.OverlayItem;

@AllArgsConstructor
@RequiredArgsConstructor
public class ProductLabel implements OverlayItem
{

  public final String            type = "PRODUCT_LABEL";
  public       String            accessibilityDescription;
  public       ProductLabelColor color;
  public       ProductLabelShape shape;
  public       LabelLine[]       lines;

  public enum ProductLabelShape
  {
    ROUNDED, HALF_ROUNDED
  }

  public enum ProductLabelColor
  {
    PROMOTION, MARKETING, NEW, INGREDIENTS, CONSUMENTENBOND_BEST_BUY,
    CONSUMENTENBOND_BEST_IN_TEST
  }

  public record LabelLine(String text, TextStyle style)
  {
  }
}
