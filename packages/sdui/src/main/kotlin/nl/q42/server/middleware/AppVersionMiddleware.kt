package nl.q42.server.middleware

import com.google.gson.Gson
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.extern.slf4j.Slf4j
import nl.q42.sdui.SDUIApplication
import nl.q42.server.ErrorResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Slf4j(topic = "App Initiation Delegate")
@Component
class AppVersionMiddleware : HandlerInterceptor {
    override fun preHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any
    ): Boolean {
        val rawVersion = request.getHeader(MiddlewareConfiguration.Companion.HEADER_APP_VERSION)

        if (rawVersion == null) return false

        try {
            val version = rawVersion.toInt()

            if (version < SDUIApplication.Companion.MINIMUM_APP_VERSION || version > SDUIApplication.Companion.MAXIMUM_APP_VERSION) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
                response.getWriter().write(
                    Gson().toJson(
                        ErrorResponse(
                            String.format(
                                "Invalid app version provided. Please provide a " +
                                        "version between %d and %d",
                                SDUIApplication.Companion.MINIMUM_APP_VERSION,
                                SDUIApplication.Companion.MAXIMUM_APP_VERSION
                            )
                        )
                    )
                )

                return false
            }

            request.setAttribute(MiddlewareConfiguration.Companion.ATTRIB_APP_VERSION, version)
        } catch (e: Exception) {
            AppVersionMiddleware.log.warn("Invalid app version: {}", rawVersion)

            return false
        }

        return true
    }
}
