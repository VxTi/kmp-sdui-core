package nl.q42.core.content;

import java.util.stream.Stream;

public record LoadComponents(String url, String uniqueId, String displayStyle)
{
  private static final String[] displayStyles = new String[]
      {"SHIMMER_PLP_ITEMS", "SHIMMER_REVIEW_DETAIL", "SHIMMER_ORDER_HISTORY_OVERVIEW"};

  public LoadComponents
  {

    if (Stream.of(displayStyles).noneMatch(displayStyle::equals))
    {
      throw new IllegalArgumentException("Invalid display style: " + displayStyle);
    }

  }
}
