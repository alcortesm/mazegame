// This class represents spanning subgraphs of 2 dimensional
// finite rectangular lattice graphs.
//
// Rectangular Lattice Graphs are described in:
//
// Gerald L. Thompson, Hamiltonian Tours and Paths in Rectangular
// Lattice Graphs. Mathematics Magazine. Vol. 50, No. 3 (May, 1977),
// pp. 147-150

package mazegame.util.grid;

import java.util.Random;
import java.util.NoSuchElementException;

import mazegame.util.Direction;

public class Grid {

    private int         rows;
    private int         cols;
    private boolean[][] hEdges;
    private boolean[][] vEdges;
    private boolean[][] visited;
    private Random      random;

    private static int vrAt(int vr, Direction d) {
        switch (d) {
            case NORTH:
                return --vr;
            case SOUTH:
                return ++vr;
            case WEST:
                return vr;
            case EAST:
                return vr;
            default:
                throw new UnsupportedOperationException(
                        "Direction " + d);
        }
    }

    private static int vcAt(int vc, Direction d) {
        switch (d) {
            case NORTH:
                return vc;
            case SOUTH:
                return vc;
            case WEST:
                return --vc;
            case EAST:
                return ++vc;
            default:
                throw new UnsupportedOperationException(
                        "Direction " + d);
        }
    }

    private static int edgeRowFromVertex(int vr, Direction d) {
        switch (d) {
            case NORTH:
                return --vr;
            case SOUTH:
                return vr;
            case WEST:
                return vr;
            case EAST:
                return vr;
            default:
                throw new UnsupportedOperationException(
                        "Direction " + d);
        }
    }

    private static int edgeColFromVertex(int vc, Direction d) {
        switch (d) {
            case NORTH:
                return vc;
            case SOUTH:
                return vc;
            case WEST:
                return --vc;
            case EAST:
                return vc;
            default:
                throw new UnsupportedOperationException(
                        "Direction " + d);
        }
    }

    private boolean[][] edgesArrayFromDir(Direction d) {
        switch (d) {
            case NORTH:
            case SOUTH:
                return vEdges;
            case WEST:
            case EAST:
                return hEdges;
            default:
                throw new UnsupportedOperationException(
                        "Direction " + d);
        }
    }

    public Grid(int rows, int cols, boolean connected) {
        if (rows < 1) {
            throw new IllegalArgumentException("rows < 1");
        }
        if (cols < 1) {
            throw new IllegalArgumentException("cols < 1");
        }
        this.rows = rows;
        this.cols = cols;
        hEdges = new boolean[rows][cols-1];
        vEdges = new boolean[rows-1][cols];
        // after ctor, all edges are set
        setAllEdges(connected);
        // after ctor, all vertices are unvisited
        visited = new boolean[rows][cols];
        random = new Random();
    }

    // all possible edges will be open
    public Grid(int rows, int cols) {
        this(rows, cols, true);
    }

    private void setAllEdges(boolean b) {
        for (int r=0; r<hEdges.length; r++) {
            for (int c=0; c<hEdges[r].length; c++) {
                hEdges[r][c] = b;
            }
        }
        for (int r=0; r<vEdges.length; r++) {
            for (int c=0; c<vEdges[r].length; c++) {
                vEdges[r][c] = b;
            }
        }
    }

    public int rows() {
        return rows;
    }

    public int cols() {
        return cols;
    }

    // the order of a graph is the number of its vertices.
    public int order() {
        return rows * cols;
    }

    private static int countBoolean(boolean[][] a, boolean b) {
        int count  = 0;
        for (int r=0; r<a.length; r++) {
            for (int c=0; c<a[r].length; c++) {
                if (a[r][c] == b) {
                    count++;
                }
            }
        }
        return count;
    }

    // the size of a graph is the number of its edges.
    public int size() {
        // should we keep a size attribute instead of calculating
        // this every time?
        int size = countBoolean(hEdges, true);
        size += countBoolean(vEdges, true);
        return size;
    }

    private boolean isValidVr(int vr) {
        if (vr < 0 || vr >= rows) {
            return false;
        }
        return true;
    }

    private boolean isValidVc(int vc) {
        if (vc < 0 || vc >= cols) {
            return false;
        }
        return true;
    }

