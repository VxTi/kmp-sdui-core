package nl.q42.core.uri;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class SearchQueryParams
{
  public @Nullable String term;
  public @Nullable String categoryId;
  public @Nullable String disableOutOfStockFallback;
  public @Nullable String sort;

}
