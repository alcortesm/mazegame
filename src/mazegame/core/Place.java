// A place is the coordinates (row and col) of an space tile in
// a map.
//
// Places can not be on Walls or other non-walkable tiles.

package mazegame.core;

class Place {

    protected int row;
    protected int col;
    protected Map map;

    // clone ctor
    Place(Place place) {
        this(place.row, place.col, place.map);
    }

    Place(int row, int col, Map map) {
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
        if (map == null) {
            throw new NullPointerException("map");
        }
        if (! map.getTile(row, col).isWalkable()) {
            throw new IllegalArgumentException(
                    "non walkable tile at the specified row x column");
        }
        this.row = row;
        this.col = col;
        this.map = map;
    }

    int getRow() { return row; }
    int getCol() { return col; }
    Map getMap() { return map; }

    public boolean equals(Place p) {
        return (this.getRow() == p.getRow()) &&
            (this.getCol() == p.getCol()) &&
            (this.map == p.map);
    }

    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}
