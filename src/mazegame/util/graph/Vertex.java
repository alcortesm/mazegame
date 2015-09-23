package mazegame.util.graph;

public class Vertex {

    private Spanning2DRectLattice graph;
    private int row;
    private int col;

    public Vertex(int row, int col, Spanning2DRectLattice graph) {
        if (graph == null) {
            throw new NullPointerException("graph");
        }
        int rows = graph.getNumRows();
        int cols = graph.getNumCols();
        if (row < 0) {
            throw new IllegalArgumentException("rows < 0");
        }
        if (row >= rows) {
            throw new IllegalArgumentException(
                    "rows >= number of rows in the graph");
        }
        if (col < 0) {
            throw new IllegalArgumentException("cols < 0");
        }
        if (col >= cols) {
            throw new IllegalArgumentException(
                    "cols >= number of cols in the graph");
        }
        this.graph = graph;
        this.row = row;
        this.col = col;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }

    public String toString() {
        return "Vertex[ " + row + ", " + col + "]";
    }
}
