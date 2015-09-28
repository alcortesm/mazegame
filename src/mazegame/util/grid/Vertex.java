package mazegame.util.grid;

import java.util.NoSuchElementException;

import mazegame.util.Direction;
import mazegame.util.List;
import mazegame.util.ListArray;

public class Vertex {

    private Grid g;
    private int r;
    private int c;

    Vertex(Grid g, int r, int c) {
        if (g == null) {
            throw new NullPointerException("g");
        }
        if (! g.isValidVertex(r, c)) {
            throw new IllegalArgumentException();
        }
        this.g = g;
        this.r = r;
        this.c = c;
    }

    public int row() {
        return r;
    }

    public int col() {
        return c;
    }

    public boolean isVisited() {
        return g.isVisited(r, c);
    }

    public void setVisited(boolean b) {
        g.setVisited(r, c, b);
    }

    public boolean hasNeighbour(Direction d) {
        return g.hasNeighbour(r, c, d);
    }

    public Vertex getNeighbour(Direction d) {
        return g.getVertex(r, c, d);
    }

    public boolean isNeighbour(Vertex v) {
        if (g != v.g) {
            throw new IllegalArgumentException();
        }
        return g.areNeighbours(r, c, v.r, v.c);
    }

    public Direction directionToNeighbour(Vertex v)
        throws NoSuchElementException {
        if (v == null) {
            throw new NullPointerException("v");
        }
        if (g != v.g) {
            throw new IllegalArgumentException();
        }
        return g.directionToNeighbour(r, c, v.r, v.c);
    }

    public boolean isConnected(Vertex v) {
        try {
            Direction d = directionToNeighbour(v);
            return isConnected(d);
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean isConnected(Direction d) {
        return g.isConnected(r, c, d);
    }

    public void setConnection(Vertex v, boolean connect)
        throws NoSuchElementException {
        Direction d = directionToNeighbour(v);
        setConnection(d, connect);
    }

    public void setConnection(Direction d, boolean connect) {
        g.setConnection(r, c, d, connect);
    }

    private boolean NotConnectedNonVisited(Direction d) {
        try {
            return ! isConnected(d) &&
                ! getNeighbour(d).isVisited();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public List<Step> notConnectedNonVisitedNegihbours() {

        List<Step> list = new ListArray<Step>(4);
        Direction[] dirs = Direction.values();

        for (int i=0; i<dirs.length; i++) {
            if (NotConnectedNonVisited(dirs[i])) {
                list.add(new Step(this, dirs[i]));
            }
        }
        return list;
    }

    public String toString() {
        return "Vertex(" + r + ", " + c + ", " + isVisited() + ")";
    }

    public boolean equals(Object o) {
        if (! (o instanceof Vertex)) {
            return false;
        }
        Vertex v = (Vertex) o;
        return (g == v.g) && (r == v.r) && (c == v.c);
    }
}
