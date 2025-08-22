package nl.q42.core.uri.validation;

import lombok.RequiredArgsConstructor;
import nl.q42.core.aws.HttpLambdaContext;
import nl.q42.core.aws.HttpLambdaEvent;

/**
 * A validator for a specific type of event.
 *
 * @param <T> The result type of the validation.
 */
@RequiredArgsConstructor
public abstract class Validator<T>
{
  /**
   * The specification of the parameter to validate.
   */
  public OpenApiParameter spec;

  /**
   * Validate the given event and context.
   *
   * @param event   The event to validate.
   * @param context The context to validate.
   * @return The validation result.
   */
  public abstract ValidationResult<T> validate(HttpLambdaEvent event,
                                               HttpLambdaContext context);

}
