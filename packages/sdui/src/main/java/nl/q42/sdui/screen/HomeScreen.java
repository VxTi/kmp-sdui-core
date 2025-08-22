package nl.q42.sdui.screen;

import nl.q42.core.app.AppContext;
import nl.q42.core.content.ContentInfoMessage;
import nl.q42.core.content.ContentItem;
import nl.q42.core.contentful.CFRecallContent;
import nl.q42.sdui.component.SearchBarComponent;
import nl.q42.sdui.component.SpacerComponent;
import nl.q42.sdui.component.TitleComponent;
import nl.q42.sdui.component.header.ProfileHeader;
import nl.q42.sdui.component.section.PrimarySection;
import nl.q42.sdui.component.section.SectionContainer;
import nl.q42.sdui.event.AirshipEvent;
import nl.q42.sdui.event.AnalyticsEvent;
import nl.q42.sdui.event.BFFEvent;
import nl.q42.sdui.event.FirebaseCustomEvent;
import nl.q42.sdui.screen.config.AutoRefreshSetting;
import nl.q42.sdui.screen.config.RefreshIdentifiers;
import nl.q42.webdata.Category;
import org.springframework.lang.Nullable;

import java.sql.Date;
import java.util.Collections;

public class HomeScreen
{

  private static final int AUTO_REFRESH_INTERVAL = 10 * 1000;

  /**
   * This method checks if the snowflakes should be shown on the home screen.
   * The snowflakes should be shown between the 6th of December and the 31st of December.
   *
   * @return True if the snowflakes should be shown, false otherwise.
   */
  private static boolean shouldShowSnowflakes()
  {
    var startDate = Date.valueOf("2024-12-06");
    var endDate   = Date.valueOf("2024-12-31");
    return startDate.before(Date.valueOf("2024-12-06")) && endDate.after(
        Date.valueOf("2024-12-31"));
  }

  /**
   * This method returns the tags that should be used for the home screen.
   *
   * @return The tags that should be used for the home screen.
   */
  private static String[] getTags()
  {
    return shouldShowSnowflakes() ? new String[]{"overlay-snowfall"} : new String[]{};
  }

  public static AbstractScreen create(
      AppContext context,
      boolean hasOpenOrders,
      @Nullable Content content,
      @Nullable Category rootCategory)
  {

    return ScreenWithContentList
        .builder()
        .id("home-" + ((content != null && content.id != null) ? content.id : "default"))
        .canRefresh(true)
        .header(context.filterForVersion(
            new ProfileHeader("Home", hasOpenOrders)))
        .tags(getTags())
        .refreshId(RefreshIdentifiers.VOUCHERS)
        .autoRefresh(new AutoRefreshSetting(AUTO_REFRESH_INTERVAL))
        .content(buildHomeContent(context))
        .onAppearEvents(getOnAppearEvents())
        .onReadyEvents(getOnReadyEvents())
        .build();
  }

  private static AnalyticsEvent[] getOnAppearEvents()
  {
    return new FirebaseCustomEvent[]{
        new FirebaseCustomEvent(
            "view_home", Collections.singletonMap(
            "page_type",
            "home"
        )
        )};
  }

  private static BFFEvent[] getOnReadyEvents()
  {
    return new BFFEvent[]{AirshipEvent.screenView("home", null)};
  }

  private static SectionContainer buildHomeContent(AppContext context)
  {
    return SectionContainer
        .builder()
        .section(
            PrimarySection.builder()
                          .contentId("home-section-content")
                          .component(SpacerComponent.builder().contentId("top").size(3).build())
                          .component(SearchBarComponent.builder()
                                                       .placeholder("Test")
                                                       .contentId("home-search")
                                                       .build())
                          .build())
        .section(
            PrimarySection.builder()
                          .contentId("home-section-quick-links")
                          .component(SpacerComponent
                                         .builder()
                                         .contentId(
                                             "home-section-quick-links-spacer-top")
                                         .size(2).build())
                          .component(
                              TitleComponent.builder()
                                            .contentId("home-quick-links-title")
                                            .text("Quick Links")
                                            .displayStyle(
                                                TitleComponent.DisplayStyle.LARGE)
                                            .build()
                          )
                          .component(SpacerComponent.builder()
                                                    .contentId(
                                                        "home-section-quick-links-spacer-middle")
                                                    .size(8)
                                                    .build()
                          )
                          .build())
        .build();
  }


  public record Content(
      String id,
      ContentInfoMessage[] infoMessages,
      ContentItem[] items,
      @Nullable CFRecallContent recall
  )
  {
  }
}
