package nl.q42.server.middleware;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import nl.q42.core.Locale;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j(topic = "App Locale Middleware")
public class AppLocaleMiddleware implements HandlerInterceptor
{

  static final Locale DEFAULT_LOCALE = Locale.NL_NL;

  @Override
  public boolean preHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler
  )
  {
    var appLocale = Locale.from(
        request.getHeader(MiddlewareConfiguration.HEADER_APP_LOCALE),
        DEFAULT_LOCALE
    );

    request.setAttribute(MiddlewareConfiguration.ATTRIB_LOCALE, appLocale);

    return true;
  }
}
