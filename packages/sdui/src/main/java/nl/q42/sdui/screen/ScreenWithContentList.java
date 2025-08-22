package nl.q42.sdui.screen;

import lombok.experimental.SuperBuilder;
import nl.q42.sdui.AbstractContent;
import nl.q42.sdui.component.section.PrimarySection;
import nl.q42.sdui.component.section.SectionContainer;
import org.springframework.lang.Nullable;

@SuperBuilder
public class ScreenWithContentList extends AbstractScreen
{
  public final String           type = "COMPONENT";
  public final SectionContainer content;

  @Nullable
  @Override
  public AbstractContent getElementById(String contentId)
  {
    for (var section : content.sections)
    {
      if (section.contentId.equals(contentId))
        return section;

      if (section instanceof PrimarySection primarySection)
        for (var subComponent : primarySection.components)
          if (subComponent.contentId.equals(contentId))
            return subComponent;
    }
    return null;
  }
}
