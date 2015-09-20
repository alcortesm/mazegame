package mazegame.core;

import mazegame.server.Icon;

public class End extends Entity {

    public End(Place place) {
        super(place);
    }

    public Icon getIcon() {
        return Icon.END;
    }
}
