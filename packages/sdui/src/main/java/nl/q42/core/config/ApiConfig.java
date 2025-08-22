package nl.q42.core.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.lang.Nullable;

@Builder
@AllArgsConstructor
public class ApiConfig extends AbstractConfiguration
{

  public           String     authEndpoint;
  public           String     apiEndpoint;
  public           String     businessApiEndpoint;
  public @Nullable String     businessApiEndpointNew;
  public           String     promotionsCatalogApiEndpoint;
  public           String     promotionBannerGeneratorBaseURL;
  public           String     client_id_android;
  public           String     client_secret_android;
  public           String     client_id_ios;
  public           String     client_secret_ios;
  public           String     client_id_system;
  public           String     client_secret_system;
  public           CoreConfig core;
}
