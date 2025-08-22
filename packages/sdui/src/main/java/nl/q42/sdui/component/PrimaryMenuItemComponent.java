package nl.q42.sdui.component;

import lombok.Builder;
import lombok.experimental.SuperBuilder;
import nl.q42.sdui.RequiresAppVersion;
import nl.q42.sdui.action.AbstractAction;
import nl.q42.sdui.component.image.AbstractImage;
import org.springframework.lang.Nullable;

@RequiresAppVersion(24)
@SuperBuilder
public class PrimaryMenuItemComponent extends AbstractComponent
{
  public final @Builder.Default String         type = "PRIMARY_MENU_ITEM";
  @Builder.Default
  @Nullable
  public                        String[]       tags = new String[0];
  public                        String         title;
  public @Nullable              String         subtitle;
  public                        AbstractImage  image;
  public @Nullable              AbstractAction action;
}
