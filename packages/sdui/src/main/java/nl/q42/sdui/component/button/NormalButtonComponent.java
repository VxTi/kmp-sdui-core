package nl.q42.sdui.component.button;

import lombok.Builder;
import lombok.experimental.SuperBuilder;
import nl.q42.sdui.action.AbstractAction;
import nl.q42.sdui.component.image.LocalImage;
import org.springframework.lang.Nullable;

@SuperBuilder
public class NormalButtonComponent extends AbstractButton
{

  public @Builder.Default
  final            String         type = "NORMAL_BUTTON_COMPONENT";
  public           DisplayType    displayType;
  public           String         title;
  public @Nullable LocalImage     icon;
  public @Nullable String[]       loadingIds;
  public @Nullable AbstractAction action;

  public enum DisplayType
  {
    BUY, PRIMARY, SECONDARY
  }
}
