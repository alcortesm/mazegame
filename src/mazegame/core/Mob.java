// A mob is a "mobile" thing.
//
// Mobs can only move over walkable tiles and they can not
// get out of the map.

package mazegame.core;

abstract public class Mob extends Thing {

    public enum DIRECTION {
        NORTH, SOUTH, EAST, WEST
    };

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
    public boolean move(DIRECTION dir) {
        int dstRow = place.getRow();
        int dstColumn = place.getColumn();
        switch (dir) {
            case NORTH:
                dstRow--;
                break;
            case SOUTH:
                dstRow++;
                break;
            case WEST:
                dstColumn--;
                break;
            case EAST:
                dstColumn++;
                break;
            default:
                throw new EnumConstantNotPresentException(
                        dir.getClass(), dir.toString());
        }
        // check if destination is out of the map.
        if (dstRow < 0 ||
                dstRow >= place.getMap().getNumRows() ||
                dstColumn < 0 ||
                dstColumn >= place.getMap().getNumColumns()) {
            return false;
        }
        // check if destination is walkable.
        if (! place.getMap().getTile(dstRow, dstColumn).isWalkable()) {
            return false;
        }

        Place dstPlace = new Place(
                dstRow, dstColumn, place.getMap());
        setPlace(dstPlace);
        return true;
    }
}
