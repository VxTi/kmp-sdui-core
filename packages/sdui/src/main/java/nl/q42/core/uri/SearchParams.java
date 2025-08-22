package nl.q42.core.uri;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

import java.util.Map;

@AllArgsConstructor
@SuperBuilder
@RequiredArgsConstructor
public class SearchParams extends SearchQueryParams
{
  public @Nullable Map<String, String[]> filters;

}
