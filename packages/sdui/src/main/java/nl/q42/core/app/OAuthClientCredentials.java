package nl.q42.core.app;

import lombok.AllArgsConstructor;
import nl.q42.core.config.ApiConfig;

@AllArgsConstructor
public class OAuthClientCredentials
{
    public String client_id;
    public String client_secret;

    public enum ClientOrigin
    {
        ANDROID,
        IOS,
        SYSTEM
    }

    /**
     * Create a new OAuthClientCredentials object from the given origin and config.
     *
     * @param origin The origin of the client.
     * @param config The configuration object.
     * @return The OAuthClientCredentials object.
     */
    public static OAuthClientCredentials fromOrigin(ClientOrigin origin, ApiConfig config)
    {
        return switch ( origin )
        {
            case ANDROID -> new OAuthClientCredentials(
                    config.client_id_android, config.client_secret_android);
            case IOS -> new OAuthClientCredentials(config.client_id_ios, config.client_secret_ios);
            case SYSTEM -> new OAuthClientCredentials(
                    config.client_id_system, config.client_secret_system);
        };
    }
}
