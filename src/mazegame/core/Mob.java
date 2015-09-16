// A mob is a "mobile" thing.
//
// Mobs can only move over walkable tiles and they can not
// get out of the map.

package mazegame.core;

import mazegame.server.Icon;
import mazegame.server.Direction;

abstract public class Mob extends Thing {

    // create a mob at the starting position
    // defined by place.
    public Mob(Place place, Icon icon) {
        super(place, icon);
    }

    private void setPlace(Place place) {
        this.place = place;
    }

    // Move the mob 1 tile towards the specified direction and
    // returns true.
    //
    // If the destination tile is not walkable or if it is outside
    // the map, the mob does not move and returns false.
    public boolean move(Direction dir) {
        int dstRow = place.getRow();
        int dstCol = place.getCol();
        if (dir == Direction.NORTH) {
                dstRow--;
        } else if (dir == Direction.SOUTH) {
                dstRow++;
        } else if (dir == Direction.WEST) {
                dstCol--;
        } else if (dir == Direction.EAST) {
                dstCol++;
        } else {
            throw new IllegalArgumentException("dir");
        }
        // check if destination is out of the map.
        if (dstRow < 0 ||
                dstRow >= place.getMap().getNumRows() ||
                dstCol < 0 ||
                dstCol >= place.getMap().getNumCols()) {
            return false;
        }
        // check if destination is walkable.
        if (! place.getMap().getTile(dstRow, dstCol).isWalkable()) {
            return false;
        }

        Place dstPlace = new Place(
                dstRow, dstCol, place.getMap());
        setPlace(dstPlace);
        return true;
    }

    public String toString() {
        return "Mob = " + super.toString();
    }
}
