package nl.q42.core.contentful.elements;

import lombok.AllArgsConstructor;
import nl.q42.core.contentful.interfaces.CFTextElement;
import org.springframework.lang.Nullable;

@AllArgsConstructor
public class CFText implements CFTextElement
{
    public final String type = "text";
    public @Nullable Boolean isBold;
    public @Nullable Boolean isItalic;
    public @Nullable Boolean isUnderline;
    public final String value;
}
