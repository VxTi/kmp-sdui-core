package nl.q42.core.contentful.elements;

import lombok.AllArgsConstructor;
import nl.q42.core.contentful.interfaces.CFContent;
import nl.q42.core.contentful.interfaces.CFListElementItemContent;
import nl.q42.core.contentful.interfaces.CFTextElement;
import org.springframework.lang.Nullable;

@AllArgsConstructor
public class CFParagraphElement implements CFTextElement, CFContent, CFListElementItemContent
{
    public final     String          type = "paragraph";
    public @Nullable CFTextElement[] content;
}
