package nl.q42.core.uri.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class OpenApiParameter
{
  public           String                 name;
  public @Nullable String                 description;
  public           ParameterLocation      in;
  public           boolean                required;
  public           OpenApiParameterSchema schema;
}
