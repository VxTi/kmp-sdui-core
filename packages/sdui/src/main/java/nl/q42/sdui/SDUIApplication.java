package nl.q42.sdui;

import com.fasterxml.jackson.annotation.JsonInclude;
import nl.q42.common.MetadataType;
import nl.q42.core.RequestContext;
import nl.q42.sdui.component.Component;

import java.io.Serializable;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SDUIApplication implements Serializable
{
  public static final int MINIMUM_APP_VERSION = 1;
  public static final int MAXIMUM_APP_VERSION = 1;

  public final Component                 screen;
  public       Component[]               tabs;
  public       Map<MetadataType, Object> metadata;

  public SDUIApplication(RequestContext context, Component screen, Component[] tabs)
  {
    this.screen   = screen;
    this.tabs     = tabs;
    this.metadata = MetadataType.DEFAULT_VALUES;
  }

  public SDUIApplication(RequestContext context, Component screen)
  {
    this(context, screen, null);
  }
}
