package nl.q42.core.aws;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HttpLambdaContext
{
  public String awsRequestId;
  public String functionName;
}
