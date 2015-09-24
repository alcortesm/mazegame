// Footprints are static entities that are created when the hero is
// moved. Their place will be the hero's point of origin after each
// movement. Footprints are stored in the Trail so clients can show
// them.

package mazegame.core;

import mazegame.server.Icon;

class Footprint extends Entity {

    Footprint(Place place) { super(place); }

    public Icon getIcon() { return Icon.FOOTPRINT; }
}
