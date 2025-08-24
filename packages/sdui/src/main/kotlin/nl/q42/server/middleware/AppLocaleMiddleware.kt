package nl.q42.server.middleware

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.extern.slf4j.Slf4j
import nl.q42.core.Locale
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
@Slf4j(topic = "App Locale Middleware")
class AppLocaleMiddleware : HandlerInterceptor {
    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        val appLocale: Locale? = Locale.Companion.from(
            request.getHeader(MiddlewareConfiguration.Companion.HEADER_APP_LOCALE),
            DEFAULT_LOCALE
        )

        request.setAttribute(MiddlewareConfiguration.Companion.ATTRIB_LOCALE, appLocale)

        return true
    }

    companion object {
        val DEFAULT_LOCALE: Locale = Locale.NL_NL
    }
}
