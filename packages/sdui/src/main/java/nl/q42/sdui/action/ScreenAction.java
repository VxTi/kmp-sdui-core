package nl.q42.sdui.action;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import nl.q42.sdui.event.BFFEvent;
import nl.q42.sdui.event.sideeffects.SideEffect;
import nl.q42.sdui.screen.AbstractScreen;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ScreenAction extends AbstractAction
{
    public final     String                            type = "SCREEN";
    public @Nullable String                            accessibilityDescription;
    public @Nullable BFFEvent[]                        onSuccessEvents;
    public @Nullable SideEffect[]                      onExecute;
    public           PresentationStyle                 presentationStyle;
    public final     String                            url;
    public final     AbstractScreen                    placeholderContent;
    public @Nullable ScreenQueryParameterPlaceholder[] queryParameterResolvers;

    public enum PresentationStyle
    {
        DETAIL,
        MODAL,
        SHEET
    }

    public enum ScreenQueryParameterPlaceholder
    {
        PROFILE_FAVORITE_STORE_ID,
        PROFILE_LAST_SELECTED_STORE_ID,
        SEARCH_HISTORY,
        SEARCH_QUERY;
    }
}
