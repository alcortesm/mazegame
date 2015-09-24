// a vertex in a graph

package mazegame.util.graph;

import mazegame.util.List;
import mazegame.util.ListArray;
import mazegame.util.Direction;

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
    public Spanning2DRectLattice getGraph() { return graph; }

    public boolean equals(Object o) {
        if (! (o instanceof Vertex)) {
            return false;
        }
        Vertex v = (Vertex) o;
        return (row == v.row &&
                col == v.col &&
                graph.equals(v.graph));
    }

    public List<Step> bloquedSteps() {
        int rows = graph.getNumRows();
        int cols = graph.getNumCols();
        List<Step> retval = new ListArray<Step>(4);
        Step step;
        if (row != 0) {
            step = new Step(this, Direction.NORTH);
            if (! step.hasLink()) {
                retval.add(step);
            }
        }
        if (row != rows-1) {
            step = new Step(this, Direction.SOUTH);
            if (! step.hasLink()) {
                retval.add(step);
            }
        }
        if (col != 0) {
            step = new Step(this, Direction.WEST);
            if (! step.hasLink()) {
                retval.add(step);
            }
        }
        if (col != cols-1) {
            step = new Step(this, Direction.EAST);
            if (! step.hasLink()) {
                retval.add(step);
            }
        }
        return retval;
    }

    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}
