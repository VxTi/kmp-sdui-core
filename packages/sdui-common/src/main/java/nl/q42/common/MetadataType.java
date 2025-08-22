package nl.q42.common;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MetadataType
{
  MAXIMIZE_BRIGHTNESS("maximizeBrightness"),
  REFRESH_INTERVAL("refreshInterval");

  public final String value;

  public static final Map<MetadataType, Object> DEFAULT_VALUES;

  MetadataType(String value)
  {
    this.value = value;
  }

  @Override
  public String toString()
  {
    return value;
  }

  public static Optional<MetadataType> from(String value)
  {
    for (MetadataType type : MetadataType.values())
    {
      if (type.value.equals(value))
      {
        return Optional.of(type);
      }
    }

    return Optional.empty();
  }

  static
  {
    DEFAULT_VALUES = new HashMap<>();
    DEFAULT_VALUES.put(MetadataType.MAXIMIZE_BRIGHTNESS, false);
    DEFAULT_VALUES.put(MetadataType.REFRESH_INTERVAL, -1); // Never refreshes
  }
}
