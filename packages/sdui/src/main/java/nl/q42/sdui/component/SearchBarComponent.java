package nl.q42.sdui.component;

import lombok.experimental.SuperBuilder;
import nl.q42.sdui.RequiresAppVersion;
import nl.q42.sdui.action.AbstractAction;

@RequiresAppVersion(29)
@SuperBuilder
public class SearchBarComponent extends AbstractComponent
{
    public final String type = "SEARCH_BAR_COMPONENT";
    public final String placeholder;
    public final AbstractAction searchAction;
}

