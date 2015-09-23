// A mobile entity.

package mazegame.core;

import mazegame.util.Direction;

public abstract class Mob extends Entity {

    public Mob(Place place) {
        super(place);
    }

    public boolean move(Direction dir) {
        Place dst = place.placeAt(dir);
        if (dst == null) {
            return false;
        }
        if (! dst.isWalkable()) {
            return false;
        }
        this.place = dst;
        return true;
    }
}
