// Collection of static methods to build different types of maps.

package mazegame.core;

public class MapGenerator {

    // prevent creation of objects from this class
    private MapGenerator() {}

    public static Map Smallest() {
        return Empty(1, 1);
    }

    public static Map Empty(int rows, int cols) {
        Tile[][] a = new Tile[rows][cols];
        for (int r=0; r<a.length; r++) {
            for (int c=0; c<a[r].length; c++) {
                a[r][c] = new Space();
            }
        }
        return new Map(a);
    }

    public static Map Test() {
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
