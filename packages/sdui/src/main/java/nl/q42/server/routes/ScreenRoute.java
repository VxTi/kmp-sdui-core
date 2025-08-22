package nl.q42.server.routes;

import lombok.extern.slf4j.Slf4j;
import nl.q42.core.RequestContext;
import nl.q42.core.exceptions.ScreenNotFoundException;
import nl.q42.sdui.SDUIApplication;
import nl.q42.sdui.screen.SDUIScreen;
import nl.q42.server.middleware.MiddlewareConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j(topic = "Application Initiation Route")
public class ScreenRoute
{
  public static final String ROUTE                          = "/screen";
  public static final String SEARCH_PARAM_SCREEN_IDENTIFIER = "id";

  @GetMapping(
      path = ROUTE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public SDUIApplication handler(
      @RequestAttribute(MiddlewareConfiguration.ATTRIB_APP_CONTEXT) RequestContext context,
      @RequestParam(SEARCH_PARAM_SCREEN_IDENTIFIER) String screenIdentifier
  ) throws ScreenNotFoundException
  {
    if (screenIdentifier.isEmpty())
    {
      log.warn("Screen identifier is missing");
      return null;
    }

    return SDUIScreen
        .tryGetById(screenIdentifier)
        .flatMap(sduiScreen -> sduiScreen.tryInstantiate(context))
        .map(screenInstance -> new SDUIApplication(context, screenInstance))
        .orElseThrow(() -> new ScreenNotFoundException(
            String.format(
                "Unable to retrieve screen with identifier %s",
                screenIdentifier
            ))
        );
  }
}
