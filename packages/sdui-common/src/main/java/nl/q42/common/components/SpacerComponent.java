package nl.q42.common.components;

import nl.q42.common.VersionDependable;

@VersionDependable(until = 2)
public class SpacerComponent extends Component {
    public final int size;

    public SpacerComponent(int size, String contentId) {
        super(ComponentTypes.SPACER, contentId);
        this.size = size;
    }
}
