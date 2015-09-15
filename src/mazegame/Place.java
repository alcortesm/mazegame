// A place is the coordinates (row and column) of an space tile in
// a map.
//
// Places can not be on Walls or other non-walkable tiles.

package mazegame;

class Place {

    protected int row;
    protected int column;
    protected Map map;

    // clone ctor
    Place(Place place) {
        this(place.row, place.column, place.map);
    }

    Place(int row, int column, Map map) {
        if (row < 0) {
            throw new IllegalArgumentException("row < 0");
        }
        if (row >= map.getNumRows()) {
            throw new IllegalArgumentException(
                    "row > number of rows in the map");
        }
        if (column < 0) {
            throw new IllegalArgumentException("column < 0");
        }
        if (column >= map.getNumColumns()) {
            throw new IllegalArgumentException(
                    "column > number of columns in the map");
        }
        if (map == null) {
            throw new NullPointerException("map");
        }
        if (! map.getTile(row, column).isWalkable()) {
            throw new IllegalArgumentException(
                    "non walkable tile at the specified row x colum");
        }
        this.row = row;
        this.column = column;
        this.map = map;
    }

    int getRow() { return row; }
    int getColumn() { return column; }
    Map getMap() { return map; }

    public boolean equals(Place p) {
        return (this.getRow() == p.getRow()) &&
            (this.getColumn() == p.getColumn()) &&
            (this.map == p.map);
    }

    public String toString() {
        return "(" + row + ", " + column + ")";
    }
}
