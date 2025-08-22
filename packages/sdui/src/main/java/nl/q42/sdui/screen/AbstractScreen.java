package nl.q42.sdui.screen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import nl.q42.sdui.AbstractContent;
import nl.q42.sdui.action.AbstractAction;
import nl.q42.sdui.component.header.AbstractHeader;
import nl.q42.sdui.event.AnalyticsEvent;
import nl.q42.sdui.event.BFFEvent;
import nl.q42.sdui.screen.config.AutoRefreshSetting;
import nl.q42.sdui.screen.config.RefreshIdentifiers;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class AbstractScreen implements Serializable
{

    public final            String                   id;
    public final            String                   type;
    public @Builder.Default boolean                  canRefresh               = true;
    public @Builder.Default String[]                 tags                     = new String[ 0 ];
    public @Nullable        AutoRefreshSetting       autoRefresh;
    public @Builder.Default boolean                  maximizeScreenBrightness = false;
    public @Nullable
    @Singular               List<RefreshIdentifiers> refreshIds;
    public @Nullable        AbstractHeader           header;
    public @Nullable        BFFEvent[]               onReadyEvents;
    public @Nullable        AnalyticsEvent[]         onAppearEvents;
    public @Nullable        AbstractAction[]         onFirstAppearActions;

    /**
     * This method returns the element with the given content ID.
     *
     * @param contentId The content ID of the element to return.
     * @return The element with the given content ID, or null if no such element exists.
     */
    public abstract @Nullable AbstractContent getElementById(String contentId);

}
