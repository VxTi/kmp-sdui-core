package nl.q42.sdui.component.image;

import org.springframework.lang.NonNull;

public class LocalImage extends AbstractImage
{
    public final String name;

    public LocalImage(LocalImages name) {
        super("LOCAL", null);
        this.name = name.name();
    }

    public LocalImage(LocalImages name, @NonNull String alt) {
        super("LOCAL", alt);
        this.name = name.name();
    }
}
