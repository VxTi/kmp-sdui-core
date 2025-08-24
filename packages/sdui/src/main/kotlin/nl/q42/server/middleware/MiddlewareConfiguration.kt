package nl.q42.server.middleware

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class MiddlewareConfiguration : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry
            .addInterceptor(AppLocaleMiddleware())
            .order(0)
        registry
            .addInterceptor(AppVersionMiddleware())
            .order(1)
        registry
            .addInterceptor(AppContextMiddleware())
            .order(2)
    }

    companion object {
        const val ATTRIB_LOCALE: String = "locale"
        const val ATTRIB_APP_VERSION: String = "appVersion"
        const val ATTRIB_APP_CONTEXT: String = "context"
        const val ATTRIB_REQUIRES_APP_REVALIDATION: String = "requiresRevalidation"

        const val HEADER_APP_VERSION: String = "X-App-Version"
        const val HEADER_APP_LOCALE: String = "X-App-Locale"
        const val HEADER_APP_IDENTITY: String = "X-App-Identity"
    }
}
