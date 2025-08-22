package nl.q42.sdui.event;

import org.springframework.lang.Nullable;

import java.util.Map;

public class AirshipEvent extends BFFEvent
{
    public final String              type = "AIRSHIP_EVENT";
    public       String              name;
    public       Map<String, Object> properties;

    public AirshipEvent(String name, Map<String, Object> properties)
    {
        this.name       = name;
        this.properties = properties;
    }

    public static AirshipEvent screenView(String screen, @Nullable  Map<String, Object> properties)
    {
        if (properties == null)
        {
            properties = new java.util.HashMap<>();
        }
        properties.put("screen", screen);
        return new AirshipEvent("screen_view", properties);
    }
}
