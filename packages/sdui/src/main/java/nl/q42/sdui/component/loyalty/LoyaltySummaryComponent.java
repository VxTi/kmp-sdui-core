package nl.q42.sdui.component.loyalty;

import lombok.Builder;
import lombok.experimental.SuperBuilder;
import nl.q42.sdui.RequiresAppVersion;
import nl.q42.sdui.action.AbstractAction;
import nl.q42.sdui.component.AbstractComponent;
import org.springframework.lang.Nullable;

@RequiresAppVersion(value = 27)
@SuperBuilder
public class LoyaltySummaryComponent extends AbstractComponent
{
    public final @Builder.Default String         type = "LOYALTY_SUMMARY";
    public @Nullable              String[]       tags;
    public @Nullable              AbstractAction action;
    public @Nullable              AbstractAction openLoyaltyCardAction;
    public @Nullable              AbstractAction openLoginAction;
    public @Nullable              AbstractAction openLoyaltyRegistrationAction;

}
