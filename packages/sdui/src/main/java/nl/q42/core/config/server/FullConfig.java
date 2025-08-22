package nl.q42.core.config.server;

import lombok.AllArgsConstructor;
import nl.q42.core.config.AbstractConfiguration;
import nl.q42.core.config.EnvironmentGeneratedConfig;

@AllArgsConstructor
public class FullConfig extends AbstractConfiguration
{
    public SecretsConfig              secrets;
    public AWSConfig                  server;
    public EnvironmentGeneratedConfig envGenerated;
}
