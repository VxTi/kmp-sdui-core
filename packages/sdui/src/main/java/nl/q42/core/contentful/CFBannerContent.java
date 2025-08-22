package nl.q42.core.contentful;

import lombok.RequiredArgsConstructor;
import nl.q42.core.contentful.interfaces.CFContentItem;
import org.springframework.lang.Nullable;

@RequiredArgsConstructor
public class CFBannerContent implements CFContentItem
{
    public enum Size
    {
        SMALL,
        LARGE
    }

    public final     String   type = "banner";
    public           String   id;
    public           Size     size;
    public           String   imageUrl;
    public @Nullable String   imageTitle;
    public @Nullable String   displayTitle;
    public @Nullable String   displaySubtitle;
    public           String   accessibilityTitle;
    public @Nullable String   url;
    public @Nullable String[] visibleForSegments;
    public @Nullable String   imageOverlayTopLeftUrl;
    public @Nullable String   imageOverlayTopRightUrl;
    public @Nullable String   imageOverlayBottomLeftUrl;
    public @Nullable String   imageOverlayBottomRightUrl;

}
