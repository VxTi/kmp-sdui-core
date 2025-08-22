package nl.q42.core.content.overlay;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import nl.q42.sdui.action.AbstractAction;
import nl.q42.sdui.component.image.RemoteImage;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@RequiredArgsConstructor
public class OverlayImage implements OverlayItem
{
    public final     String         type = "'OVERLAY_IMAGE'";
    public           RemoteImage    image;
    public @Nullable AbstractAction action;
}
