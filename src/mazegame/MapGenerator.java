// Collection of static methods to build different types of maps.

package mazegame;

class MapGenerator {

    private static final int SMALL = 6;

    static enum TYPE {
        SMALLEST_EMPTY,
        SMALL_EMPTY,
        TEST
    }

    // prevent creation of objects from this class
    private MapGenerator() {}

    private static Map generateSmallestEmpty() {
        Tile[][] a =
            new Tile[Map.getMinRows()][Map.getMinColumns()];

        for (int r=0; r<a.length; r++) {
            for (int c=0; c<a[r].length; c++) {
                a[r][c] = new Space();
            }
        }

        return new Map(a);
    }

    private static Map generateSmallEmpty() {
        int rows = Math.max(SMALL, Map.getMinRows());
        int columns = Math.max(SMALL, Map.getMinColumns());
        Tile[][] a = new Tile[rows][columns];
        for (int r=0; r<a.length; r++) {
            for (int c=0; c<a[r].length; c++) {
                a[r][c] = new Space();
            }
        }
        return new Map(a);
    }

    private static Map generateTest() {
        Tile[][] a = {
            { new Space(), new Wall(), new Space(), new Wall(),
            new Space(), new Space(), new Space(), new Wall()},
            { new Space(), new Wall(), new Space(), new Space(),
            new Space(), new Wall(), new Space(), new Space()},
            { new Space(), new Wall(), new Space(), new Wall(),
            new Wall(), new Wall(), new Space(), new Wall()},
            { new Space(), new Space(), new Space(), new Wall(),
            new Space(), new Space(), new Space(), new Space()},
        };
        return new Map(a);
    }

    static Map generate(TYPE type) {
        switch (type) {
            case SMALLEST_EMPTY:
                return generateSmallestEmpty();
            case SMALL_EMPTY:
                return generateSmallEmpty();
            case TEST:
                return generateTest();
            default:
                // should never get here
                throw new EnumConstantNotPresentException(
                        type.getClass(), type.toString());
        }
    }
}
