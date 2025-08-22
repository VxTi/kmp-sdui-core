package nl.q42.core.config;

import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public abstract class AbstractConfiguration
{
    public @Nullable String              envFilePath;
    public @Nullable Map<String, Object> env;

    /**
     * Constructor for AbstractConfiguration
     * This constructor reads the environment file and stores the key-value pairs in a map
     *
     * @param envFilePath path to the environment file
     *                    The environment file is a text file with key-value pairs separated by an equals sign
     * @throws RuntimeException if the environment file cannot be read
     */
    public AbstractConfiguration(@NonNull String envFilePath) {
        this.envFilePath = envFilePath;
        this.env = new HashMap<>();

        try
        {
            Files.readAllLines(Path.of(envFilePath)).forEach(line -> {
                String[] parts = line.split("=");
                env.put(parts[ 0 ], parts[ 1 ]);
            });
        }
        catch ( IOException e )
        {
            throw new RuntimeException("Unable to process environment file", e);
        }
    }

    /**
     * Get the value of an environment variable
     *
     * @param key the name of the environment variable
     * @return the value of the environment variable
     * @throws RuntimeException if the environment variable is not set
     */
    public static String getSysEnv(String key) {
        String envValue = System.getenv(key);
        if (envValue == null)
        {
            envValue = System.getProperty(key);
            if (envValue == null)
                throw new RuntimeException("Environment variable " + key + " is not set");
        }

        return envValue;
    }

    /**
     * Get the value of an environment variable as a String
     *
     * @param key the name of the environment variable
     * @param defaultValue the default value to return if the environment variable is not set
     * @return the value of the environment variable if it is set, otherwise the default value
     */
    public static String getSysEnv(String key, String defaultValue)
    {
        String envValue = System.getenv(key);
        return envValue != null ? envValue : defaultValue;
    }

    /**
     * Get the value of an environment variable as a boolean
     *
     * @param key the name of the environment variable
     * @param defaultValue the default value to return if the environment variable is not set
     * @return the value of the environment variable as a boolean if it is set, otherwise the default value
     */
    public static boolean getSysEnv(String key, boolean defaultValue)
    {
        return Boolean.parseBoolean(getSysEnv(key, Boolean.toString(defaultValue)));
    }

    /**
     * Get the value of an environment variable as an integer
     *
     * @param key the name of the environment variable
     * @param defaultValue the default value to return if the environment variable is not set
     * @return the value of the environment variable as an integer if it is set, otherwise the default value
     */
    public static int getSysEnv(String key, int defaultValue)
    {
        return Integer.parseInt(getSysEnv(key, Integer.toString(defaultValue)));
    }

    /**
     * Get the value of an environment variable
     *
     * @param key the name of the environment variable
     * @param defaultValue the default value to return if the environment variable is not set
     * @return the value of the environment variable if it is set, otherwise the default value
     */
    public String getEnv(String key, String defaultValue)
    {
        if ( env != null )
            return env.getOrDefault(key, defaultValue).toString();

        String envValue = System.getenv(key);
        return envValue != null ? envValue : defaultValue;
    }

    /**
     * Get the value of an environment variable as a boolean
     *
     * @param key the name of the environment variable
     * @param defaultValue the default value to return if the environment variable is not set
     * @return the value of the environment variable as a boolean if it is set, otherwise the default value
     */
    public boolean getEnv(String key, boolean defaultValue)
    {
        return Boolean.parseBoolean(getEnv(key, Boolean.toString(defaultValue)));
    }

    /**
     * Get the value of an environment variable as an integer
     *
     * @param key the name of the environment variable
     * @param defaultValue the default value to return if the environment variable is not set
     * @return the value of the environment variable as an integer if it is set, otherwise the default value
     */
    public int getEnv(String key, int defaultValue)
    {
        return Integer.parseInt(getEnv(key, Integer.toString(defaultValue)));
    }
}
