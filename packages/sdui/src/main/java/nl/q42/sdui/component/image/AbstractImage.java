package nl.q42.sdui.component.image;

import lombok.AllArgsConstructor;
import nl.q42.sdui.RequiresAppVersion;
import org.springframework.lang.Nullable;

@RequiresAppVersion
@AllArgsConstructor
public class AbstractImage
{
  public final     String type;
  public @Nullable String alt = null;
}
