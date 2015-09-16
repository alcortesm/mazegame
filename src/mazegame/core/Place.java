// A place is a point in the map or maze (row and col).

package mazegame.core;

public class Place {

    protected int row;
    protected int col;
    protected Map map;

    // clone ctor
    public Place(Place place) {
        this(place.row, place.col, place.map);
    }

    public Place(int row, int col, Map map) {
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
        this.row = row;
        this.col = col;
        this.map = map;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public Map getMap() { return map; }

    public boolean equals(Place p) {
        return (this.getRow() == p.getRow()) &&
            (this.getCol() == p.getCol()) &&
            (this.map == p.map);
    }

    public String toString() {
        return "Place[" + row + ", " + col + "]";
    }
}