    boolean isValidVertex(int vr, int vc) {
        return isValidVr(vr) && isValidVc(vc);
    }

    public Vertex getVertex(int vr, int vc) {
        return new Vertex(this, vr, vc);
    }

    Vertex getVertex(int vr, int vc, Direction d) {
        if (! isValidVertex(vr, vc)) {
            throw new IllegalArgumentException();
        }
        int nvr = vrAt(vr, d);
        int nvc = vcAt(vc, d);
        try {
            return new Vertex(this, nvr, nvc);
        } catch (IllegalArgumentException ex) {
            throw new NoSuchElementException();
        }
    }

    public Vertex getRandomVertex() {
        int rr = random.nextInt(rows);
        int rc = random.nextInt(cols);
        return new Vertex(this, rr, rc);
    }

    boolean isVisited(int vr, int vc) {
        if (! isValidVertex(vr, vc)) {
            throw new IllegalArgumentException();
        }
        return visited[vr][vc];
    }

    void setVisited(int vr, int vc, boolean b) {
        if (! isValidVertex(vr, vc)) {
            throw new IllegalArgumentException();
        }
        visited[vr][vc] = b;
    }

    boolean hasNeighbour(int vr, int vc, Direction d) {
        int nvr = vrAt(vr, d);
        int nvc = vcAt(vc, d);
        return isValidVertex(nvr, nvc);
    }

    // returns the direction from a to b if they are neighbours
    Direction directionToNeighbour(
            int ar, int ac, int br, int bc) {
        if (! isValidVertex(ar, ac) ||
                ! isValidVertex(br, bc)) {
            throw new IllegalArgumentException();
        }
        // if they are the same vertex, they do not share an edge
        if (ar == br && ac == bc) {
            throw new NoSuchElementException();
        }
        if (ar == br) { // if they are in the same row...
            if (ac == bc-1) {
                return Direction.EAST;
            } else if (ac == bc+1) {
                return Direction.WEST;
            }
        } else if (ac == bc) {
            if (ar == br-1) {
                return Direction.SOUTH;
            } else if (ar == br+1) {
                return Direction.NORTH;
            }
        }
        throw new NoSuchElementException();
    }

    boolean areNeighbours(int ar, int ac, int br, int bc) {
        try {
            directionToNeighbour(ar, ac, br, bc);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    boolean isConnected(int vr, int vc, Direction d) {
        if (! isValidVertex(vr, vc)) {
            throw new IllegalArgumentException();
        }
        if (! hasNeighbour(vr, vc, d)) {
            return false;
        }
        boolean[][] edges = edgesArrayFromDir(d);
        int er = edgeRowFromVertex(vr, d);
        int ec = edgeColFromVertex(vc, d);
        return edges[er][ec];
    }

    void setConnection(
            int vr, int vc, Direction d, boolean connect) {
        boolean[][] edges = edgesArrayFromDir(d);
        int er = edgeRowFromVertex(vr, d);
        int ec = edgeColFromVertex(vc, d);
        edges[er][ec] = connect;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        // append vertices, east and south connections
        // of all rows except the last one
        for (int r=0; r<rows-1; r++) {
            sb = appendVertexAndEastLinks(sb, r);
            sb = appendSouthLinks(sb, r);
        }
        // append vertices and east connections of last row
        sb = appendVertexAndEastLinks(sb, rows-1);
        return sb.toString();
    }

    private StringBuilder appendVertexAndEastLinks(
        StringBuilder sb, int row) {
        Vertex current;
        sb.append("\t");
        for (int c=0; c<cols; c++) {
            sb.append("*");
            if (c<cols-1) {
                current = getVertex(row, c);
                if (current.isConnected(Direction.EAST)) {
                    sb.append("--");
                } else {
                    sb.append("  ");
                }
            }
        }
        sb.append(System.lineSeparator());
        return sb;
    }

    private StringBuilder appendSouthLinks(
            StringBuilder sb, int row) {
        Vertex current;
        sb.append("\t");
        for (int c=0; c<cols; c++) {
            current = getVertex(row, c);
            if (current.isConnected(Direction.SOUTH)) {
                sb.append("|  ");
            } else {
                sb.append("   ");
            }
        }
        sb.append(System.lineSeparator());
        return sb;
    }
}
