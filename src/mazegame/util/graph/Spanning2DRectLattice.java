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

public class Spanning2DRectLattice {

    private int rows;
    private int cols;

    public Spanning2DRectLattice(int rows, int cols) {
        if (rows < 1) {
            throw new IllegalArgumentException("rows < 1");
        }
        if (cols < 1) {
            throw new IllegalArgumentException("cols < 1");
        }
        this.rows = rows;
        this.cols = cols;
    }

    public Vertex getVertex(int r, int c) {
        return new Vertex(r, c, this);
    }

    public Edge getEdge(Vertex a, Vertex b) {
        return Edge.NO_LINK;
    }

    public void setEdge(Vertex a, Vertex b, Edge e) {
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
}
