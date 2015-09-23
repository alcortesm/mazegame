package mazegame.core;

import mazegame.util.graph.Spanning2DRectLattice;

abstract class GraphAlgo extends MapGeneratorAlgo {

    protected Spanning2DRectLattice graph;

    GraphAlgo(int rows, int cols) {
        super(rows, cols);
    }

    static protected int GraphDimToMapDim(int graphDim) {
        return (2*graphDim)-1;
    }

    static protected int MapDimToGraphDim(int mapDim) {
        return roundUpIntegerDivision(mapDim, 2);
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
