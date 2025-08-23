package nl.q42.server.routes;

import lombok.extern.slf4j.Slf4j;
import nl.q42.core.RequestContext;
import nl.q42.core.exceptions.ScreenNotFoundException;
import nl.q42.sdui.SDUIApplication;
import nl.q42.sdui.ScreenRegistry;
import nl.q42.server.middleware.MiddlewareConfiguration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j(topic = "Application Initiation Route")
public class ScreenRoute {
    public static final String ROUTE = "/screen";
    public static final String SEARCH_PARAM_SCREEN_IDENTIFIER = "id";

    private final ScreenRegistry registry;

    public ScreenRoute(ScreenRegistry registry) {
        this.registry = registry;

        for (var screen : registry.getAll()) {
            log.info("Found screen: {}", screen.name);
        }
    }

    @GetMapping(
            path = ROUTE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SDUIApplication handler(
            @RequestAttribute(MiddlewareConfiguration.ATTRIB_APP_CONTEXT) RequestContext context,
            @RequestParam(SEARCH_PARAM_SCREEN_IDENTIFIER) String screenIdentifier
    ) throws ScreenNotFoundException {
        if (screenIdentifier.isEmpty()) {
            log.warn("Screen identifier is missing");
            return null;
        }

        log.info("Retrieving screen with identifier: {}", screenIdentifier);

        var screen = registry.getByIdentifier(screenIdentifier);

        if (screen == null) {
            throw new ScreenNotFoundException(
                    String.format(
                            "Unable to retrieve screen with identifier %s",
                            screenIdentifier
                    ));
        }

        return SDUIApplication.builder().screen(screen).build();
    }
}
