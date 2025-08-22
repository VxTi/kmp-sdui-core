package nl.q42;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Objects;

@Slf4j
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })

@ComponentScan("nl.hema")
public class Main
{
    public static final String SERVER_MODE = "dev";

    public static void main(String[] args) throws IOException, ReflectiveOperationException
    {
        injectEnvironmentVariables();
        SpringApplication app = new SpringApplication(Main.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "3000"));
        app.run(args);
    }

    private static void injectEnvironmentVariables() throws IOException, ReflectiveOperationException
    {
        // TODO: Implement proper environment variable injection
        File envPath = new File("/Users/luca/Projects/Personal/experience-customerapp-java/env");
        if ( !envPath.exists() )
            throw new RuntimeException("Environment file not found");

        for ( File file : Objects.requireNonNull(envPath.listFiles()) )
        {
            if ( !file.isFile() || !file.getName().endsWith(".json") || !
                    file.getName().startsWith(SERVER_MODE) )
                continue;

            Gson gson       = new Gson();
            var  fileReader = Files.newBufferedReader(file.toPath());
            var  env        = JsonParser.parseReader(fileReader);
            for ( var entry : env.getAsJsonObject().entrySet() )
            {
                log.info("Injecting environment variable: {}", entry.getKey());
                System.setProperty(entry.getKey(), entry.getValue().getAsString());
            }

            log.info("Injected environment variables from {}", file.getName());
        }
    }
}