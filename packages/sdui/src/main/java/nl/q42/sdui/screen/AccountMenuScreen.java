package nl.q42.sdui.screen;

import nl.q42.core.app.AppContext;
import nl.q42.sdui.component.PrimaryMenuItemComponent;
import nl.q42.sdui.component.image.LocalImages;
import nl.q42.sdui.component.section.AbstractSection;
import nl.q42.sdui.component.section.PrimarySection;
import nl.q42.sdui.component.section.SectionContainer;
import nl.q42.sdui.screen.config.RefreshIdentifiers;

import static nl.q42.core.app.Translations.translate;

public class AccountMenuScreen
{
  public static ScreenWithContentList create(AppContext context)
  {
    return ScreenWithContentList
        .builder()
        .id("account-menu")
        .canRefresh(true)
        .refreshId(RefreshIdentifiers.VOUCHERS)
        .content(buildContent(context))
        .build();
  }

  private static SectionContainer buildContent(AppContext context)
  {
    return SectionContainer.builder()
                           .section(primaryButtonSection(context))
                           // .section(signoutSection(context))
                           .build();
  }

  private static AbstractSection primaryButtonSection(AppContext context)
  {
    return PrimarySection
        .builder()
        .contentId("account-menu-primary-buttons")
        .isUsingSpacerComponents(true)
        .component(
            PrimaryMenuItemComponent
                .builder()
                .contentId("account-menu-primary-buttons-0")
                .title(translate(
                    "server.account.primary.favorites", context.locale))
                .image(LocalImages.ACCOUNT_FAVORITES.toLocalImage())
                .build()
        )
        .component(
            PrimaryMenuItemComponent
                .builder()
                .contentId("account-menu-orders-button-1")
                .title(translate(
                    "server.account.primary.orders", context.locale))
                .image(LocalImages.ACCOUNT_ORDERS.toLocalImage())
                .build()
        )
        .component(
            PrimaryMenuItemComponent
                .builder()
                .contentId("account-menu-vouchers-button-2")
                .title(translate(
                    "server.account.primary.vouchers", context.locale))
                .image(LocalImages.ACCOUNT_VOUCHERS.toLocalImage())
                .build()
        )
        .build();
  }

  private static AbstractSection signoutSection(AppContext context)
  {
    if (!context.authenticated)
      return null;

    return PrimarySection
        .builder()
        .contentId("account-menu-sign-out-section")
        .isUsingSpacerComponents(true)
        .component(PrimaryMenuItemComponent
                       .builder()
                       .contentId("account-menu-sign-out-button")
                       .title(translate(
                           "server.account.primary.signout", context.locale))
                       .build()
        )
        .build();
  }
}
