package nl.q42.core.contentful;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import nl.q42.core.contentful.interfaces.CFContent;
import nl.q42.core.contentful.interfaces.CFContentItem;
import nl.q42.core.contentful.interfaces.CFInspectableContent;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@RequiredArgsConstructor
public class CFRecallContent implements CFInspectableContent,
                                        CFContentItem
{
    public final     String                   type = "recall-content";
    public           String                   id;
    public @Nullable CFProductRecallContent[] products;
    public @Nullable CFContent                text;
    public @Nullable String                   url;
}
