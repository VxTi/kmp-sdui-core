package nl.q42.core.content.overlay;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@NoArgsConstructor
public class Overlay
{
  public @Nullable OverlayItem topLeft;
  public @Nullable OverlayItem topRight;
  public @Nullable OverlayItem bottomLeft;
  public @Nullable OverlayItem bottomRight;
}
