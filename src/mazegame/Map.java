package mazegame;

class Map {
    private Tile[][] data;
    private int rows;
    private int columns;
    private static int MIN_ROWS = 3;
    private static int MIN_COLUMNS = 3;

    Map(Tile[][] data) {
        throwIfThereAreNulls(data);
        if (! isRectArray(data)) {
            throw new IllegalArgumentException("data is not a rectangular array");
        }
        this.data = data;

        rows = data.length;
        if (rows < MIN_ROWS) {
            throw new IllegalArgumentException(
                    "number of rows (" + rows +
                    ") is less than " + MIN_ROWS);
        }

        columns = data[0].length;
        if (columns < MIN_COLUMNS) {
            throw new IllegalArgumentException(
                    "number of columns (" + columns +
                    ") is less than " + MIN_COLUMNS);
        }
    }

    int getNumRows() { return this.rows; }
    int getNumColumns() { return this.columns; }
    static int getMinRows() { return MIN_ROWS; }
    static int getMinColumns() { return MIN_COLUMNS; }
    Tile getTile(int row, int column) { return data[row][column]; }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r=0; r<getNumRows(); r++) {
            for (int c=0; c<getNumColumns(); c++) {
                sb.append(getTile(r, c));
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    // Checks if a 2D array is rectangular.
    //
    // If one or both of the dimensions are 0, it is not considered
    // rectangular for the purpose of this program.
    private static boolean isRectArray(Tile[][] a) {
        int numRows = a.length;
        if (numRows == 0) {
            return false;
        }
        int numColumns = a[0].length;
        if (numColumns == 0) {
            return false;
        }
        for (int r = 1; r < numRows; r++) {
            if (a[r].length != numColumns) {
                return false;
            }
        }
        return true;
    }

    private static void throwIfThereAreNulls(Tile[][] a) {
        if (a == null) {
            throw new NullPointerException("a");
        }
        for (int r=0; r<a.length; r++) {
            if (a[r] == null) {
                throw new NullPointerException("row " + r);
            }
            for (int c=0; c<a[r].length; c++) {
                if (a[r][c] == null) {
                    throw new NullPointerException("row "
                            + r + ", column " + c);
                }
            }
        }
    }
}
