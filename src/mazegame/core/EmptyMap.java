// Empty map is a simple algorithm to generate empty maps,
// this is, maps without walls.

package mazegame.core;

public class EmptyMap extends MapGeneratorAlgo {

    public EmptyMap(int rows, int cols) {
        super(rows, cols);
    }

    public Map generateMap() {
        Tile space = new Space();
        Tile[][] tiles = new Tile[mapRows][mapCols];
        for (int r=0; r<mapRows; r++) {
            for (int c=0; c<mapCols; c++) {
                tiles[r][c] = space;
            }
        }
        return new Map(tiles);
    }
}
