// A place is the coordinates (row and column) of a tile in
// a map.

package mazegame;

class Place {
    protected int row;
    protected int column;
    protected Map map;

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
        this.row = row;
        this.column = column;
        this.map = map;
    }

    int getRow() { return row; }

    int getColumn() { return column; }

    public boolean equals(Place p) {
        return (this.getRow() == p.getRow()) &&
            (this.getColumn() == p.getColumn()) &&
            (this.map == p.map);
    }

    public String toString() {
        return "(" + row + ", " + column + ")";
    }

    // This method returns the string index of a place
    // in the string representation of its map
    int getMapStringIndex() {
        // A row holds as many chars as the number of columns
        // plus 2 (the two east and west wall characters)
        // plus the eol charactters
        int rowLength = map.getNumColumns() + 2 +
            System.lineSeparator().length();
        int index = 0;
        // add the proper number of rows (+1 as the first row will
        // always be the north wall).
        index += (getRow() + 1) * rowLength;
        // add the proper number of columns (+1 as the first column
        // will always be the east wall character.
        index += getColumn() + 1;
        return index;
    }
}
