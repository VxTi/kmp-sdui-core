package nl.q42.sdui.component.section;

import lombok.Data;

import java.io.Serializable;

@Data
public class ScreenTab implements Serializable
{
    public String tabId;
    public SectionContainer content;
}
