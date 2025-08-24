package nl.q42.server.middleware

import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.extern.slf4j.Slf4j
import nl.q42.core.Locale
import nl.q42.core.RequestContext
import nl.q42.server.routes.ApplicationInitiationRoute
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.io.IOException

@Slf4j(topic = "App Context Middleware")
@Component
class AppContextMiddleware : HandlerInterceptor {
    @Throws(ServletException::class, IOException::class)
    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        val locale = request.getAttribute(MiddlewareConfiguration.Companion.ATTRIB_LOCALE) as Locale
        val version =
            request.getAttribute(MiddlewareConfiguration.Companion.ATTRIB_APP_VERSION) as Int
        val appIdentity = request.getHeader(MiddlewareConfiguration.Companion.HEADER_APP_IDENTITY)

        val requiresAppRevalidation = appIdentity == null || !appIdentity.startsWith(locale.value)

        val context = RequestContext(locale, version, requiresAppRevalidation)

        request.setAttribute(MiddlewareConfiguration.Companion.ATTRIB_APP_CONTEXT, context)

        if (requiresAppRevalidation
            && !request.getRequestURI().startsWith(ApplicationInitiationRoute.Companion.ROUTE)
        ) {
            AppContextMiddleware.log.warn(
                "App revalidation required for request: {}",
                request.getRequestURI()
            )

            request.getRequestDispatcher(ApplicationInitiationRoute.Companion.ROUTE)
                .forward(request, response)

            return false
        }

        return true
    }
}