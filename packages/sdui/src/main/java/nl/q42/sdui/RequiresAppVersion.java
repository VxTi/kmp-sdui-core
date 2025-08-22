package nl.q42.sdui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresAppVersion
{
    int FUTURE_VERSION = -1;

    /**
     * The version that is required for this feature.
     * When left empty, or set to -1, the feature is always available.
     *
     * @return The version that is required for this feature.
     */
    int value() default FUTURE_VERSION;

    /**
     * The name of the feature.
     * This is used to identify the feature in <code>AppFeatures</code>
     *
     * @return The name of the feature.
     */
    String featureName() default "";
}
