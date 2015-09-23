// Randomized Prim's Algorithm for maze generation.

package mazegame.core;

import mazegame.util.graph.Spanning2DRectLattice;

public class PrimsAlgo extends GraphAlgo {

    public PrimsAlgo(int rows, int cols) {
        super(rows, cols);
    }

    public Map generateMap() {
        int graphRows = MapDimToGraphDim(mapRows);
        int graphCols = MapDimToGraphDim(mapCols);

        Spanning2DRectLattice graph =
            new Spanning2DRectLattice(graphRows, graphCols);
        // return a FAKE map
        // TODO: trim edges in graph to generate a real prim's map
        Tile[][] tiles = new Tile[mapRows][mapCols];
        Space s = new Space();
        for (int r=0; r<mapRows; r++) {
            for (int c=0; c<mapCols; c++) {
                tiles[r][c] = s;
            }
        }
        return new Map(tiles);
    }

}
