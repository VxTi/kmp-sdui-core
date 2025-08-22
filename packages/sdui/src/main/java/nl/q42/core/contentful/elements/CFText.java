package nl.q42.core.contentful.elements;

import lombok.AllArgsConstructor;
import nl.q42.core.contentful.interfaces.CFTextElement;
import org.springframework.lang.Nullable;

@AllArgsConstructor
public class CFText implements CFTextElement
{
  public final     String  type = "text";
  public final     String  value;
  public @Nullable Boolean isBold;
  public @Nullable Boolean isItalic;
  public @Nullable Boolean isUnderline;
}
