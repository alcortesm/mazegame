// Randomized Prim's Algorithm for maze generation.

package mazegame.core;

import mazegame.util.graph.Spanning2DRectLattice;
import mazegame.util.graph.Vertex;
import mazegame.util.graph.Edge;
import mazegame.util.graph.Step;
import mazegame.util.Direction;
import mazegame.util.RandomList;
import mazegame.util.RandomListArray;
import mazegame.util.List;
import mazegame.util.ListArray;

public class PrimsAlgo extends GraphAlgo {

    public PrimsAlgo(int mapRows, int mapCols) {
        super(mapRows, mapCols);
        int graphRows = MapDimToGraphDim(mapRows);
        int graphCols = MapDimToGraphDim(mapCols);
        graph = new Spanning2DRectLattice(graphRows, graphCols);
        algorithm();
//        System.out.println(graph);
    }

    // Randomized Prim's Algorithm
    private void algorithm() {

        // The ingredients:
        //
        // - Visited: A list of already visited Vertices
        List<Vertex> visited = new ListArray<Vertex>(graph.order());
        // - Frontier: a random list of bloqued steps
        RandomList<Step> frontier = new RandomListArray<Step>();
        // - Start: the starting point of the algorithm
        Vertex start;


        // The algorithm:
        //
        // 1. Pick a random vertex as the starting point and count it
        // as visited.
        start = graph.getRandomVertex();
        visited.add(start);
//        System.out.println("start: " + start);

        // 2. Add it bloqued steps to the frontier
        frontier.add(start.bloquedSteps());

        // 3. while there are neighbours in the frontier...
        Step bloquedPath;
        Vertex destination;
        while(! frontier.isEmpty()) {
//            System.out.println("frontier: " + frontier);
//            System.out.println("visited: " + visited);
//            System.out.println(graph);

            // 3a. Extract a random vertex from the frontier
            bloquedPath = frontier.remove();
            destination = bloquedPath.destination();
//            System.out.println("removing: " + bloquedPath);

            // 3b. If it has already been visited, forget about it
            if (visited.contains(destination)) {
//                System.out.println("dropping it");
                continue;
            }
//            System.out.println("opening " + bloquedPath);

            // ... else open a link to it and mark it as visited
            graph.openLink(bloquedPath);
            visited.add(destination);

            // add the neighbours of the destination to the
            // frontier
            frontier.add(destination.bloquedSteps());
        }
    }
}
