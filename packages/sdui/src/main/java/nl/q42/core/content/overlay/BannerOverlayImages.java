package nl.q42.core.content.overlay;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import nl.q42.sdui.component.image.RemoteImage;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@NoArgsConstructor
public class BannerOverlayImages
{
    public @Nullable RemoteImage topLeftImage;
    public @Nullable RemoteImage topRightImage;
    public @Nullable RemoteImage bottomLeftImage;
    public @Nullable RemoteImage bottomRightImage;
}
