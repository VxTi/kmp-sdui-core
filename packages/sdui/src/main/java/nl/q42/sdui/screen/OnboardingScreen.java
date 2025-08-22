package nl.q42.sdui.screen;

import nl.q42.core.app.AppContext;
import nl.q42.sdui.component.header.ProfileHeader;
import nl.q42.sdui.component.section.PrimarySection;
import nl.q42.sdui.component.section.SectionContainer;

public class OnboardingScreen
{
    public static AbstractScreen create(AppContext context)
    {
        return ScreenWithContentList
                .builder()
                .id("onboarding")
                .canRefresh(false)
                .header(new ProfileHeader("Hello!", true))
                .content(buildContent(context))
                .build();
    }

    private static SectionContainer buildContent(AppContext context)
    {
        return SectionContainer.builder()
                .section(
                        PrimarySection.builder()
                                .contentId("authorize")
                                .build()
                        )
                .build();
    }
}
