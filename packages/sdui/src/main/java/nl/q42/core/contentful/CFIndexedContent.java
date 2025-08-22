package nl.q42.core.contentful;

import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
public class CFIndexedContent<T>
{
  public String               id;
  public CFInspectUrlResult[] inspectUrls;
  public T                    content;
}
