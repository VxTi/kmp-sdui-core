package nl.q42.server;

import nl.q42.core.app.AppContext;
import nl.q42.core.data.ScreenCacheData;
import nl.q42.sdui.screen.AbstractScreen;

public class ScreenResponseWithCache extends BasicScreenResponse
{
    public ScreenCacheData cacheData;

    public ScreenResponseWithCache(final AbstractScreen screen,
                                   final ScreenCacheData cacheData, final AppContext context)
    {
        super(screen, context);
        this.cacheData = cacheData;
    }
}
