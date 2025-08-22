package nl.q42.core.app;

import nl.q42.sdui.RequiresAppVersion;

import java.util.Map;

public record AppFeatures(Map<String, Boolean> featureMap, int version)
{

  /**
   * Derive a new set of features from an existing set of features.
   *
   * @param features The existing features.
   * @param changes  The changes to apply.
   * @param version  The version of the new features.
   * @return The new features.
   */
  public static AppFeatures deriveFrom(AppFeatures features, Map<String, Boolean> changes, int version)
  {
    Map<String, Boolean> newFeatureMap = features.featureMap;
    newFeatureMap.putAll(changes);
    return new AppFeatures(newFeatureMap, version);
  }

  /**
   * Check if a feature is enabled, which is the case by default, unless
   * explicitly disabled in <code>featureMap</code>.
   *
   * @param features     The features to check.
   * @param featureClass The feature
   * @param <T>          The feature type.
   * @return True if the feature is enabled, false otherwise.
   */
  public <T> boolean isFeatureEnabled(AppFeatures features, Class<T> featureClass)
  {
    var featureAnnotation = featureClass.getAnnotation(RequiresAppVersion.class);
    if (featureAnnotation == null)
    {
      return false;
    }
    var featureName = featureAnnotation.featureName();
    if (featureName.isBlank())
      featureName = featureClass.getSimpleName();

    return !this.featureMap.containsKey(featureName) || features.featureMap.get(featureName);
  }

  /**
   * Check if a feature is supported.
   *
   * @param featureName The name of the feature.
   * @return True if the feature is supported, false otherwise.
   */
  public boolean isSupported(String featureName)
  {
    return this.featureMap.getOrDefault(featureName, false);
  }
}
