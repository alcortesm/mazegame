package mazegame.core;

import mazegame.server.Icon;

class Footprint extends Entity {

    Footprint(Place place) { super(place); }

    public Icon getIcon() { return Icon.FOOTPRINT; }
}
