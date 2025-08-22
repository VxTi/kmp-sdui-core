package nl.q42.server.middleware;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import nl.q42.sdui.SDUIApplication;
import nl.q42.server.ErrorResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j(topic = "App Initiation Delegate")
@Component
public class AppVersionMiddleware implements HandlerInterceptor
{

  @Override
  public boolean preHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler
  )
  {
    var rawVersion = request.getHeader(MiddlewareConfiguration.HEADER_APP_VERSION);

    if (rawVersion == null) return false;

    try
    {
      var version = Integer.parseInt(rawVersion);

      if (version < SDUIApplication.MINIMUM_APP_VERSION || version > SDUIApplication.MAXIMUM_APP_VERSION)
      {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write(new Gson().toJson(new ErrorResponse(
            String.format(
                "Invalid app version provided. Please provide a " +
                "version between %d and %d",
                SDUIApplication.MINIMUM_APP_VERSION,
                SDUIApplication.MAXIMUM_APP_VERSION
            )
        )));

        return false;
      }

      request.setAttribute(MiddlewareConfiguration.ATTRIB_APP_VERSION, version);

    }
    catch (Exception e)
    {
      log.warn("Invalid app version: {}", rawVersion);

      return false;
    }

    return true;
  }
}
