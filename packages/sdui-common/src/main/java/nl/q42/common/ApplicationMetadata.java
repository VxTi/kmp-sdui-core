package nl.q42.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public record ApplicationMetadata(Map<Type, Object> data) implements Serializable
{

  public static final Map<Type, Object> DEFAULT_VALUES;
  
  public ApplicationMetadata()
  {
    this(DEFAULT_VALUES);
  }
  
  static {
    DEFAULT_VALUES = new HashMap<>();
    DEFAULT_VALUES.put(Type.MAXIMIZE_BRIGHTNESS, false);
    DEFAULT_VALUES.put(Type.REFRESH_INTERVAL, -1); // Never refreshes
  }

  public enum Type
  {
    MAXIMIZE_BRIGHTNESS,
    REFRESH_INTERVAL;
  }
}
