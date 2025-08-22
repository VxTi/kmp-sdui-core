package nl.q42.sdui.component.section;

import lombok.Builder;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import nl.q42.core.content.LoadComponents;
import nl.q42.sdui.component.AbstractComponent;
import org.springframework.lang.Nullable;

import java.util.List;

@SuperBuilder
public class PrimarySection extends AbstractSection
{
  public final @Builder.Default String type = "PRIMARY";

  public final @Singular List<AbstractComponent> components;
  public @Builder.Default
  final                  boolean                 isUsingSpacerComponents = false;
  public @Nullable       LoadComponents          nextPage;
}