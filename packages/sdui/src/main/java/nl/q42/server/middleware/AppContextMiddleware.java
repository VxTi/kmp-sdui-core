package nl.q42.server.middleware;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import nl.q42.core.Locale;
import nl.q42.core.RequestContext;
import nl.q42.server.routes.ApplicationInitiationRoute;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Slf4j(topic = "App Context Middleware")
@Component
public class AppContextMiddleware implements HandlerInterceptor
{

  @Override
  public boolean preHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler
  ) throws ServletException, IOException
  {
    var locale      = (Locale) request.getAttribute(MiddlewareConfiguration.ATTRIB_LOCALE);
    var version     = (Integer) request.getAttribute(MiddlewareConfiguration.ATTRIB_APP_VERSION);
    var appIdentity = request.getHeader(MiddlewareConfiguration.HEADER_APP_IDENTITY);

    var requiresAppRevalidation = appIdentity == null || !appIdentity.startsWith(locale.value);

    var context = new RequestContext(locale, version, requiresAppRevalidation);

    request.setAttribute(MiddlewareConfiguration.ATTRIB_APP_CONTEXT, context);

    if (requiresAppRevalidation
        && !request.getRequestURI().startsWith(ApplicationInitiationRoute.ROUTE))
    {
      log.warn("App revalidation required for request: {}", request.getRequestURI());

      request.getRequestDispatcher(ApplicationInitiationRoute.ROUTE).forward(request, response);

      return false;
    }

    return true;
  }
}