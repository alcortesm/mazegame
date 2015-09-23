package mazegame.core;

import mazegame.util.graph.Spanning2DRectLattice;
import mazegame.util.graph.Vertex;
import mazegame.util.graph.Edge;
import mazegame.util.Direction;
import mazegame.core.Space;

abstract class GraphAlgo extends MapGeneratorAlgo {

    private static final Space space = new Space();

    protected Spanning2DRectLattice graph;

    GraphAlgo(int mapRows, int mapCols) {
        super(mapRows, mapCols);
    }

    static protected int MapDimToGraphDim(int m) {
        return roundUpIntegerDivision(m, 2);
    }

    static protected int GraphIndexToMapIndex(int g) {
        return (2*g);
    }

    static private int roundUpIntegerDivision(int num, int div) {
        // black magic to avoid using Math.ceil() on the result of
        // double division
        return (num + div - 1) / div;
    }

    public Map generateMap() {
        if (graph == null) {
            throw new NullPointerException("graph");
        }
        Tile[][] tiles = createChessBoard();
        tiles = turnLinksIntoSpaces(tiles);
        return new Map(tiles);
    }

    private Tile[][] createChessBoard() {
        Tile[][] tiles = new Tile[mapRows][mapCols];
        Space s = new Space();
        Wall w = new Wall();
        for (int r=0; r<mapRows; r++) {
            for (int c=0; c<mapCols; c++) {
                if ((r + c) % 2 == 0) {
                    tiles[r][c] = s;
                } else {
                    tiles[r][c] = w;
                }
            }
        }
        return tiles;
    }

    private Tile[][] turnLinksIntoSpaces(Tile[][] tiles) {
        int graphRows = graph.getNumRows();
        int graphCols = graph.getNumCols();
        Vertex current;

        // for all rows but the last...
        for (int r=0; r<graphRows-1; r++) {
            // for all columns but the last...
            for (int c=0; c<graphCols-1; c++) {
                // set south and east tiles
                current = graph.getVertex(r, c);
                carveSpaceFromEdge(tiles, current, Direction.SOUTH);
                carveSpaceFromEdge(tiles, current, Direction.EAST);
            }
            // for the last column set only the south tile
            current = graph.getVertex(r, graphCols-1);
            carveSpaceFromEdge(tiles, current, Direction.SOUTH);
        }

        // for the last row, set only the east tiles
        for (int c=0; c<graphCols-1; c++) {
            // set south and east tiles
            current = graph.getVertex(graphRows-1, c);
            carveSpaceFromEdge(tiles, current, Direction.EAST);
        }
        return tiles;
    }

    private void carveSpaceFromEdge(
            Tile[][] tiles, Vertex vertex, Direction dir) {
        int graphRow = vertex.getRow();
        int graphCol = vertex.getCol();
        int mapRow = GraphIndexToMapIndex(graphRow);
        int mapCol = GraphIndexToMapIndex(graphCol);
        if (graph.getEdge(vertex, dir) == Edge.LINK) {
            switch (dir) {
                case NORTH:
                    tiles[mapRow-1][mapCol] = space;
                    break;
                case SOUTH:
                    tiles[mapRow+1][mapCol] = space;
                    break;
                case EAST:
                    tiles[mapRow][mapCol+1] = space;
                    break;
                case WEST:
                    tiles[mapRow][mapCol-1] = space;
                    break;
                default:
                    throw new UnsupportedOperationException(
                            dir.toString());
            }
        }
    }
}
