package nl.q42.core.contentful;

import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;

@AllArgsConstructor
public class CFButtonLinkContent
{
    public final     String type = "button-link";
    public           String id;
    public           String title;
    public           String url;
    public @Nullable String iconUrl;
}
