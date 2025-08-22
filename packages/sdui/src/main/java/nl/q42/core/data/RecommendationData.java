package nl.q42.core.data;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RecommendationData
{
  public String   recommenderName;
  public String   __recoUUID;
  public String[] productIds;
}
