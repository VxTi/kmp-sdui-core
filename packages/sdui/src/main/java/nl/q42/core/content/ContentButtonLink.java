package nl.q42.core.content;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import nl.q42.core.Link;
import nl.q42.core.contentful.CFButtonLinkContent;
import nl.q42.core.uri.LookupDestination;
import org.springframework.lang.Nullable;

import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
public class ContentButtonLink
{
  public final     String            id;
  public final     String            title;
  public @Nullable String            iconUrl;
  public @Nullable LookupDestination destination;

  public static ContentButtonLink from(
      CFButtonLinkContent content, List<Link> links)
  {
    return new ContentButtonLink(
        content.id,
        content.title,
        content.iconUrl,
        links.stream()
             .filter(link -> link.originalUrl.equals(content.url))
             .findFirst()
             .map(link -> link.destination)
             .orElse(null)
    );
  }
}
