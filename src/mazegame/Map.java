// A map is the physical part of the maze, the walls and the spaces
// between them.
//
// The map constructor receives a 2D array of Tiles. Before accepting
// the array it must pass some sanity checks and we must enforce the
// class invariants:
//
//  1. no nulls of any type
//  2. it must be rectangular
//  3. all the border tiles must be walls
//  4. there should be at least one interior tile that is a space
//
//  1 is checked by throwIfThereAreNulls().
//  2 is checked by isRectArray().
//  3 and 4 is more difficult for the student as, you should do that
//  using reflection or by adding an identification method to Tile,
//  which is very bad design.
//
//  I will solve 3 by leaving out the exterior walls as implicit.
//
//  I will solve 4 with and identification method in Tile to avoid
//  reflection.
//
//  The minimum possible maze will be:
//
//  ###
//  # #   This is a 1 by 1 array of one Space Tile (as exterior
//  ###   walls are implicit).
//
//  A more general Maze (7x4) will look like this:
//
//  #########
//  #1#     #  To get from 1 to 2 the player will have to move as:
//  #   # ###  as follows:
//  # ###  2#
//  #   #####    South, East x 2, North, East x 2,
//  #########    South x 2, East x 2.

package mazegame;

class Map {
    private Tile[][] data;
    private int rows;
    private int columns;
    private static int MIN_ROWS = 1;
    private static int MIN_COLUMNS = 1;

    Map(Tile[][] data) {
        throwIfThereAreNulls(data);
        if (! isRectArray(data)) {
            throw new IllegalArgumentException(
                    "data is not a rectangular array");
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

    // there is no need to check for parameter errors, as the
    // array access will already check that for us.
    Tile getTile(int row, int column) { return data[row][column]; }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        // North wall
        for (int c=0; c<getNumColumns()+2; c++) {
            sb.append(Wall.TEXT_ICON);
        }
        sb.append(System.lineSeparator());

        // Maze interior (walls and spaces as per 'data')
        for (int r=0; r<getNumRows(); r++) {

            sb.append(Wall.TEXT_ICON); // East wall

            for (int c=0; c<getNumColumns(); c++) {
                sb.append(getTile(r, c)); // data
            }

            sb.append(Wall.TEXT_ICON); // West wall
            sb.append(System.lineSeparator());
        }

        // South wall
        for (int c=0; c<getNumColumns()+2; c++) {
            sb.append(Wall.TEXT_ICON);
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
            throw new NullPointerException("array");
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
