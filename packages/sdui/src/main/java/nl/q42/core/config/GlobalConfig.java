package nl.q42.core.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import nl.q42.core.HemaWebEnvironment;
import nl.q42.core.config.server.ServerConfig;

@AllArgsConstructor
@Builder
public class GlobalConfig extends AbstractConfiguration
{
    public ApiConfig     api;
    public ContentConfig content;
    public CoreConfig    core;
    public ServerConfig  server;

    public static final GlobalConfig defaultConfig;

    static
    {
        ServerConfig server = ServerConfig
                .builder()
                .debugErrorResponse(
                        getSysEnv("debug_error_response", false))
                .nonPrimaryExternalServiceTimeoutMs(getSysEnv(
                        "non_primary_external_service_timeout_ms", 2500))
                .build();

        CoreConfig core = CoreConfig
                .builder()
                .appUrlScheme(getSysEnv("app_url_scheme", null))
                .webBasicAuthUser(getSysEnv("web_basic_auth_username", null))
                .webBasicAuthPassword(
                        getSysEnv("web_basic_auth_password", null))
                .webEnvironment(
                        HemaWebEnvironment.from(getSysEnv("web_env", "prod")))
                .staticFilesRootUrl(
                        "https://" + getSysEnv("static_files_hostname", null))
                .build();

        ApiConfig apiConfig = ApiConfig
                .builder()
                .authEndpoint(getSysEnv("api_auth_endpoint"))
                .apiEndpoint(getSysEnv("app_url_scheme"))
                .businessApiEndpoint(getSysEnv("api_business_endpoint"))
                .businessApiEndpointNew(// Allowed to be null
                                        getSysEnv("api_business_endpoint_new", null))
                .promotionsCatalogApiEndpoint(
                        getSysEnv("api_promotions_catalog_endpoint"))
                .promotionBannerGeneratorBaseURL(
                        getSysEnv("promotion_banner_generator_baseURL"))
                .client_id_system(getSysEnv("oauth_client_id_system"))
                .client_secret_system(
                        getSysEnv("oauth_client_secret_system"))
                .client_id_android(getSysEnv("oauth_client_id_android"))
                .client_secret_android(
                        getSysEnv("oauth_client_secret_android"))
                .client_id_ios(getSysEnv("oauth_client_id_ios"))
                .client_secret_ios(getSysEnv("oauth_client_secret_ios"))
                .build();

        defaultConfig = GlobalConfig
                .builder()
                .api(apiConfig)
                .core(core)
                .server(server)
                .build();
    }
}
