package nl.q42.common.components;

public enum TextFormatting
{
  NORMAL,
  BOLD,
  ITALIC,
  UNDERLINE;

  public TextFormatting from(int value)
  {
    var values      = TextFormatting.values();
    var withinRange = value >= 0 && value < values.length;

    return !withinRange
           ? NORMAL : values[value];
  }
}
