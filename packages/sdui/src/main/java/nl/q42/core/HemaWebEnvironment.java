package nl.q42.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HemaWebEnvironment
{
    INT("int"),
    PROD("prod");

    private final String value;

    /**
     * Returns the HemaWebEnvironment enum value for the given string.
     * If the string does not match any of the enum values, the default value is PROD.
     *
     * @param value the string value to convert to HemaWebEnvironment
     * @return the HemaWebEnvironment enum value for the given string
     */
    public static HemaWebEnvironment from(String value)
    {
        for (HemaWebEnvironment env : values())
        {
            if (env.value.equals(value))
            {
                return env;
            }
        }
        return PROD;
    }
}
