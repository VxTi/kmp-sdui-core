package nl.q42.sdui;

public @interface ComponentName
{
    /**
     * The value of the content type.
     * This determines the type of content that is being shown.
     *
     * @return The value of the content type.
     */
    String value() default "";
}
