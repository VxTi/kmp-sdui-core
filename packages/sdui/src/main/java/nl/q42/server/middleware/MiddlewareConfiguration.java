package nl.q42.server.middleware;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MiddlewareConfiguration implements WebMvcConfigurer
{
  public static final String ATTRIB_LOCALE                    = "locale";
  public static final String ATTRIB_APP_VERSION               = "appVersion";
  public static final String ATTRIB_APP_CONTEXT               = "context";
  public static final String ATTRIB_REQUIRES_APP_REVALIDATION = "requiresRevalidation";

  public static final String HEADER_APP_VERSION = "X-App-Version";
  public static final String HEADER_APP_LOCALE = "X-App-Locale";
  public static final String HEADER_APP_IDENTITY = "X-App-Identity";

  @Override
  public void addInterceptors(InterceptorRegistry registry)
  {
    registry
        .addInterceptor(new AppLocaleMiddleware())
        .order(0);
    registry
        .addInterceptor(new AppVersionMiddleware())
        .order(1);
    registry
        .addInterceptor(new AppInitiationMiddleware())
        .order(2);
  }
}
