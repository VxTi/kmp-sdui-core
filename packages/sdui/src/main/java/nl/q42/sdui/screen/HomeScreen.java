package nl.q42.sdui.screen;

import nl.q42.core.RequestContext;
import nl.q42.sdui.component.Component;
import nl.q42.sdui.component.TextComponent;
import nl.q42.sdui.screen.common.Screen;

import java.util.List;

public class HomeScreen extends Screen
{
  public HomeScreen(RequestContext context, String screenName)
  {
    super(context, screenName);
  }

  @Override
  public List<Component> getContent()
  {
    // Return a list of text components
    return List.of(new TextComponent[]{new TextComponent("Hello world!", "test-1")});
  }
}
