package nl.q42.server.routes;

import nl.q42.sdui.screen.AccountMenuScreen;
import nl.q42.server.BasicClientRequest;
import nl.q42.server.IScreenRoute;
import nl.q42.server.ScreenResponseWithCache;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountMenuScreenRoute implements IScreenRoute
{
  @GetMapping(
      path = "/account-menu",
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ScreenResponseWithCache getAccountScreen(@RequestAttribute("request") BasicClientRequest request)
  {
    return new ScreenResponseWithCache(
        AccountMenuScreen.create(request.context), null, request.context);
  }
}
