package nl.q42.server.middleware;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import nl.q42.core.Locale;
import nl.q42.core.RequestContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j(topic = "App Initiation Delegate")
@Component
public class AppInitiationMiddleware implements HandlerInterceptor
{

  @Override
  public boolean preHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler
  )
  {
    var locale      = (Locale) request.getAttribute(MiddlewareConfiguration.ATTRIB_LOCALE);
    var version     = (Integer) request.getAttribute(MiddlewareConfiguration.ATTRIB_APP_VERSION);
    var appIdentity = request.getHeader(MiddlewareConfiguration.HEADER_APP_IDENTITY);

    var requiresAppRevalidation = appIdentity == null;

    var context = new RequestContext(locale, version, requiresAppRevalidation);

    request.setAttribute(MiddlewareConfiguration.ATTRIB_APP_CONTEXT, context);

    return true;
  }
}