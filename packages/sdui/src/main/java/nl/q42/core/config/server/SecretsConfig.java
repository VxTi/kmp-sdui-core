package nl.q42.core.config.server;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import nl.q42.core.config.AbstractConfiguration;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@RequiredArgsConstructor
public class SecretsConfig extends AbstractConfiguration
{
  public           String poeditor_api_token;
  public           String web_root_url;
  public           String oauth_client_id_ios;
  public           String oauth_client_secret_ios;
  public           String oauth_client_id_android;
  public           String oauth_client_secret_android;
  public           String oauth_client_id_system;
  public           String oauth_client_secret_system;
  public           String contentful_space_id;
  public           String contentful_delivery_api_key;
  public @Nullable String web_basic_auth_username;
  public @Nullable String web_basic_auth_password;
}
