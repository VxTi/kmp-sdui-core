package nl.q42.sdui.event;

import lombok.AllArgsConstructor;
import nl.q42.sdui.event.sideeffects.SideEffect;

@AllArgsConstructor
public class FirebaseCustomEvent implements SideEffect, AnalyticsEvent
{
  public final String type = "FIREBASE_CUSTOM";
  public final String name;
  public final Object parameters;
}
