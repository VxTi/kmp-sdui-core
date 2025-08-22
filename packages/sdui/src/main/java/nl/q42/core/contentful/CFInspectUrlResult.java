package nl.q42.core.contentful;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@RequiredArgsConstructor
public class CFInspectUrlResult
{
  public           String originalUrl;
  public @Nullable String inspectUrl;
}
