package nl.q42.core.content;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import nl.q42.core.content.overlay.OverlayItem;
import nl.q42.sdui.action.AbstractAction;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@NoArgsConstructor
public class FavoriteButton implements OverlayItem
{
  public final     String         type = "FAVORITE_BUTTON";
  public @Nullable AbstractAction favoriteAction;
  public @Nullable AbstractAction unFavoriteAction;
}
