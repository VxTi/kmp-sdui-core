package nl.q42.sdui.component.header;

import nl.q42.sdui.RequiresAppVersion;

@RequiresAppVersion(13)
public class ProfileHeader extends AbstractHeader
{
    public boolean isLoading;

    public ProfileHeader(String title, boolean isLoaded)
    {
        super("PROFILE", title);
        this.isLoading = isLoaded;
    }
}
