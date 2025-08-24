package nl.q42.server.routes

import nl.q42.common.actions.NavigationAction
import nl.q42.core.RequestContext
import nl.q42.sdui.SDUIApplication
import nl.q42.sdui.ScreenRegistry
import nl.q42.sdui.screen.HomeScreen
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
        @RequestAttribute(MiddlewareConfiguration.ATTRIB_APP_CONTEXT) context: RequestContext
    ): SDUIApplication? {
        log.info(
            "Application initiated - Locale: {} - Version: {} - Revalidate: {}", context.locale,
            context.appVersion,
            context.revalidateRequest
        )

        return SDUIApplication(registry.getByIdentifier(HomeScreen.SCREEN_NAME))
            .tabs(ScreenRegistry.Companion.SCREEN_TABS)
            .screen(registry.getByIdentifier(HomeScreen.SCREEN_NAME))
            .onLoadAction(NavigationAction("home", false))
            .build()
    }

    companion object {
        const val ROUTE: kotlin.String = "/"

        private val log = LoggerFactory.getLogger(ApplicationInitiationRoute::class.java);
    }
}
