package nl.q42.sdui;

import nl.q42.common.ApplicationMetadata;
import nl.q42.core.RequestContext;
import nl.q42.sdui.component.Component;

import java.io.Serializable;

public class SDUIApplication implements Serializable
{
  public static final int MINIMUM_APP_VERSION = 1;
  public static final int MAXIMUM_APP_VERSION = 1;

  public String name;

  public final Component           content;
  public Component[]         tabs = null;
  public ApplicationMetadata metadata;

  public SDUIApplication(RequestContext context, Component content, Component[] tabs)
  {
    this.name     = context.locale.value;
    this.content  = content;
    this.tabs     = tabs;
    this.metadata = new ApplicationMetadata();
  }

  public SDUIApplication(RequestContext context, Component content)
  {
    this(context, content, null);
  }
}
