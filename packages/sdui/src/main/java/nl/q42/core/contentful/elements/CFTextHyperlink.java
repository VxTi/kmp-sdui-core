package nl.q42.core.contentful.elements;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

@RequiredArgsConstructor
@AllArgsConstructor
public class CFTextHyperlink
{
    public final String type = "hyperlink";
    public @Nullable String url;
    public final String value;
}
