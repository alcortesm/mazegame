// Randomized Prim's Algorithm for maze generation.

package mazegame.core;

import mazegame.util.graph.Spanning2DRectLattice;
import mazegame.util.graph.Vertex;
import mazegame.util.graph.Edge;
import mazegame.util.Direction;

public class PrimsAlgo extends GraphAlgo {

    public PrimsAlgo(int mapRows, int mapCols) {
        super(mapRows, mapCols);
        int graphRows = MapDimToGraphDim(mapRows);
        int graphCols = MapDimToGraphDim(mapCols);
        graph = new Spanning2DRectLattice(graphRows, graphCols);

        // Randomized Prim's Algorithm
        Vertex start = graph.getRandomVertex();

        // return a FAKE map: just a big L
        // first the downward corridor
        for (int r=0; r<graph.getNumRows()-1; r++) {
            Vertex current = graph.getVertex(r, 0);
            graph.setEdge(current, Direction.SOUTH, Edge.LINK);
        }
        // now the bottom corridor
        for (int c=0; c<graph.getNumCols()-1; c++) {
            Vertex current =
                graph.getVertex(graph.getNumRows()-1, c);
            graph.setEdge(current, Direction.EAST, Edge.LINK);
        }
    }
}
