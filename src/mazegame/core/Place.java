// A place is the coordinates (row and col) of a tile in
// a map.

package mazegame.core;

import mazegame.server.Direction;

public class Place {

    protected int row;
    protected int col;
    protected Map map;

    // clone ctor
    Place(Place place) {
        this(place.row, place.col, place.map);
    }

    public Place(int row, int col, Map map) {
        if (map == null) {
            throw new NullPointerException("map");
        }
        if (row < 0) {
            throw new IllegalArgumentException("row < 0");
        }
        if (row >= map.getNumRows()) {
            throw new IllegalArgumentException(
                    "row > number of rows in the map");
        }
        if (col < 0) {
            throw new IllegalArgumentException("column < 0");
        }
        if (col >= map.getNumCols()) {
            throw new IllegalArgumentException(
                    "column > number of columns in the map");
        }
        this.row = row;
        this.col = col;
        this.map = map;
    }

    int getRow() { return row; }
    int getCol() { return col; }
    Map getMap() { return map; }

    boolean isWalkable() {
        return map.getTile(row, col).isWalkable();
    }

    public boolean equals(Place p) {
        return (this.getRow() == p.getRow()) &&
            (this.getCol() == p.getCol()) &&
            (this.map == p.map);
    }

    public String toString() {
        return "Place[" + row + ", " + col + "]";
    }

    // Returns null if the destination place is out of
    // the map.
    Place placeAt(Direction dir) {
        if (dir == null) {
            throw new NullPointerException("dir");
        }
        int dstRow = getRow();
        int dstCol = getCol();
        switch (dir) {
            case NORTH:
                dstRow--;
                break;
            case SOUTH:
                dstRow++;
                break;
            case EAST:
                dstCol++;
                break;
            case WEST:
                dstCol--;
                break;
            default:
                throw new UnsupportedOperationException(
                        dir.toString());
        }
        if (dstRow < 0 || dstRow >= map.getNumRows() ||
                dstCol < 0 || dstCol >= map.getNumCols()) {
            return null;
        }
        return new Place(dstRow, dstCol, map);
    }
}
