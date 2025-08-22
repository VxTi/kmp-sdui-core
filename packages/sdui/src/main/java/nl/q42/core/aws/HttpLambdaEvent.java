package nl.q42.core.aws;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Map;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class HttpLambdaEvent
{

  public           String              path;
  public           String              httpMethod;
  public @Nullable Map<String, String> headers;
  public @Nullable Map<String, String> queryStringParameters;
  public @Nullable Map<String, String> pathParameters;
  public @Nullable Map<String, String> stageVariables;
  public @Nullable String              body;
  public           RequestContext      requestContext;

  public static class RequestContext
  {
    public String                   path;
    public String                   requestId;
    public long                     requestTimeEpoch;
    public String                   domainName;
    public AuthorizerRequestContext authorizer;
  }
}
