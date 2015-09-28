package mazegame.util.grid;

import mazegame.util.Direction;

// an step is a directed edge, this is
// a point of origin an a direction.
public class Step {

    private Vertex    v;
    private Direction d;

    Step(Vertex v, Direction d) {
        if (v == null) {
            throw new NullPointerException("v");
        }
        if (! v.hasNeighbour(d)) {
            throw new IllegalArgumentException();
        }
        this.v = v;
        this.d = d;
    }

    public Vertex destination() {
        return v.getNeighbour(d);
    }

    public void setConnection(boolean connect) {
        v.setConnection(d, connect);
    }

    public String toString() {
        return "Step: " + v + " --> " + d;
    }
}
