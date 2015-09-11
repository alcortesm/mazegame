// Collection of static methods to build different types of maps.

package mazegame;

class MapGenerator {

    static enum TYPE {
        SMALLEST_EMPTY
    }

    // prevent creation of objects from this class
    private MapGenerator() {}

    private static Map createSmallestEmpty() {
        Tile[][] a = new Tile[Map.getMinRows()][Map.getMinColumns()];
        for (int r=0; r<a.length; r++) {
            for (int c=0; c<a[r].length; c++) {
                // put walls in the external borders
                if (r == 0 || r == a.length-1 ||
                        c == 0 || c == a[r].length-1) {
                    a[r][c] = new Wall();
                } else { // and empty space in the center area
                    a[r][c] = new Space();
                }
            }
        }
        return new Map(a);
    }

    static Map create(TYPE type) {
        switch (type) {
            case SMALLEST_EMPTY:
                return createSmallestEmpty();
            default:
                // should never get here
                throw new EnumConstantNotPresentException(
                        type.getClass(), type.toString());
        }
    }
}
