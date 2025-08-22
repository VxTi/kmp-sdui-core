package nl.q42.server;

import nl.q42.core.app.AppContext;
import nl.q42.sdui.component.AbstractComponent;
import nl.q42.sdui.component.section.PrimarySection;
import nl.q42.sdui.screen.AbstractScreen;
import nl.q42.sdui.screen.ScreenWithContentList;

public class BasicScreenResponse extends AbstractHttpResponse
{
    public AbstractScreen screen;

    public BasicScreenResponse(final AbstractScreen screen, final AppContext context)
    {
        this.screen = screen;
        // Not supported yet
        if ( !( screen instanceof ScreenWithContentList screenWithContentList ) )
            return;

        for ( var section : screenWithContentList.content.sections )
        {
            if ( !( section instanceof PrimarySection primarySection ) )
                continue;

            /*primarySection.components.removeIf(component -> {
                var reqVer = component.getClass().getAnnotation(RequiresAppVersion.class);
                return reqVer != null && reqVer.value() > context.version;
            });*/
            for ( var element : primarySection.components )
            {
                element.fingerprint = AbstractComponent.calculateFingerprint(element);
            }
        }
    }
}
