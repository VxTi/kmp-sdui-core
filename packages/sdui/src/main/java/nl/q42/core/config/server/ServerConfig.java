package nl.q42.core.config.server;

import lombok.AllArgsConstructor;
import lombok.Builder;
import nl.q42.core.config.AbstractConfiguration;

@AllArgsConstructor
@Builder
public class ServerConfig extends AbstractConfiguration
{
    public boolean debugErrorResponse;
    public int     nonPrimaryExternalServiceTimeoutMs;
}
