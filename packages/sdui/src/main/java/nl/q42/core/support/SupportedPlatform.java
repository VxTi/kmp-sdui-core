package nl.q42.core.support;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SupportedPlatform
{
    ANDROID("android"),
    IOS("ios");

    private final String platform;
}
