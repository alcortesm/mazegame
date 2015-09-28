package mazegame.util.grid;

import org.junit.Test;
import static org.junit.Assert.*;

public class GridTest {

    @Test
    public void ctorMustWork() {
        new Grid(3, 5);
        new Grid(5, 3);
        new Grid(1, 1);
        new Grid(100, 100);
    }

    @Test
    public void dimensionsMustWork() {
        Grid grid = new Grid(3, 5);
        assertTrue(grid.rows() == 3);
        assertTrue(grid.cols() == 5);
        grid = new Grid(1, 1);
        assertTrue(grid.rows() == 1);
        assertTrue(grid.cols() == 1);
    }

    @Test
    public void orderMustWork() {
        Grid grid = new Grid(3, 5);
        assertTrue(grid.order() == 15);
        grid = new Grid(5, 3);
        assertTrue(grid.order() == 15);
        grid = new Grid(1, 1);
        assertTrue(grid.order() == 1);
    }

    @Test
    public void ctorMustThrowOnNonPositiveRowsOrCols() {
        try {
            new Grid(0, 5);
        } catch (IllegalArgumentException ex) {
            // expected
        }
        try {
            new Grid(5, 0);
        } catch (IllegalArgumentException ex) {
            // expected
        }
        try {
            new Grid(0, 0);
        } catch (IllegalArgumentException ex) {
            // expected
        }
        try {
            new Grid(-1, 5);
        } catch (IllegalArgumentException ex) {
            // expected
        }
        try {
            new Grid(5, -1);
        } catch (IllegalArgumentException ex) {
            // expected
        }
        try {
            new Grid(-1, -1);
        } catch (IllegalArgumentException ex) {
            // expected
        }
    }

    @Test
    public void isValidVertex() {
        Grid g = new Grid(1, 1);
        assertTrue(g.isValidVertex(0, 0));
        g = new Grid(1, 2);
        assertTrue(g.isValidVertex(0, 0));
        assertTrue(g.isValidVertex(0, 1));
        g = new Grid(2, 1);
        assertTrue(g.isValidVertex(0, 0));
        assertTrue(g.isValidVertex(1, 0));
        g = new Grid(2, 1);
        assertTrue(g.isValidVertex(0, 0));
        assertTrue(g.isValidVertex(1, 0));
        g = new Grid(2, 2);
        assertTrue(g.isValidVertex(0, 0));
        assertTrue(g.isValidVertex(0, 1));
        assertTrue(g.isValidVertex(1, 0));
        assertTrue(g.isValidVertex(1, 1));
        g = new Grid(3, 3);
        assertTrue(g.isValidVertex(0, 0));
        assertTrue(g.isValidVertex(0, 1));
        assertTrue(g.isValidVertex(0, 2));
        assertTrue(g.isValidVertex(1, 0));
        assertTrue(g.isValidVertex(1, 1));
        assertTrue(g.isValidVertex(1, 2));
        assertTrue(g.isValidVertex(2, 0));
        assertTrue(g.isValidVertex(2, 1));
        assertTrue(g.isValidVertex(2, 2));
        // invalid
        g = new Grid(1, 1);
        assertFalse(g.isValidVertex(3, 5));
        assertFalse(g.isValidVertex(-3, -5));
        assertFalse(g.isValidVertex(1, 0));
        assertFalse(g.isValidVertex(0, 1));
        assertFalse(g.isValidVertex(1, 1));
        assertFalse(g.isValidVertex(0, -1));
        assertFalse(g.isValidVertex(-1, 0));
    }

    @Test
    public void getVertex() {
        Grid g;
        Vertex v;

        g = new Grid(1, 1);
        v = g.getVertex(0, 0);

        try {
            v = g.getVertex(0, 1);
            fail("must throw, but did not");
        } catch (IllegalArgumentException ex) {
            // expected
        } catch (Exception ex) {
            fail(ex.toString());
        }

        try {
            v = g.getVertex(1, 0);
            fail("must throw, but did not");
        } catch (IllegalArgumentException ex) {
            // expected
        } catch (Exception ex) {
            fail(ex.toString());
        }

        try {
            v = g.getVertex(1, 1);
            fail("must throw, but did not");
        } catch (IllegalArgumentException ex) {
            // expected
        } catch (Exception ex) {
            fail(ex.toString());
        }

        try {
            v = g.getVertex(-1, 0);
            fail("must throw, but did not");
        } catch (IllegalArgumentException ex) {
            // expected
        } catch (Exception ex) {
            fail(ex.toString());
        }

        try {
            v = g.getVertex(0, -1);
            fail("must throw, but did not");
        } catch (IllegalArgumentException ex) {
            // expected
        } catch (Exception ex) {
            fail(ex.toString());
        }

        g = new Grid(1, 2);
        v = g.getVertex(0, 0);
        v = g.getVertex(0, 1);

        try {
            v = g.getVertex(1, 0);
            fail("must throw, but did not");
        } catch (IllegalArgumentException ex) {
            // expected
        } catch (Exception ex) {
            fail(ex.toString());
        }

        try {
            v = g.getVertex(1, 1);
            fail("must throw, but did not");
        } catch (IllegalArgumentException ex) {
            // expected
        } catch (Exception ex) {
            fail(ex.toString());
        }

        g = new Grid(3, 5);
        v = g.getVertex(0, 0);
        v = g.getVertex(0, 1);
        v = g.getVertex(0, 2);
        v = g.getVertex(0, 3);
        v = g.getVertex(0, 4);
        v = g.getVertex(1, 0);
        v = g.getVertex(1, 1);
        v = g.getVertex(1, 2);
        v = g.getVertex(1, 3);
        v = g.getVertex(1, 4);
        v = g.getVertex(2, 0);
        v = g.getVertex(2, 1);
        v = g.getVertex(2, 2);
        v = g.getVertex(2, 3);
        v = g.getVertex(2, 4);

        try {
            v = g.getVertex(2, 5);
            fail("must throw, but did not");
        } catch (IllegalArgumentException ex) {
            // expected
        } catch (Exception ex) {
            fail(ex.toString());
        }

        try {
            v = g.getVertex(1, 5);
            fail("must throw, but did not");
        } catch (IllegalArgumentException ex) {
            // expected
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    // TODO test size;
}
