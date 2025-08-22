package nl.q42.server;

import lombok.AllArgsConstructor;
import nl.q42.core.app.AppContext;
import nl.q42.core.config.GlobalConfig;
import org.springframework.lang.Nullable;

import java.util.Map;

@AllArgsConstructor
public class BasicClientRequest
{
  public final     String              body;
  public final     Map<String, String> headers;
  public final     AppContext          context;
  public final     GlobalConfig        config = GlobalConfig.defaultConfig;
  public @Nullable String              contentPreviewToken;

}
