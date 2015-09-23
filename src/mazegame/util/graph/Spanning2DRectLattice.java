// This class represents spanning subgraphs of 2 dimensional
// finite rectangular lattice graphs.
//
// Rectangular Lattice Graphs are described in:
//
// Gerald L. Thompson, Hamiltonian Tours and Paths in Rectangular
// Lattice Graphs. Mathematics Magazine. Vol. 50, No. 3 (May, 1977),
// pp. 147-150

package mazegame.util.graph;

import mazegame.core.Map;
import mazegame.util.Direction;

public class Spanning2DRectLattice {

    private int rows;
    private int cols;
    private Edge[][] horizontals;
    private Edge[][] verticals;

    public Spanning2DRectLattice(int rows, int cols) {
        if (rows < 1) {
            throw new IllegalArgumentException("rows < 1");
        }
        if (cols < 1) {
            throw new IllegalArgumentException("cols < 1");
        }
        this.rows = rows;
        this.cols = cols;
        horizontals = new Edge[rows][cols-1];
        for (int r=0; r<rows; r++) {
            for (int c=0; c<cols-1; c++) {
                horizontals[r][c] = Edge.NO_LINK;
            }
        }
        verticals = new Edge[rows-1][cols];
        for (int r=0; r<rows-1; r++) {
            for (int c=0; c<cols; c++) {
                verticals[r][c] = Edge.NO_LINK;
            }
        }
    }

    public Vertex getVertex(int r, int c) {
        return new Vertex(r, c, this);
    }

    public Edge getEdge(Vertex v, Direction d) {
        if (v == null) {
            throw new NullPointerException("v");
        }
        int r = v.getRow();
        int c = v.getCol();
        switch (d) {
            case NORTH:
                return verticals[r-1][c];
            case SOUTH:
                return verticals[r][c];
            case EAST:
                return horizontals[r][c];
            case WEST:
                return horizontals[r][c-1];
            default:
                throw new UnsupportedOperationException(
                        d.toString());
        }
    }

    // TODO: repeating this switch from the getEdge method is ugly
    public void setEdge(Vertex v, Direction d, Edge e) {
        if (v == null) {
            throw new NullPointerException("v");
        }
        int r = v.getRow();
        int c = v.getCol();
        switch (d) {
            case NORTH:
                verticals[r-1][c] = e;
                break;
            case SOUTH:
                verticals[r][c] = e;
                break;
            case EAST:
                horizontals[r][c] = e;
                break;
            case WEST:
                horizontals[r][c-1] = e;
                break;
            default:
                throw new UnsupportedOperationException(
                        d.toString());
        }
    }

    // the order of a graph is the number of its vertices
    public int order() {
        return rows*cols;
    }

    public int getNumRows() {
        return rows;
    }

    public int getNumCols() {
        return cols;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Spanning2DRectLattice: (" +
                rows + ", " + cols + ")" +
                System.lineSeparator() + System.lineSeparator());

        // append vertex, east and south links of all vertex in all
        // rows except the last one
        for (int r=0; r<rows-1; r++) {
            sb = appendVertexAndEastLinks(sb, r);
            sb = appendSouthLinks(sb, r);
        }
        // append vertex and east links of vertex in the
        // last row
        sb = appendVertexAndEastLinks(sb, rows-1);

        return sb.toString();
    }

    private StringBuilder appendVertexAndEastLinks(StringBuilder sb, int row) {
        Vertex current;
        sb.append("\t");
        for (int c=0; c<cols; c++) {
            sb.append("*");
            if (c<cols-1) {
                current = getVertex(row, c);
                if (getEdge(current, Direction.EAST) ==
                        Edge.LINK) {
                    sb.append("--");
                } else {
                    sb.append("  ");
                }
            }
        }
        sb.append(System.lineSeparator());
        return sb;
    }

    private StringBuilder appendSouthLinks(StringBuilder sb, int row) {
        Vertex current;
        sb.append("\t");
        for (int c=0; c<cols; c++) {
            current = getVertex(row, c);
            if (getEdge(current, Direction.SOUTH) ==
                    Edge.LINK) {
                sb.append("|  ");
            } else {
                sb.append("   ");
            }
        }
        sb.append(System.lineSeparator());
        return sb;
    }
}
