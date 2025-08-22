package nl.q42.core.contentful;

import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;

@AllArgsConstructor
public class CFInfoMessageContent
{
  public final     String              type = "info-message";
  public           String              id;
  public @Nullable String              title;
  public @Nullable String              message;
  public @Nullable CFButtonLinkContent button;
}
