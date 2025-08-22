package nl.q42.core.contentful;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import nl.q42.core.contentful.interfaces.CFContentItem;
import org.springframework.lang.Nullable;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CFContentScreen
{

    public @Builder.Default String          type = "content-screen";
    public @Nullable        CFContentItem[] items;
    public                  String          slug;
    public                  String          body;
    public                  String          titleLong;
    public                  String          titleShort;
    public                  String          imageUrl;
    public                  String          id;
}
