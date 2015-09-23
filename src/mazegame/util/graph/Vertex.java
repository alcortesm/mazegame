package mazegame.util.graph;

public class Vertex {

    private Spanning2DRectLattice graph;
    private int row;
    private int col;

    public Vertex(int r, int c, Spanning2DRectLattice graph) {
        if (graph == null) {
            throw new NullPointerException("graph");
        }
        int rows = graph.getNumRows();
        int cols = graph.getNumCols();
        if (r < 0) {
            throw new IllegalArgumentException("rows < 0");
        }
        if (r >= rows) {
            throw new IllegalArgumentException(
                    "rows >= number of rows in the graph");
        }
        if (c < 0) {
            throw new IllegalArgumentException("cols < 0");
        }
        if (c >= cols) {
            throw new IllegalArgumentException(
                    "cols >= number of cols in the graph");
        }
        this.graph = graph;
        this.row = row;
        this.col = col;
    }
}
