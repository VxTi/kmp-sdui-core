package nl.q42.sdui.component.section;

import lombok.experimental.SuperBuilder;
import nl.q42.core.content.LoadComponents;
import nl.q42.sdui.component.AbstractComponent;
import org.springframework.lang.Nullable;

@SuperBuilder
public class InfoSection extends AbstractSection
{

  public final     String              type = "INFO";
  public           AbstractComponent[] components;
  public @Nullable LoadComponents      nextPage;
  public           boolean             isUsingSpacerComponents;
  //public @Nullable PlainButton button;

}
