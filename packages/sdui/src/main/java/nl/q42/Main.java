package nl.q42;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.Collections;

@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})

@ComponentScan("nl.q42")
public class Main
{
  public static final String SERVER_MODE = "dev";

  public static void main(String[] args) throws IOException
  {
    // injectEnvironmentVariables();
    SpringApplication app = new SpringApplication(Main.class);
    app.setDefaultProperties(Collections.singletonMap("server.port", "8080"));
    app.run(args);
  }

  /*private static void injectEnvironmentVariables() throws IOException
  {
    // TODO: Implement proper environment variable injection
    File envPath = new File("env");

    System.out.printf("Path: %s", envPath.getAbsolutePath());
    if (!envPath.exists())
      throw new RuntimeException("Environment file not found");

    for (File file : Objects.requireNonNull(envPath.listFiles()))
    {
      if (!file.isFile() || !file.getName().endsWith(".json") || !
          file.getName().startsWith(SERVER_MODE))
        continue;

      Gson gson       = new Gson();
      var  fileReader = Files.newBufferedReader(file.toPath());
      var  env        = JsonParser.parseReader(fileReader);
      for (var entry : env.getAsJsonObject().entrySet())
      {
        log.info("Injecting environment variable: {}", entry.getKey());
        System.setProperty(entry.getKey(), entry.getValue().getAsString());
      }

      log.info("Injected environment variables from {}", file.getName());
    }
  }*/
}