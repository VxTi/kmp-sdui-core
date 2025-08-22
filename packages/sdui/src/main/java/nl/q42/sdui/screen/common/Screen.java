package nl.q42.sdui.screen.common;

import nl.q42.common.components.ComponentTypes;
import nl.q42.core.RequestContext;
import nl.q42.common.components.Component;

import java.io.Serializable;
import java.util.List;

public abstract class Screen extends Component implements Serializable
{
  protected transient final RequestContext context;

  public Screen(RequestContext context, String screenName)
  {
    super(screenName, ComponentTypes.SCREEN);
    this.context = context;
  }

  /**
   * This method returns the produced content by the screen.
   */
  public abstract List<Component> getContent();
}
