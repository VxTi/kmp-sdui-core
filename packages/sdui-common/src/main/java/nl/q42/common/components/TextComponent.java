package nl.q42.common.components;

public class TextComponent extends Component
{

  public TextColor      color;
  public TextFormatting formatting;
  public String         text;

  public TextComponent(
      String text,
      TextColor color,
      TextFormatting formatting,

      String contentId)
  {
    super(ComponentTypes.TEXT, contentId);
    this.formatting = formatting;
    this.color      = color;
    this.text       = text;
  }

  public TextComponent(String text, String contentId)
  {
    this(text, TextColor.PRIMARY, TextFormatting.NORMAL, contentId);
  }
}
