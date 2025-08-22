package nl.q42.server.routes;

import nl.q42.sdui.screen.AbstractScreen;
import nl.q42.sdui.screen.OnboardingScreen;
import nl.q42.server.BasicClientRequest;
import nl.q42.server.BasicScreenResponse;
import nl.q42.server.IScreenRoute;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnboardingScreenRoute implements IScreenRoute
{

    @GetMapping(
            path = "/onboarding",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BasicScreenResponse handle(@RequestAttribute("request") BasicClientRequest request)
    {
        AbstractScreen screen;

        switch (request.body) {
            default:
                screen = OnboardingScreen.create(request.context);
                break;
        }

        return new BasicScreenResponse(screen, request.context);
    }
}
