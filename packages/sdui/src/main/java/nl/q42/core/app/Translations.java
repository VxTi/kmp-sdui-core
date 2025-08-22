package nl.q42.core.app;

import lombok.extern.slf4j.Slf4j;
import nl.q42.core.support.SupportedLocale;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Translations
{
  private static final Map<SupportedLocale, Map<String, String>> translationMap = new HashMap<>();

  public static String translate(String key, SupportedLocale locale)
  {
    Map<String, String> languageMap = translationMap.get(locale);
    if (languageMap == null)
      return key;

    return languageMap.getOrDefault(key, key);
  }
/*
    static {
        try {
            var stream = Translations.class
                    .getClassLoader()
                    .getResourceAsStream("./labels.json");

            if (stream == null)
                throw new RuntimeException("Failed to load translations");

            var fileContent = new String(stream.readAllBytes());
            var json = JsonParser.parseString(fileContent).getAsJsonObject();
            json.keySet().forEach(key -> {
                var locale = SupportedLocale.fromString(key);
                if (locale == null)
                    return;

                var languageMap = new HashMap<String, String>();
                json.getAsJsonObject(key).entrySet().forEach(entry -> languageMap.put(entry.getKey(), entry.getValue().getAsString()));
                translationMap.put(SupportedLocale.valueOf(key), languageMap);
            });

            stream.close();

        } catch (Exception e) {
            log.error("Failed to load translations", e);
        }
    }*/
}
