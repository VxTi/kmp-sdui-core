package nl.q42.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface VersionDependable {
    int since() default 1;

    /**
     * Specifies the version of the application up to which the annotated element is applicable.
     * E.g., whenever a component is marked with {@link VersionDependable} and the {@code since}}
     * value is set to `3`, applications instantiated with version 4 will not be able to receive said
     * component.
     *
     * @return The maximum version number (inclusive) for which the annotated element is valid.
     * The default value is {@link Integer#MAX_VALUE}, indicating no upper limit.
     */
    int until() default Integer.MAX_VALUE;
}
