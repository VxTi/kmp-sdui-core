package nl.q42.core.contentful.elements;

import lombok.AllArgsConstructor;
import nl.q42.core.contentful.interfaces.CFContentElement;
import nl.q42.core.contentful.interfaces.CFListElementItemContent;

@AllArgsConstructor
public class CFListElement implements CFListElementItemContent, CFContentElement
{
  public final String              type = "list";
  public final CFListElementItem[] items;
  public final ListType            listType;

  public enum ListType
  {
    ORDERED,
    UNORDERED
  }
}
