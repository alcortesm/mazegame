// Randomized Prim's Algorithm for maze generation.

package mazegame.core;

import mazegame.util.Direction;
import mazegame.util.OpaqueSack;
import mazegame.util.OpaqueSackArray;
import mazegame.util.List;
import mazegame.util.ListArray;
import mazegame.util.grid.Grid;
import mazegame.util.grid.Vertex;
import mazegame.util.grid.Step;

public class PrimsAlgo extends GridAlgo {

    public PrimsAlgo(int mapRows, int mapCols) {
        super(mapRows, mapCols);
        int gridRows = mapDimToGridDim(mapRows);
        int gridCols = mapDimToGridDim(mapCols);
        grid = new Grid(gridRows, gridCols, false);

        algorithm();
    }

    // Randomized Prim's Algorithm
    private void algorithm() {

        // The ingredients:
        //
        // - Frontier: a random list of bloqued neighbours
        OpaqueSack<Step> frontier = new OpaqueSackArray<Step>();
        // - Start: the starting point of the algorithm
        Vertex start;


        // The algorithm:
        //
        // 1. Pick a random vertex as the starting point and count it
        // as visited.
        start = grid.getRandomVertex();
        start.setVisited(true);

        // 2. Add its bloqued steps to the frontier
        frontier.add(start.notConnectedNonVisitedNegihbours());

        // 3. while there are neighbours in the frontier...
        Step bloquedPath;
        Vertex destination;
        while(! frontier.isEmpty()) {

            // 3a. Extract a random vertex from the frontier
            bloquedPath = frontier.remove();
            destination = bloquedPath.destination();

            // 3b. If it has already been visited, forget about it
            if (destination.isVisited()) {
                continue;
            }

            // ... else open a link to it and mark it as visited
            bloquedPath.setConnection(true);
            destination.setVisited(true);

            // add the neighbours of the destination to the
            // frontier
            frontier.add(
                    destination.notConnectedNonVisitedNegihbours());
        }
    }
}
