// Depth First Algorithm for maze generation.

package mazegame.core;

import java.util.Random;

import mazegame.util.Direction;
import mazegame.util.OpaqueSack;
import mazegame.util.OpaqueSackArray;
import mazegame.util.List;
import mazegame.util.ListArray;
import mazegame.util.grid.Grid;
import mazegame.util.grid.Vertex;
import mazegame.util.grid.Step;

public class DepthFirstAlgo extends GridAlgo {

    private Random random;

    public DepthFirstAlgo(int mapRows, int mapCols) {
        super(mapRows, mapCols);
        int gridRows = mapDimToGridDim(mapRows);
        int gridCols = mapDimToGridDim(mapCols);
        grid = new Grid(gridRows, gridCols, false);
        random = new Random();

        int rows = grid.rows();
        int cols = grid.cols();
        Vertex current = grid.getVertex(rows-1, cols-1);
        algorithm(current);
    }

    // Depth First Algorithm (recursive version)
    private void algorithm(Vertex current) {
        OpaqueSack<Step> neighbours = new OpaqueSackArray<Step>(4);
        current.setVisited(true);
        neighbours.add(current.notConnectedNonVisitedNegihbours());
        while (! neighbours.isEmpty()) {
            Step step = neighbours.remove();
            Vertex destination = step.destination();
            if (destination.isVisited()) {
                continue;
            }
            step.setConnection(true);
            algorithm(destination);
        }
    }
}
