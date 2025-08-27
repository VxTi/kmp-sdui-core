package nl.q42.server.middleware

import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import nl.q42.common.RequestHeader
import nl.q42.common.core.AppIdentity
import nl.q42.common.core.Locale
import nl.q42.core.RequestContext
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.io.IOException

@Component
class AppContextMiddleware : HandlerInterceptor {

    @Throws(ServletException::class, IOException::class)
    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        val locale = request.getAttribute(RequestHeader.ATTRIB_LOCALE) as Locale
        val version =
            request.getAttribute(RequestHeader.ATTRIB_APP_VERSION) as Int?
        val appIdentity = request.getHeader(RequestHeader.HEADER_APP_IDENTITY)

        if (version == null || appIdentity == null) return false;

        val computedIdentity = AppIdentity.calculate(locale, version);

        if (appIdentity != computedIdentity) {
            println("Identity mismatch: $appIdentity != $computedIdentity")
            return false;
        }

        val context = RequestContext(locale, version)

        request.setAttribute(RequestHeader.ATTRIB_APP_CONTEXT, context)

        println("Incoming request: ${request.requestURI}")

        return true
    }

    companion object {
        private val log = LoggerFactory.getLogger(AppContextMiddleware::class.java)
    }
}