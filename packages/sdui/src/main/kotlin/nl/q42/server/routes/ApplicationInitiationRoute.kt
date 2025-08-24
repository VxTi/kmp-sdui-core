package nl.q42.server.routes

import nl.q42.common.RequestHeader
import nl.q42.common.ScreenResponse
import nl.q42.core.RequestContext
import nl.q42.sdui.ScreenRegistry
import nl.q42.server.middleware.MiddlewareConfiguration
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RestController

@RestController
class ApplicationInitiationRoute(private val registry: ScreenRegistry) {

    @GetMapping(
        path = [ROUTE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun handler(
        @RequestAttribute(RequestHeader.ATTRIB_APP_CONTEXT) context: RequestContext
    ): ScreenResponse? {
        log.info(
            "Application initiated - Locale: {} - Version: {}", context.locale,
            context.appVersion,
        )

        val screen = registry.defaultScreen();

        return ScreenResponse(screen, tabs = ScreenRegistry.SCREEN_TABS);
    }

    companion object {
        const val ROUTE: kotlin.String = "/"

        private val log = LoggerFactory.getLogger(ApplicationInitiationRoute::class.java);
    }
}
