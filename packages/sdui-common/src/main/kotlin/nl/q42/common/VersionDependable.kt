package nl.q42.common

@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
annotation class VersionDependable(
    val since: Int = 1,
    /**
     * Specifies the version of the application up to which the annotated element is applicable.
     * E.g., whenever a component is marked with [VersionDependable] and the `since`}
     * value is set to `3`, applications instantiated with version 4 will not be able to receive said
     * component.
     *
     * @return The maximum version number (inclusive) for which the annotated element is valid.
     * The default value is [Integer.MAX_VALUE], indicating no upper limit.
     */
    val until: Int = Int.Companion.MAX_VALUE
)
