package nl.q42.server.routes

import lombok.extern.slf4j.Slf4j
import nl.q42.common.RequestHeader
import nl.q42.common.ScreenResponse
import nl.q42.common.screen.Screen
import nl.q42.core.RequestContext
import nl.q42.core.exceptions.ScreenNotFoundException
import nl.q42.sdui.ScreenRegistry
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Slf4j(topic = "Application Initiation Route")
class ScreenRoute(private val registry: ScreenRegistry) {

    private val log = LoggerFactory.getLogger(ScreenRoute::class.java)

    @GetMapping(path = [ROUTE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @Throws(ScreenNotFoundException::class)
    fun handler(
        @RequestAttribute(RequestHeader.ATTRIB_APP_CONTEXT) context: RequestContext?,
        @RequestParam(SEARCH_PARAM_SCREEN_IDENTIFIER) screenIdentifier: String
    ): ScreenResponse? {
        if (screenIdentifier.isEmpty()) {
            log.warn("Screen identifier is missing")
            return null
        }

        log.info("Incoming screen request for ID \"{}\"", screenIdentifier)

        val screen: Screen? = registry.getByIdentifier(screenIdentifier)

        if (screen == null) {
            throw ScreenNotFoundException(
                String.format(
                    "Unable to retrieve screen with identifier %s",
                    screenIdentifier
                )
            )
        }

        return ScreenResponse(screen);
    }

    companion object {
        const val ROUTE: String = "/screen"
        const val SEARCH_PARAM_SCREEN_IDENTIFIER: String = "id"
    }
}
