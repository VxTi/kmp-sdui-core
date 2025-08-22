package nl.q42.sdui.component.section;

import lombok.Singular;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public class SectionContainer
{

    public final           String                  type = "SECTIONS";
    public final @Singular List<AbstractSection> sections;
}
