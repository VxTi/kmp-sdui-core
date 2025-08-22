package nl.q42.core.config;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ContentfulConfig extends AbstractConfiguration
{
    public String spaceId;
    public String accessToken;
    public String environment;
}
