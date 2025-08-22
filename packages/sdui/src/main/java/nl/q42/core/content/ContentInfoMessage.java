package nl.q42.core.content;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import nl.q42.core.Link;
import nl.q42.core.contentful.CFInfoMessageContent;
import org.springframework.lang.Nullable;

import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
public class ContentInfoMessage
{
  public           String            id;
  public @Nullable String            title;
  public @Nullable String            message;
  public @Nullable ContentButtonLink button;

  public static ContentInfoMessage from(
      CFInfoMessageContent content,
      List<Link> links)
  {
    return new ContentInfoMessage(
        content.id,
        content.title,
        content.message,
        content.button == null ? null : ContentButtonLink.from(content.button, links)
    );
  }
}
