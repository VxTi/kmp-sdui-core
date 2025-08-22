package nl.q42.core.contentful.elements;

import nl.q42.core.contentful.interfaces.CFContentElement;

import java.util.stream.Stream;

public class CFHeaderElement implements CFContentElement
{
    public static final String[] subTypes = new String[]{ "H1", "H2", "H3", "H4", "H5", "H6"};

    public final String type = "header";
    public String subType;
    public String value;

    public CFHeaderElement(String subType, String value)
    {
        if ( Stream.of(subTypes).noneMatch(subType::equals) )
        {
            throw new IllegalArgumentException("Invalid subType: " + subType);
        }
        this.subType = subType;
        this.value = value;
    }
}
