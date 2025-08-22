package nl.q42.core.contentful.source;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CFEinsteinRecommendations implements CFProductCarouselSource
{
  public final String type = "einstein-recommendations";
  public final String recommenderName;
}
