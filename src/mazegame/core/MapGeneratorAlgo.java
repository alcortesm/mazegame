// This class is a map generator that uses algorithms that rely on
// knowing the map rows and columns in advance.

package mazegame.core;

abstract class MapGeneratorAlgo implements MapGenerator {

    protected int mapRows;
    protected int mapCols;

    MapGeneratorAlgo(int mapRows, int mapCols) {
        if (mapRows < 1) {
            throw new IllegalArgumentException("mapRows < 1");
        }
        if (mapCols < 1) {
            throw new IllegalArgumentException("mapCols < 1");
        }
        this.mapRows = mapRows;
        this.mapCols = mapCols;
    }
}
