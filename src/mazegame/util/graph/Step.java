// Steps represent a vertex using relative coordinates: origin +
// direction.

package mazegame.util.graph;

import mazegame.util.Direction;

public class Step {

    private Vertex origin;
    private Direction dir;

    public Step(Vertex origin, Direction dir) {
        if (origin == null) {
            throw new NullPointerException("origin");
        }
        this.origin = origin;
        this.dir = dir;
    }

    public boolean equals(Object o) {
        if (! (o instanceof Step)) {
            return false;
        }
        Step s = (Step) o;
        return (origin.equals(s.origin) &&
                dir.equals(s.dir));
    }

    public Vertex getOrigin() {
        return origin;
    }

    public Direction getDirection() {
        return dir;
    }

    public boolean hasLink() {
        Spanning2DRectLattice graph = origin.getGraph();
        Edge edge = graph.getEdge(this);
        return edge == Edge.LINK;
    }

    public Vertex destination() {
        Spanning2DRectLattice graph = origin.getGraph();
        int row = origin.getRow();
        int col = origin.getCol();
        switch (dir) {
            case NORTH:
                return graph.getVertex(row-1, col);
            case SOUTH:
                return graph.getVertex(row+1, col);
            case EAST:
                return graph.getVertex(row, col+1);
            case WEST:
                return graph.getVertex(row, col-1);
            default:
                throw new UnsupportedOperationException(
                        dir.toString());
        }
    }

    public String toString() {
        return "" + origin + "->" + dir;
    }
}
