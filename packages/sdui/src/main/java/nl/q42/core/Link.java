package nl.q42.core;

import lombok.AllArgsConstructor;
import nl.q42.core.contentful.CFContentScreen;
import nl.q42.core.contentful.CFIndexedContent;
import nl.q42.core.contentful.CFInspectUrlResult;
import nl.q42.core.uri.LookupDestination;
import nl.q42.core.uri.LookupDestinationResolver;

import java.util.List;

@AllArgsConstructor
public class Link
{
  public String            originalUrl;
  public LookupDestination destination;

  public static List<Link> fromInspectResults(
      List<CFInspectUrlResult> inspectUrls,
      List<CFIndexedContent<CFContentScreen>> prefetchedScreens,
      HemaWebEnvironment env)
  {
    return inspectUrls
        .stream()
        .map(inspectUrl -> {
          var destination =
              LookupDestinationResolver.lookupDestinationFromUrl(
                  inspectUrl.originalUrl, inspectUrls, env);

          if (!destination.type.equals("content"))
            return new Link(inspectUrl.originalUrl, destination);

          var result = (LookupDestination.ContentScreen) destination;
          var screen = prefetchedScreens
              .stream()
              .filter(cfscreen ->
                          cfscreen.content.slug.equals(result.slug))
              .findFirst()
              .map(cfscreen -> cfscreen.content)
              .orElse(null);
          return new Link(
              inspectUrl.originalUrl,
              new LookupDestination.ContentScreen(
                  result.originalUrl,
                  result.locale,
                  result.slug,
                  screen
              )
          );
        })
        .toList();
  }
}
