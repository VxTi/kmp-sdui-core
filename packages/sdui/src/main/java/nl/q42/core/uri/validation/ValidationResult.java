package nl.q42.core.uri.validation;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class ValidationResult<T>
{
    public String type;

    @SuperBuilder
    public static class ValidResult<T> extends ValidationResult<T> {
        public @Builder.Default String type = "valid";
        public T message;
    }

    @SuperBuilder
    public static class InvalidResult<T> extends ValidationResult<T> {
        public @Builder.Default String type = "invalid";
        public String message;
    }
}
