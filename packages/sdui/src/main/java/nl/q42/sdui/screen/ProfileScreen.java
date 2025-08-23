package nl.q42.sdui.screen;

import org.springframework.stereotype.Component;

import nl.q42.common.screen.Screen;

@Component
public class ProfileScreen extends Screen
{

  public static final String SCREEN_NAME = "profile";

  public ProfileScreen()
  {
    super(SCREEN_NAME);
  }
}
