package nl.q42.core.uri.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

@RequiredArgsConstructor
public abstract class SearchQueryValidator<T>
{

    public ParameterLocation in;
    public @Nullable String description;
    public String name;

    public static class OptionalStringValidator extends SearchQueryValidator<String>
    {
        public OptionalStringValidator(ParameterLocation in, String name, @Nullable String description)
        {
        }
    }
}
