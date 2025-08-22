package nl.q42.core.contentful;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import nl.q42.core.content.ContentItem;
import nl.q42.core.contentful.interfaces.CFContentItem;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class CFContentCardContent implements CFContentItem, ContentItem
{

  public @Builder.Default String type = "content-card";
  public                  String id;
  public @Nullable        String imageUrl;
  public                  String title;
  public                  String body;
}
