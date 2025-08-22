package nl.q42.core.config.server;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import nl.q42.core.HemaWebEnvironment;
import nl.q42.core.config.AbstractConfiguration;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class AWSConfig extends AbstractConfiguration
{
    public @Nullable String             debug_error_response;
    public           String             api_auth_endpoint;
    public           String             api_api_endpoint;
    public           String             api_business_endpoint;
    public @Nullable String             api_business_endpoint_new;
    public           String             api_promotions_catalog_endpoint;
    public           String             promotion_banner_generator_baseURL;
    public           String             web_attachment_prefix;
    public           HemaWebEnvironment web_env;
    public           String             contentful_environment;
    public           String             poeditor_project_id;
    public           String             deploy_aws_account;
    public           String             app_url_scheme;
    public @Nullable String             order_eventbus_sender_aws_account_id;
    public @Nullable String             non_primary_external_service_timeout_ms;
    public @Nullable String             kibana_dashboard_url;

    @AllArgsConstructor
    @Builder
    @RequiredArgsConstructor
    public static class Extended extends AbstractConfiguration
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
}
