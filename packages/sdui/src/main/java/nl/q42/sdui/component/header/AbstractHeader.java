package nl.q42.sdui.component.header;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public abstract class AbstractHeader implements Serializable
{
    public final String type;
    public final String title;
}
