// Collection of static methods to build different types of maps.

package mazegame;

class MapGenerator {

    // prevent creation of objects from this class
    private MapGenerator() {}

    static Map Smallest() {
        return Empty(1, 1);
    }

    static Map Empty(int rows, int columns) {
        Tile[][] a = new Tile[rows][columns];
        for (int r=0; r<a.length; r++) {
            for (int c=0; c<a[r].length; c++) {
                a[r][c] = new Space();
            }
        }
        return new Map(a);
    }

    static Map Test() {
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
}
