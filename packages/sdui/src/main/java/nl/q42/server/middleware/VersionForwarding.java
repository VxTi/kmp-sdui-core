package nl.q42.server.middleware;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import nl.q42.core.app.AppContext;
import nl.q42.server.BasicClientRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j(topic = "App Version Forwarding")
@Component
public class VersionForwarding extends OncePerRequestFilter
{

  static final Pattern VERSION_PATTERN = Pattern.compile("^/(v\\d+|future)(/.*)$");

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException
  {

    Matcher matcher = VERSION_PATTERN.matcher(request.getRequestURI());
    if (!matcher.matches())
    {
      filterChain.doFilter(request, response);
      return;
    }

    int appVersion = matcher.group(1).equals("future") ? -1 :
                     Integer.parseInt(matcher.group(1).substring(1));

    String newUri = matcher.group(2);
    AppContext context = AppContext.builder()
                                   .version(appVersion)
                                   .build();

    log.info(
        "Headers: {}, body: {}", request.getHeaderNames(),
        request.getReader().lines().reduce("", String::concat)
    );

    Map<String, String> headers =
        Collections.list(request.getHeaderNames())
                   .stream()
                   .collect(Collectors.toMap(
                       name -> name,
                       request::getHeader
                   ));

    var body = request.getReader().lines().reduce("", String::concat);

    request.setAttribute("request", new BasicClientRequest(body, headers, context, null));
    request.getRequestDispatcher(newUri).forward(request, response);

    log.info(
        "Forwarding request to {} with version {}", newUri,
        appVersion == -1 ? "future" : appVersion
    );
  }
}