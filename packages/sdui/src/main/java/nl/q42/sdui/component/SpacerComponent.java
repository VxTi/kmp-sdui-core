package nl.q42.sdui.component;

import lombok.Builder;
import lombok.experimental.SuperBuilder;
import nl.q42.sdui.RequiresAppVersion;

@RequiresAppVersion(27)
@SuperBuilder
public class SpacerComponent extends AbstractComponent
{
  public final                  int    size;
  public final @Builder.Default String type = "SPACER";
}
