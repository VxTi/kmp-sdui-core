package nl.q42.common.screen;

import java.io.Serializable;

public record ScreenTab(String title, String imageUrl, String screenName) implements Serializable
{
}
