package nl.q42.server.routes;

import lombok.extern.slf4j.Slf4j;
import nl.q42.sdui.screen.CategoriesScreen;
import nl.q42.server.BasicClientRequest;
import nl.q42.server.BasicScreenResponse;
import nl.q42.server.IScreenRoute;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CategoriesScreenRoute implements IScreenRoute
{

    @GetMapping(
            path = "/categories",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BasicScreenResponse handle(@RequestAttribute("request")BasicClientRequest request)
    {
        return new BasicScreenResponse(CategoriesScreen.create(request.context), request.context);
    }
}
