package nl.q42.common.components;

public enum TextColor
{
  PRIMARY,
  SECONDARY,
  TERTIARY,
  DISABLED,
  ERROR,
  SUCCESS,
  INFO,
  CUSTOM;

  @Override
  public String toString()
  {
    return String.format("%d", this.ordinal());
  }

  public static TextColor from(int value)
  {
    var values = TextColor.values();
    var withinRange = value >= 0 && value < values.length;

    return !withinRange
           ? PRIMARY : values[value];
  }
}
