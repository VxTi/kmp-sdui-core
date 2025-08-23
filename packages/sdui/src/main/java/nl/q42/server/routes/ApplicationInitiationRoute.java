package nl.q42.server.routes;

import lombok.extern.slf4j.Slf4j;
import nl.q42.common.actions.NavigationAction;
import nl.q42.core.RequestContext;
import nl.q42.sdui.SDUIApplication;
import nl.q42.sdui.ScreenRegistry;
import nl.q42.sdui.screen.HomeScreen;
import nl.q42.server.middleware.MiddlewareConfiguration;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j(topic = "Application Initiation Route")
public class ApplicationInitiationRoute {

    public static final String ROUTE = "/";

    private final ScreenRegistry registry;

    public ApplicationInitiationRoute(ScreenRegistry registry) {
        this.registry = registry;
    }

    @GetMapping(
            path = ROUTE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public SDUIApplication handler(
            @RequestAttribute(MiddlewareConfiguration.ATTRIB_APP_CONTEXT) RequestContext context
    ) {
        log.info(
                "Application initiated - Locale: {} - Version: {} - Revalidate: {}", context.locale,
                context.appVersion,
                context.revalidateRequest
        );

        return SDUIApplication.builder()
                .tabs(ScreenRegistry.SCREEN_TABS)
                .screen(registry.getByIdentifier(HomeScreen.SCREEN_NAME))
                .onLoadAction(new NavigationAction("home", false))
                .build();
    }
}
