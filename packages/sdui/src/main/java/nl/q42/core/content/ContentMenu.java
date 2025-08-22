package nl.q42.core.content;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ContentMenu implements ContentItem
{
  public final String type = "menu";
  public final String id;
}
