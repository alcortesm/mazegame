package mazegame.core;

abstract class MapGeneratorAlgo implements MapGenerator {

    protected int mapRows;
    protected int mapCols;

    MapGeneratorAlgo(int mapRows, int mapCols) {
        if (mapRows < 1) {
            throw new IllegalArgumentException("rows < 1");
        }
        if (mapCols < 1) {
            throw new IllegalArgumentException("cols < 1");
        }
        this.mapRows = mapRows;
        this.mapCols = mapCols;
    }
}
