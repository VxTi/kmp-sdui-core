package nl.q42.sdui.screen.common;

import java.io.Serializable;

public record ScreenTab(String title, String imageUrl, String screenName) implements Serializable
{
}
