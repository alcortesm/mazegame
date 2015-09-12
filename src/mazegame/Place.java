// A place is the coordinates (row and column) of a tile in
// a map.

package mazegame;

class Place {
    private int row;
    private int column;

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
        this.row = row;
        this.column = column;
    }

    int getRow() { return row; }

    int getColumn() { return column; }

    public boolean equals(Place p) {
        return (this.getRow() == p.getRow()) &&
            (this.getColumn() == p.getColumn());
    }

    public String toString() {
        return "(" + row + ", " + column + ")";
    }
}
