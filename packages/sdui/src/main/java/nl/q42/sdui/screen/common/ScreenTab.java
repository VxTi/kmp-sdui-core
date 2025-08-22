package nl.q42.sdui.screen.common;

import nl.q42.common.components.ComponentTypes;
import nl.q42.sdui.component.Component;

public class ScreenTab extends Component
{
  public final String title;
  public final String imageUrl;

  public ScreenTab(String title, String imageUrl, String contentId)
  {
    super(ComponentTypes.SCREEN_TAB, contentId);
    this.title    = title;
    this.imageUrl = imageUrl;
  }
}
