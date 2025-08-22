package nl.q42.core.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import nl.q42.core.HemaWebEnvironment;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class CoreConfig extends AbstractConfiguration
{
  public           String             appUrlScheme;
  public           HemaWebEnvironment webEnvironment;
  public @Nullable String             webBasicAuthPassword;
  public @Nullable String             webBasicAuthUser;
  public           String             staticFilesRootUrl;
}
