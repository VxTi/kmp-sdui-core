package nl.q42.core.uri.validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

public class OpenApiParameterSchema
{

  public           Type     type;
  public @Nullable String[] enumerable;

  @Getter
  @RequiredArgsConstructor
  public enum Type
  {
    STRING("string"),
    BOOLEAN("boolean"),
    INTEGER("integer");
    private final String value;
  }
}
