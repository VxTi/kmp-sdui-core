package nl.q42.sdui.component.image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.q42.core.app.AppContext;

@Getter
@AllArgsConstructor
public enum StaticImage
{
  BASKET("basket"),
  WELCOME_DISCOUNT("welcome-discount"),
  GOED_IDEE("goed_idee_header_image"),
  HEADER_TOMPOUCE("header_tompouce"),
  HEARTS_CONFETTI("hearts_confetti"),
  HEMA_PAS("hema_pas"),
  HEMA_EXTRA("hema_extra"),
  PAYMENT_OPTIONS("payment_options"),
  REVIEWS_STAR("reviews_star"),
  EMPTY_BASKET("empty_basket"),
  EMPTY_FAVORITES("empty_favorites"),
  EMPTY_PURCHASE_HISTORY("empty_purchase_history"),
  EMPTY_SEARCH("empty_search"),
  EMPTY_SEARCH_RESULTS("empty_search_results"),
  EMPTY_PROMOTION("empty_promotion");

  private final String name;

  public boolean isLocalised()
  {
    return switch (this)
    {
      case PAYMENT_OPTIONS, WELCOME_DISCOUNT -> true;
      default -> false;
    };
  }

  public boolean hasDarkModeVariant()
  {
    return switch (this)
    {
      case PAYMENT_OPTIONS, GOED_IDEE -> true;
      default -> false;
    };
  }

  /**
   * Returns the file extension for the image based on the platform and feature support.
   *
   * @param context The app context.
   * @return The file extension.
   */
  public String getFileExtension(AppContext context)
  {
    return switch (this)
    {
      case StaticImage.PAYMENT_OPTIONS, StaticImage.HEMA_PAS, StaticImage.HEMA_EXTRA ->
      {
        if (context.features.isSupported("iOSSVGSupport"))
          yield "svg";

        yield context.platform.getPlatform().equals("android") ? "svg" : "png";
      }
      case StaticImage.HEARTS_CONFETTI, StaticImage.BASKET, StaticImage.REVIEWS_STAR -> "jpg";
      case StaticImage.HEADER_TOMPOUCE, StaticImage.GOED_IDEE, StaticImage.EMPTY_BASKET,
           StaticImage.EMPTY_FAVORITES, StaticImage.EMPTY_PURCHASE_HISTORY,
           StaticImage.EMPTY_SEARCH, StaticImage.EMPTY_SEARCH_RESULTS,
           StaticImage.EMPTY_PROMOTION, StaticImage.WELCOME_DISCOUNT -> "png";
    };
  }
}
