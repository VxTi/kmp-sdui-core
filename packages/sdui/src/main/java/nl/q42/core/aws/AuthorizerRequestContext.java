package nl.q42.core.aws;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorizerRequestContext
{
  public           String principalId;
  public @Nullable String scope;
  public @Nullable String clientId;
  public @Nullable String hemaId;
  public @Nullable String customerId;
  public @Nullable String customerNumber;
}
