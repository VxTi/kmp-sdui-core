package nl.q42.core.contentful.elements;

import nl.q42.core.contentful.interfaces.CFContentElement;
import org.springframework.lang.Nullable;

import java.util.stream.Stream;

public class CFTable implements CFContentElement
{
  private static final String[] headerStyles = new String[]{"first-row", "first-column"};

  public final     String       type = "table";
  public           CFTableRow[] rows;
  public @Nullable String       headerStyle;

  public CFTable(CFTableRow[] rows, @Nullable String headerStyle)
  {
    if (headerStyle != null && Stream.of(headerStyles).noneMatch(headerStyle::equals))
      throw new IllegalArgumentException("Invalid headerStyle: " + headerStyle);

    this.rows        = rows;
    this.headerStyle = headerStyle;
  }
}
