// Randomized Prim's Algorithm for maze generation.

package mazegame.core;

import mazegame.util.graph.Spanning2DRectLattice;

public class PrimsAlgo extends GraphAlgo {

    public PrimsAlgo(int rows, int cols) {
        super(rows, cols);
        int graphRows = MapDimToGraphDim(mapRows);
        int graphCols = MapDimToGraphDim(mapCols);
        graph = new Spanning2DRectLattice(graphRows, graphCols);
        // return a FAKE map
        // TODO: trim edges in graph to generate a real prim's map
    }
}
