package nl.q42.sdui.component;

import lombok.Builder;
import lombok.experimental.SuperBuilder;
import nl.q42.sdui.action.AbstractAction;
import org.springframework.lang.Nullable;

@SuperBuilder
public class TitleComponent extends AbstractComponent
{
    public final @Builder.Default String         type = "TITLE";
    public                        DisplayStyle   displayStyle;
    public @Nullable              AbstractAction action;
    public                        String         text;
    public @Nullable              String[]       tags;

    public enum DisplayStyle
    {
        EXTRA_LARGE,
        LARGE,
        MEDIUM,
        SMALL
    }
}
