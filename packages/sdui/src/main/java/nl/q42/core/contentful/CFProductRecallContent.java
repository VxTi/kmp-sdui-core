package nl.q42.core.contentful;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import nl.q42.core.contentful.interfaces.CFInspectableContent;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@RequiredArgsConstructor
public class CFProductRecallContent implements CFInspectableContent
{
  public static final String type = "product-recall";
  public              String id;
  public @Nullable    String name;
  public @Nullable    String articleNumberDescription;
}
