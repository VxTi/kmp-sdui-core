package nl.q42.sdui.screen;

import nl.q42.core.app.AppContext;
import nl.q42.sdui.component.SearchBarComponent;
import nl.q42.sdui.component.section.PrimarySection;
import nl.q42.sdui.component.section.SectionContainer;

public class CategoriesScreen
{
  public static AbstractScreen create(AppContext context)
  {
    return ScreenWithContentList
        .builder()
        .id("categories")
        .canRefresh(true)
        .content(generateContent(context))
        .build();
  }

  private static SectionContainer generateContent(AppContext context)
  {
    return SectionContainer
        .builder()
        .section(PrimarySection
                     .builder()
                     .contentId("search-bar")
                     .component(
                         SearchBarComponent
                             .builder()
                             .placeholder(
                                 "Search product")
                             .contentId(
                                 "search-bar-element")
                             .build())
                     .build())
        .build();
  }
}
