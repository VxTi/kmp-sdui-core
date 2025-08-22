package nl.q42.core.contentful;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import nl.q42.core.contentful.interfaces.CFContentItem;
import org.springframework.lang.Nullable;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CFDonationBannerContent implements CFContentItem
{
  public @Builder.Default String type = "donation-banner";
  public @Nullable        String about;
  public @Nullable        String conditions;
  public                  String id;
  public                  String bannerImageUrl;
  public                  String mainImageUrl;
  public                  String charityName;
  public                  String charityId;
}
