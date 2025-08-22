package nl.q42.sdui.component;

import java.io.Serializable;

public abstract class Component implements Serializable
{
  public final String contentId;
  public final String type;

  public Component(String type, String contentId)
  {
    this.type      = type;
    this.contentId = contentId;
  }
}
