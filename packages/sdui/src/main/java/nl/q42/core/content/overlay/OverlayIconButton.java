package nl.q42.core.content.overlay;

import lombok.AllArgsConstructor;
import nl.q42.sdui.action.AbstractAction;
import nl.q42.sdui.component.image.LocalImage;
import org.springframework.lang.Nullable;

@AllArgsConstructor
public class OverlayIconButton implements OverlayItem
{
  public final     String         type = "ICON_BUTTON";
  public final     LocalImage     icon;
  public @Nullable AbstractAction action;
  public           int            size = 0;
}
