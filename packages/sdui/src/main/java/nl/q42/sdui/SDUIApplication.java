package nl.q42.sdui;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Singular;
import nl.q42.common.MetadataType;
import nl.q42.common.actions.Action;
import nl.q42.common.analytics.AnalyticEvent;
import nl.q42.common.components.Component;
import nl.q42.sdui.screen.common.ScreenTab;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class SDUIApplication implements Serializable
{
  public static final int MINIMUM_APP_VERSION = 1;
  public static final int MAXIMUM_APP_VERSION = 1;

  public @Singular List<Action>        onLoadActions;
  public @Singular List<AnalyticEvent> onLoadAnalyticEvents;

  public final Component                 screen;
  public       ScreenTab[]               tabs;
  public       Map<MetadataType, Object> metadata;
}
