package nl.q42.sdui.screen;

import lombok.Builder;
import lombok.experimental.SuperBuilder;
import nl.q42.sdui.AbstractContent;
import nl.q42.sdui.component.section.ScreenTab;
import org.springframework.lang.Nullable;

@SuperBuilder
public class ScreenWithTabs extends AbstractScreen
{
  public final @Builder.Default String      type = "TABS";
  public                        ScreenTab[] tabs;
  public                        String      initialTabId;

  @Nullable
  @Override
  public AbstractContent getElementById(String contentId)
  {
    for (ScreenTab tab : tabs)
      for (AbstractContent element : tab.content.sections)
        if (element.contentId.equals(contentId))
          return element;
    return null;
  }
}
