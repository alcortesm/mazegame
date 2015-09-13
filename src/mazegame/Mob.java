// A mob is a "mobile" place.
//
// Mobs can only move over walkable tiles and they can not
// get out of the map.

package mazegame;

class Mob extends Place {

    enum DIRECTION {
        NORTH, SOUTH, EAST, WEST
    };

    // create a mob at the starting position
    // defined by place.
    Mob(Place place) {
        super(place.getRow(), place.getColumn(), place.map);
    }

    // Move the mob 1 tile towards the specified direction and
    // returns true.
    //
    // If the destination tile is not walkable or if it is outside
    // the map, the mob does not move and returns false.
    boolean move(DIRECTION dir) {
        int dstRow = row;
        int dstColumn = column;
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
        if (dstRow < 0 || dstRow >= map.getNumRows() ||
                dstColumn < 0 || dstColumn >= map.getNumColumns()) {
            return false;
        }
        // check if destination is walkable.
        if (! map.getTile(dstRow, dstColumn).isWalkable()) {
            return false;
        }

        row = dstRow;
        column = dstColumn;
        return true;
    }
}
