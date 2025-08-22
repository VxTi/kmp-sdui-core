package nl.q42.core.contentful;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import nl.q42.core.content.ContentItem;
import nl.q42.core.contentful.interfaces.CFContentItem;
import nl.q42.core.support.SupportedCountry;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@RequiredArgsConstructor
public class CFUSPContent implements CFContentItem, ContentItem
{
    public final     String           type = "usp";
    public           String           id;
    public           SupportedCountry country;
    public @Nullable String           categoryId;
    public @Nullable String[]         usps;
}
