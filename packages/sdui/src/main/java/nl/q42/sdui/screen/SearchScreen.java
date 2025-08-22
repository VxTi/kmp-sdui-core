package nl.q42.sdui.screen;

import nl.q42.core.RequestContext;
import nl.q42.sdui.component.Component;
import nl.q42.sdui.screen.common.Screen;

import java.util.List;

public class SearchScreen extends Screen
{
  public SearchScreen(RequestContext context, String screenName)
  {
    super(context, screenName);
  }

  @Override
  public List<Component> getContent()
  {
    return List.of();
  }
}
