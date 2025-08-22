package nl.q42.sdui.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.experimental.SuperBuilder;
import nl.q42.sdui.AbstractContent;
import org.springframework.lang.Nullable;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Abstract class for all components.
 */
@SuperBuilder
public abstract class AbstractComponent extends AbstractContent
{
  public @Nullable String   fingerprint;
  public @Nullable
  @Builder.Default String[] tags = new String[0];

  /**
   * Calculate a fingerprint for the given class.
   *
   * @param object The object to calculate the fingerprint for.
   * @return The calculated fingerprint.
   */
  public static String calculateFingerprint(Object object)
  {
    ObjectMapper mapper = new ObjectMapper();
    try
    {
      MessageDigest crypt = MessageDigest.getInstance("SHA-1");
      crypt.reset();
      var json = mapper.writeValueAsString(object);
      crypt.update(json.getBytes(StandardCharsets.UTF_8));
      return String.format("%040x", new java.math.BigInteger(1, crypt.digest()));
    }
    catch (Exception e)
    {
      throw new RuntimeException("Failed to calculate fingerprint", e);
    }
  }
}
