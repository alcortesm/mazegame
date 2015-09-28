package mazegame.util.grid;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.NoSuchElementException;

import mazegame.util.Direction;

public class VertexTest {

    @Test
    public void ctorMustWork() {
        Grid g;
        g = new Grid(1, 1);
        new Vertex(g, 0, 0);
        g = new Grid(1, 2);
        new Vertex(g, 0, 0);
        new Vertex(g, 0, 1);
        g = new Grid(2, 1);
        new Vertex(g, 0, 0);
        new Vertex(g, 1, 0);
        g = new Grid(2, 2);
        new Vertex(g, 0, 0);
        new Vertex(g, 1, 0);
        new Vertex(g, 0, 1);
        new Vertex(g, 1, 1);
        g = new Grid(3, 5);
        new Vertex(g, 0, 0);
        new Vertex(g, 0, 1);
        new Vertex(g, 0, 2);
        new Vertex(g, 0, 3);
        new Vertex(g, 0, 4);
        new Vertex(g, 1, 0);
        new Vertex(g, 1, 1);
        new Vertex(g, 1, 2);
        new Vertex(g, 1, 3);
        new Vertex(g, 1, 4);
        new Vertex(g, 2, 0);
        new Vertex(g, 2, 1);
        new Vertex(g, 2, 2);
        new Vertex(g, 2, 3);
        new Vertex(g, 2, 4);
    }

    @Test
    public void ctorMustFail() {
        Grid g;
        g = new Grid(1, 1);
        try { new Vertex(g, 0, 1); fail(); } catch (Exception ex) {}
        try { new Vertex(g, 1, 1); fail(); } catch (Exception ex) {}
        try { new Vertex(g, 1, 1); fail(); } catch (Exception ex) {}
        try { new Vertex(g, 0, -1); fail(); } catch (Exception ex) {}
        try { new Vertex(g, -1, 0); fail(); } catch (Exception ex) {}
        g = new Grid(3, 5);
        try { new Vertex(g, 0, 5); fail(); } catch (Exception ex) {}
        try { new Vertex(g, 3, 0); fail(); } catch (Exception ex) {}
    }

    @Test
    public void isVisited() {
        Grid g;
        Vertex v;
        g = new Grid(3, 4);
        int rows = g.rows();
        int cols = g.cols();
        for (int r=0; r<rows; r++) {
            for (int c=0; c<cols; c++) {
                v = g.getVertex(r, c);
                if (v.isVisited()) {
                    fail();
                }
            }
        }
    }

    @Test
    public void setVisited() {
        Grid g;
        Vertex v;
        g = new Grid(16, 4);
        boolean[][] vi = {
            {false, false, false, false},
            {false, false, false, true},
            {false, false, true, false},
            {false, false, true, true},
            {false, true, false, false},
            {false, true, false, true},
            {false, true, true, false},
            {false, true, true, true},
            {true, false, false, false},
            {true, false, false, true},
            {true, false, true, false},
            {true, false, true, true},
            {true, true, false, false},
            {true, true, false, true},
            {true, true, true, false},
            {true, true, true, true},
        };
        for (int r=0; r<16; r++) {
            for (int c=0; c<4; c++) {
                v = g.getVertex(r, c);
                v.setVisited(vi[r][c]);
            }
        }
        for (int r=0; r<3; r++) {
            for (int c=0; c<4; c++) {
                v = g.getVertex(r, c);
                if (v.isVisited() != vi[r][c]) {
                    fail();
                }
            }
        }
    }

    @Test
    public void hasNeighbour() {
        Grid g;
        Vertex v;
        g = new Grid(1, 1);
        v = g.getVertex(0, 0);
        assertFalse(v.hasNeighbour(Direction.NORTH));
        assertFalse(v.hasNeighbour(Direction.SOUTH));
        assertFalse(v.hasNeighbour(Direction.WEST));
        assertFalse(v.hasNeighbour(Direction.EAST));

        g = new Grid(2, 2);
        v = g.getVertex(0, 0);
        assertFalse(v.hasNeighbour(Direction.NORTH));
        assertTrue(v.hasNeighbour(Direction.SOUTH));
        assertFalse(v.hasNeighbour(Direction.WEST));
        assertTrue(v.hasNeighbour(Direction.EAST));
        v = g.getVertex(0, 1);
        assertFalse(v.hasNeighbour(Direction.NORTH));
        assertTrue(v.hasNeighbour(Direction.SOUTH));
        assertTrue(v.hasNeighbour(Direction.WEST));
        assertFalse(v.hasNeighbour(Direction.EAST));
        v = g.getVertex(1, 0);
        assertTrue(v.hasNeighbour(Direction.NORTH));
        assertFalse(v.hasNeighbour(Direction.SOUTH));
        assertFalse(v.hasNeighbour(Direction.WEST));
        assertTrue(v.hasNeighbour(Direction.EAST));
        v = g.getVertex(1, 1);
        assertTrue(v.hasNeighbour(Direction.NORTH));
        assertFalse(v.hasNeighbour(Direction.SOUTH));
        assertTrue(v.hasNeighbour(Direction.WEST));
        assertFalse(v.hasNeighbour(Direction.EAST));

        g = new Grid(3, 4);
        v = g.getVertex(0, 0);
        assertFalse(v.hasNeighbour(Direction.NORTH));
        assertTrue(v.hasNeighbour(Direction.SOUTH));
        assertFalse(v.hasNeighbour(Direction.WEST));
        assertTrue(v.hasNeighbour(Direction.EAST));
        v = g.getVertex(0, 1);
        assertFalse(v.hasNeighbour(Direction.NORTH));
        assertTrue(v.hasNeighbour(Direction.SOUTH));
        assertTrue(v.hasNeighbour(Direction.WEST));
        assertTrue(v.hasNeighbour(Direction.EAST));
        v = g.getVertex(0, 2);
        assertFalse(v.hasNeighbour(Direction.NORTH));
        assertTrue(v.hasNeighbour(Direction.SOUTH));
        assertTrue(v.hasNeighbour(Direction.WEST));
        assertTrue(v.hasNeighbour(Direction.EAST));
        v = g.getVertex(0, 3);
        assertFalse(v.hasNeighbour(Direction.NORTH));
        assertTrue(v.hasNeighbour(Direction.SOUTH));
        assertTrue(v.hasNeighbour(Direction.WEST));
        assertFalse(v.hasNeighbour(Direction.EAST));
        v = g.getVertex(1, 0);
        assertTrue(v.hasNeighbour(Direction.NORTH));
        assertTrue(v.hasNeighbour(Direction.SOUTH));
        assertFalse(v.hasNeighbour(Direction.WEST));
        assertTrue(v.hasNeighbour(Direction.EAST));
        v = g.getVertex(1, 1);
        assertTrue(v.hasNeighbour(Direction.NORTH));
        assertTrue(v.hasNeighbour(Direction.SOUTH));
        assertTrue(v.hasNeighbour(Direction.WEST));
        assertTrue(v.hasNeighbour(Direction.EAST));
        v = g.getVertex(1, 2);
        assertTrue(v.hasNeighbour(Direction.NORTH));
        assertTrue(v.hasNeighbour(Direction.SOUTH));
        assertTrue(v.hasNeighbour(Direction.WEST));
        assertTrue(v.hasNeighbour(Direction.EAST));
        v = g.getVertex(1, 3);
        assertTrue(v.hasNeighbour(Direction.NORTH));
        assertTrue(v.hasNeighbour(Direction.SOUTH));
        assertTrue(v.hasNeighbour(Direction.WEST));
        assertFalse(v.hasNeighbour(Direction.EAST));
        v = g.getVertex(2, 0);
        assertTrue(v.hasNeighbour(Direction.NORTH));
        assertFalse(v.hasNeighbour(Direction.SOUTH));
        assertFalse(v.hasNeighbour(Direction.WEST));
        assertTrue(v.hasNeighbour(Direction.EAST));
        v = g.getVertex(2, 1);
        assertTrue(v.hasNeighbour(Direction.NORTH));
        assertFalse(v.hasNeighbour(Direction.SOUTH));
        assertTrue(v.hasNeighbour(Direction.WEST));
        assertTrue(v.hasNeighbour(Direction.EAST));
        v = g.getVertex(2, 2);
        assertTrue(v.hasNeighbour(Direction.NORTH));
        assertFalse(v.hasNeighbour(Direction.SOUTH));
        assertTrue(v.hasNeighbour(Direction.WEST));
        assertTrue(v.hasNeighbour(Direction.EAST));
        v = g.getVertex(2, 3);
        assertTrue(v.hasNeighbour(Direction.NORTH));
        assertFalse(v.hasNeighbour(Direction.SOUTH));
        assertTrue(v.hasNeighbour(Direction.WEST));
        assertFalse(v.hasNeighbour(Direction.EAST));
    }

    @Test
    public void getNeighbourMustFail() {
        Grid g;
        Vertex vs;
        Vertex vd;

        g = new Grid(1, 1);
        vs = g.getVertex(0, 0);
        try {
            vs.getNeighbour(Direction.NORTH);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        try {
            vs.getNeighbour(Direction.SOUTH);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        try {
            vs.getNeighbour(Direction.WEST);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        try {
            vs.getNeighbour(Direction.EAST);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected

        g = new Grid(1, 2);
        vs = g.getVertex(0, 0);
        try {
            vs.getNeighbour(Direction.NORTH);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        try {
            vs.getNeighbour(Direction.SOUTH);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        try {
            vs.getNeighbour(Direction.WEST);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        vs = g.getVertex(0, 1);
        try {
            vs.getNeighbour(Direction.NORTH);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        try {
            vs.getNeighbour(Direction.SOUTH);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        try {
            vs.getNeighbour(Direction.EAST);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected

        g = new Grid(2, 1);
        vs = g.getVertex(0, 0);
        try {
            vs.getNeighbour(Direction.NORTH);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        try {
            vs.getNeighbour(Direction.WEST);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        try {
            vs.getNeighbour(Direction.EAST);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        vs = g.getVertex(1, 0);
        try {
            vs.getNeighbour(Direction.SOUTH);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        try {
            vs.getNeighbour(Direction.WEST);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        try {
            vs.getNeighbour(Direction.EAST);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected

        g = new Grid(2, 2);
        vs = g.getVertex(0, 0);
        try {
            vs.getNeighbour(Direction.NORTH);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        try {
            vs.getNeighbour(Direction.WEST);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        vs = g.getVertex(0, 1);
        try {
            vs.getNeighbour(Direction.NORTH);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        try {
            vs.getNeighbour(Direction.EAST);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        vs = g.getVertex(1, 0);
        try {
            vs.getNeighbour(Direction.SOUTH);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        try {
            vs.getNeighbour(Direction.WEST);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        vs = g.getVertex(1, 1);
        try {
            vs.getNeighbour(Direction.SOUTH);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        try {
            vs.getNeighbour(Direction.EAST);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected

        g = new Grid(3, 3);
        vs = g.getVertex(0, 0);
        try {
            vs.getNeighbour(Direction.NORTH);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        try {
            vs.getNeighbour(Direction.WEST);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        vs = g.getVertex(0, 1);
        try {
            vs.getNeighbour(Direction.NORTH);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        vs = g.getVertex(0, 2);
        try {
            vs.getNeighbour(Direction.NORTH);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        try {
            vs.getNeighbour(Direction.EAST);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        vs = g.getVertex(1, 0);
        try {
            vs.getNeighbour(Direction.WEST);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        vs = g.getVertex(1, 2);
        try {
            vs.getNeighbour(Direction.EAST);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        vs = g.getVertex(2, 0);
        try {
            vs.getNeighbour(Direction.SOUTH);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        try {
            vs.getNeighbour(Direction.WEST);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        vs = g.getVertex(2, 1);
        try {
            vs.getNeighbour(Direction.SOUTH);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        vs = g.getVertex(2, 2);
        try {
            vs.getNeighbour(Direction.SOUTH);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
        try {
            vs.getNeighbour(Direction.EAST);
            fail();
        } catch (NoSuchElementException ex) {
        } // expected
    }

    @Test
    public void equalsMustWork() {
        Grid g;
        Vertex v1;
        Vertex v2;

        g= new Grid(3, 4);
        v1 = g.getVertex(0, 0);
        v2 = g.getVertex(0, 0);
        assertTrue(v1.equals(v2));
        v2 = g.getVertex(0, 1);
        assertFalse(v1.equals(v2));
        v2 = g.getVertex(0, 2);
        assertFalse(v1.equals(v2));
        v2 = g.getVertex(0, 3);
        assertFalse(v1.equals(v2));
        v2 = g.getVertex(1, 0);
        assertFalse(v1.equals(v2));
        v2 = g.getVertex(1, 1);
        assertFalse(v1.equals(v2));
        v2 = g.getVertex(1, 2);
        assertFalse(v1.equals(v2));
        v2 = g.getVertex(1, 3);
        assertFalse(v1.equals(v2));
        v2 = g.getVertex(2, 0);
        assertFalse(v1.equals(v2));
        v2 = g.getVertex(2, 1);
        assertFalse(v1.equals(v2));
        v2 = g.getVertex(2, 2);
        assertFalse(v1.equals(v2));
        v2 = g.getVertex(2, 3);
        assertFalse(v1.equals(v2));

        v1 = g.getVertex(1, 2);
        v2 = g.getVertex(0, 0);
        assertFalse(v1.equals(v2));
        v2 = g.getVertex(0, 1);
        assertFalse(v1.equals(v2));
        v2 = g.getVertex(0, 2);
        assertFalse(v1.equals(v2));
        v2 = g.getVertex(0, 3);
        assertFalse(v1.equals(v2));
        v2 = g.getVertex(1, 0);
        assertFalse(v1.equals(v2));
        v2 = g.getVertex(1, 1);
        assertFalse(v1.equals(v2));
        v2 = g.getVertex(1, 2);
        assertTrue(v1.equals(v2));
        v2 = g.getVertex(1, 3);
        assertFalse(v1.equals(v2));
        v2 = g.getVertex(2, 0);
        assertFalse(v1.equals(v2));
        v2 = g.getVertex(2, 1);
        assertFalse(v1.equals(v2));
        v2 = g.getVertex(2, 2);
        assertFalse(v1.equals(v2));
        v2 = g.getVertex(2, 3);
        assertFalse(v1.equals(v2));

        Grid g2 = new Grid(3, 4);
        v2 = g2.getVertex(1, 2);
        assertFalse(v1.equals(v2));
    }

    @Test
    public void getNeighbourMustWork() {
        Grid g;
        Vertex v;

        g = new Grid(3, 4);
        v = g.getVertex(0, 0);
        assertTrue(v.getNeighbour(Direction.EAST).equals(
                    g.getVertex(0, 1)));
        assertTrue(v.getNeighbour(Direction.SOUTH).equals(
                    g.getVertex(1, 0)));
        v = g.getVertex(0, 1);
        assertTrue(v.getNeighbour(Direction.EAST).equals(
                    g.getVertex(0, 2)));
        assertTrue(v.getNeighbour(Direction.SOUTH).equals(
                    g.getVertex(1, 1)));
        assertTrue(v.getNeighbour(Direction.WEST).equals(
                    g.getVertex(0, 0)));
        v = g.getVertex(0, 2);
        assertTrue(v.getNeighbour(Direction.EAST).equals(
                    g.getVertex(0, 3)));
        assertTrue(v.getNeighbour(Direction.SOUTH).equals(
                    g.getVertex(1, 2)));
        assertTrue(v.getNeighbour(Direction.WEST).equals(
                    g.getVertex(0, 1)));
        v = g.getVertex(0, 3);
        assertTrue(v.getNeighbour(Direction.SOUTH).equals(
                    g.getVertex(1, 3)));
        assertTrue(v.getNeighbour(Direction.WEST).equals(
                    g.getVertex(0, 2)));

        v = g.getVertex(1, 0);
        assertTrue(v.getNeighbour(Direction.NORTH).equals(
                    g.getVertex(0, 0)));
        assertTrue(v.getNeighbour(Direction.EAST).equals(
                    g.getVertex(1, 1)));
        assertTrue(v.getNeighbour(Direction.SOUTH).equals(
                    g.getVertex(2, 0)));
        v = g.getVertex(1, 1);
        assertTrue(v.getNeighbour(Direction.NORTH).equals(
                    g.getVertex(0, 1)));
        assertTrue(v.getNeighbour(Direction.EAST).equals(
                    g.getVertex(1, 2)));
        assertTrue(v.getNeighbour(Direction.SOUTH).equals(
                    g.getVertex(2, 1)));
        assertTrue(v.getNeighbour(Direction.WEST).equals(
                    g.getVertex(1, 0)));
        v = g.getVertex(1, 2);
        assertTrue(v.getNeighbour(Direction.NORTH).equals(
                    g.getVertex(0, 2)));
        assertTrue(v.getNeighbour(Direction.EAST).equals(
                    g.getVertex(1, 3)));
        assertTrue(v.getNeighbour(Direction.SOUTH).equals(
                    g.getVertex(2, 2)));
        assertTrue(v.getNeighbour(Direction.WEST).equals(
                    g.getVertex(1, 1)));
        v = g.getVertex(1, 3);
        assertTrue(v.getNeighbour(Direction.NORTH).equals(
                    g.getVertex(0, 3)));
        assertTrue(v.getNeighbour(Direction.SOUTH).equals(
                    g.getVertex(2, 3)));
        assertTrue(v.getNeighbour(Direction.WEST).equals(
                    g.getVertex(1, 2)));

        v = g.getVertex(2, 0);
        assertTrue(v.getNeighbour(Direction.EAST).equals(
                    g.getVertex(2, 1)));
        assertTrue(v.getNeighbour(Direction.NORTH).equals(
                    g.getVertex(1, 0)));
        v = g.getVertex(2, 1);
        assertTrue(v.getNeighbour(Direction.EAST).equals(
                    g.getVertex(2, 2)));
        assertTrue(v.getNeighbour(Direction.NORTH).equals(
                    g.getVertex(1, 1)));
        assertTrue(v.getNeighbour(Direction.WEST).equals(
                    g.getVertex(2, 0)));
        v = g.getVertex(2, 2);
        assertTrue(v.getNeighbour(Direction.EAST).equals(
                    g.getVertex(2, 3)));
        assertTrue(v.getNeighbour(Direction.NORTH).equals(
                    g.getVertex(1, 2)));
        assertTrue(v.getNeighbour(Direction.WEST).equals(
                    g.getVertex(2, 1)));
        v = g.getVertex(2, 3);
        assertTrue(v.getNeighbour(Direction.NORTH).equals(
                    g.getVertex(1, 3)));
        assertTrue(v.getNeighbour(Direction.WEST).equals(
                    g.getVertex(2, 2)));
    }

    @Test
    public void isNeighbourMustWork() {
        Grid g;
        Vertex v1;
        Vertex v2;

        g = new Grid(1, 1);
        v1 = g.getVertex(0, 0);
        v2 = g.getVertex(0, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));

        g = new Grid(3, 4);
        v1 = g.getVertex(0, 0);
        v2 = g.getVertex(0, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 1);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 0);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));

        v1 = g.getVertex(0, 1);
        v2 = g.getVertex(0, 0);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 2);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 1);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));

        v1 = g.getVertex(0, 2);
        v2 = g.getVertex(0, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 1);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 3);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 2);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));

        v1 = g.getVertex(0, 3);
        v2 = g.getVertex(0, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 2);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 3);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));

        v1 = g.getVertex(1, 0);
        v2 = g.getVertex(0, 0);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 1);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 0);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));

        v1 = g.getVertex(1, 1);
        v2 = g.getVertex(0, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 1);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 0);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 2);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 1);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));

        v1 = g.getVertex(1, 2);
        v2 = g.getVertex(0, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 2);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 1);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 3);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 2);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));

        v1 = g.getVertex(1, 3);
        v2 = g.getVertex(0, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 3);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 2);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 3);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));

        v1 = g.getVertex(2, 0);
        v2 = g.getVertex(0, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 0);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 1);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));

        v1 = g.getVertex(2, 1);
        v2 = g.getVertex(0, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 1);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 0);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 2);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));

        v1 = g.getVertex(2, 2);
        v2 = g.getVertex(0, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 2);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 1);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 3);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));

        v1 = g.getVertex(2, 3);
        v2 = g.getVertex(0, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(0, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 2);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(1, 3);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 0);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 1);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 2);
        assertTrue(v1.isNeighbour(v2));
        assertTrue(v2.isNeighbour(v1));
        v2 = g.getVertex(2, 3);
        assertFalse(v1.isNeighbour(v2));
        assertFalse(v2.isNeighbour(v1));
    }

    @Test
    public void directionToNeighbourMustWork() {
        Grid g;
        Vertex v1;
        Vertex v2;

        g = new Grid(1, 1);
        v1 = g.getVertex(0, 0);
        v2 = g.getVertex(0, 0);
        try { v1.directionToNeighbour(v2); fail();
        } catch (NoSuchElementException ex) {} // expected
        try { v2.directionToNeighbour(v1); fail();
        } catch (NoSuchElementException ex) {} // expected

        g = new Grid(1, 2);
        v1 = g.getVertex(0, 0);
        v2 = g.getVertex(0, 0);
        try { v1.directionToNeighbour(v2); fail();
        } catch (NoSuchElementException ex) {} // expected
        try { v2.directionToNeighbour(v1); fail();
        } catch (NoSuchElementException ex) {} // expected
        v2 = g.getVertex(0, 1);
        assertTrue(v1.directionToNeighbour(v2) == Direction.EAST);
        assertTrue(v2.directionToNeighbour(v1) == Direction.WEST);

        g = new Grid(2, 1);
        v1 = g.getVertex(0, 0);
        v2 = g.getVertex(0, 0);
        try { v1.directionToNeighbour(v2); fail();
        } catch (NoSuchElementException ex) {} // expected
        try { v2.directionToNeighbour(v1); fail();
        } catch (NoSuchElementException ex) {} // expected
        v2 = g.getVertex(1, 0);
        assertTrue(v1.directionToNeighbour(v2) == Direction.SOUTH);
        assertTrue(v2.directionToNeighbour(v1) == Direction.NORTH);

        g = new Grid(2, 2);
        v1 = g.getVertex(0, 0);
        v2 = g.getVertex(0, 0);
        try { v1.directionToNeighbour(v2); fail();
        } catch (NoSuchElementException ex) {} // expected
        try { v2.directionToNeighbour(v1); fail();
        } catch (NoSuchElementException ex) {} // expected
        v2 = g.getVertex(0, 1);
        assertTrue(v1.directionToNeighbour(v2) == Direction.EAST);
        assertTrue(v2.directionToNeighbour(v1) == Direction.WEST);
        v2 = g.getVertex(1, 0);
        assertTrue(v1.directionToNeighbour(v2) == Direction.SOUTH);
        assertTrue(v2.directionToNeighbour(v1) == Direction.NORTH);
        v2 = g.getVertex(1, 1);
        try { v1.directionToNeighbour(v2); fail();
        } catch (NoSuchElementException ex) {} // expected
        try { v2.directionToNeighbour(v1); fail();
        } catch (NoSuchElementException ex) {} // expected
        v1 = g.getVertex(1, 1);
        v2 = g.getVertex(0, 1);
        assertTrue(v1.directionToNeighbour(v2) == Direction.NORTH);
        assertTrue(v2.directionToNeighbour(v1) == Direction.SOUTH);
        v2 = g.getVertex(1, 0);
        assertTrue(v1.directionToNeighbour(v2) == Direction.WEST);
        assertTrue(v2.directionToNeighbour(v1) == Direction.EAST);

        g = new Grid(3, 4);
        v1 = g.getVertex(1, 2);
        v2 = g.getVertex(0, 0);
        try { v1.directionToNeighbour(v2); fail();
        } catch (NoSuchElementException ex) {} // expected
        try { v2.directionToNeighbour(v1); fail();
        } catch (NoSuchElementException ex) {} // expected
        v2 = g.getVertex(0, 1);
        try { v1.directionToNeighbour(v2); fail();
        } catch (NoSuchElementException ex) {} // expected
        try { v2.directionToNeighbour(v1); fail();
        } catch (NoSuchElementException ex) {} // expected
        v2 = g.getVertex(0, 2);
        assertTrue(v1.directionToNeighbour(v2) == Direction.NORTH);
        assertTrue(v2.directionToNeighbour(v1) == Direction.SOUTH);
        v2 = g.getVertex(0, 3);
        try { v1.directionToNeighbour(v2); fail();
        } catch (NoSuchElementException ex) {} // expected
        try { v2.directionToNeighbour(v1); fail();
        } catch (NoSuchElementException ex) {} // expected

        v2 = g.getVertex(1, 0);
        try { v1.directionToNeighbour(v2); fail();
        } catch (NoSuchElementException ex) {} // expected
        try { v2.directionToNeighbour(v1); fail();
        } catch (NoSuchElementException ex) {} // expected
        v2 = g.getVertex(1, 1);
        assertTrue(v1.directionToNeighbour(v2) == Direction.WEST);
        assertTrue(v2.directionToNeighbour(v1) == Direction.EAST);
        v2 = g.getVertex(1, 2);
        try { v1.directionToNeighbour(v2); fail();
        } catch (NoSuchElementException ex) {} // expected
        try { v2.directionToNeighbour(v1); fail();
        } catch (NoSuchElementException ex) {} // expected
        v2 = g.getVertex(1, 3);
        assertTrue(v1.directionToNeighbour(v2) == Direction.EAST);
        assertTrue(v2.directionToNeighbour(v1) == Direction.WEST);

        v2 = g.getVertex(2, 0);
        try { v1.directionToNeighbour(v2); fail();
        } catch (NoSuchElementException ex) {} // expected
        try { v2.directionToNeighbour(v1); fail();
        } catch (NoSuchElementException ex) {} // expected
        v2 = g.getVertex(2, 1);
        try { v1.directionToNeighbour(v2); fail();
        } catch (NoSuchElementException ex) {} // expected
        try { v2.directionToNeighbour(v1); fail();
        } catch (NoSuchElementException ex) {} // expected
        v2 = g.getVertex(2, 2);
        assertTrue(v1.directionToNeighbour(v2) == Direction.SOUTH);
        assertTrue(v2.directionToNeighbour(v1) == Direction.NORTH);
        v2 = g.getVertex(2, 3);
        try { v1.directionToNeighbour(v2); fail();
        } catch (NoSuchElementException ex) {} // expected
        try { v2.directionToNeighbour(v1); fail();
        } catch (NoSuchElementException ex) {} // expected
    }

    private void assertConnectionsFullSet(int rows, int cols) {
        Grid g;
        Vertex v1;
        Vertex v2;
        Direction d;

        g = new Grid(rows, cols);
        for (int r=0; r<rows; r++) {
            for (int c=0; c<cols; c++) {
                v1 = g.getVertex(r, c);
                for (int rr=0; rr<rows; rr++) {
                    for (int cc=0; cc<cols; cc++) {
                        v2 = g.getVertex(rr, cc);
                        if (v1.isNeighbour(v2)) {
                            assertTrue(v1 + " " + v2,
                                    v1.isConnected(v2));
                            assertTrue(v1 + " " + v2,
                                    v1.isConnected(v2));
                        } else {
                            assertFalse(v1 + " " + v2,
                                    v1.isConnected(v2));
                        }
                    }
                }
            }
        }
    }

    @Test
    public void isConnectedMustWorkOnFullConnected() {
        assertConnectionsFullSet(1, 1);
        assertConnectionsFullSet(1, 2);
        assertConnectionsFullSet(2, 1);
        assertConnectionsFullSet(2, 2);
        assertConnectionsFullSet(11, 7);
    }

    @Test
    public void setConnectionMustWork() {
        Grid g;
        Vertex v1;
        Vertex v2;

        // *--*--*
        //    |
        // *  *--*
        // |  |  |
        // *--*  *
        g = new Grid(3, 3);
        v1 = g.getVertex(0, 0);
        v1.setConnection(Direction.SOUTH, false);
        v1 = g.getVertex(1, 2);
        v1.setConnection(Direction.NORTH, false);
        v1 = g.getVertex(1, 1);
        v1.setConnection(Direction.WEST, false);
        v1 = g.getVertex(2, 1);
        v1.setConnection(Direction.EAST, false);

        v1 = g.getVertex(0, 0);
        v2 = g.getVertex(0, 1);
        assertTrue(v1.isConnected(v2));
        v2 = g.getVertex(1, 0);
        assertFalse(v1.isConnected(v2));

        v1 = g.getVertex(0, 1);
        v2 = g.getVertex(0, 0);
        assertTrue(v1.isConnected(v2));
        v2 = g.getVertex(0, 1);
        assertFalse(v1.isConnected(v2));
        v2 = g.getVertex(0, 2);
        assertTrue(v1.isConnected(v2));

        v1 = g.getVertex(0, 2);
        v2 = g.getVertex(0, 1);
        assertTrue(v1.isConnected(v2));
        v2 = g.getVertex(1, 2);
        assertFalse(v1.isConnected(v2));

        v1 = g.getVertex(1, 0);
        v2 = g.getVertex(0, 0);
        assertFalse(v1.isConnected(v2));
        v2 = g.getVertex(1, 1);
        assertFalse(v1.isConnected(v2));
        v2 = g.getVertex(2, 0);
        assertTrue(v1.isConnected(v2));

        v1 = g.getVertex(1, 1);
        v2 = g.getVertex(0, 1);
        assertTrue(v1.isConnected(v2));
        v2 = g.getVertex(1, 0);
        assertFalse(v1.isConnected(v2));
        v2 = g.getVertex(2, 1);
        assertTrue(v1.isConnected(v2));
        v2 = g.getVertex(1, 2);
        assertTrue(v1.isConnected(v2));

        v1 = g.getVertex(1, 2);
        v2 = g.getVertex(0, 2);
        assertFalse(v1.isConnected(v2));
        v2 = g.getVertex(1, 1);
        assertTrue(v1.isConnected(v2));
        v2 = g.getVertex(2, 2);
        assertTrue(v1.isConnected(v2));

        v1 = g.getVertex(2, 0);
        v2 = g.getVertex(1, 0);
        assertTrue(v1.isConnected(v2));
        v2 = g.getVertex(2, 1);
        assertTrue(v1.isConnected(v2));

        v1 = g.getVertex(2, 1);
        v2 = g.getVertex(2, 0);
        assertTrue(v1.isConnected(v2));
        v2 = g.getVertex(1, 1);
        assertTrue(v1.isConnected(v2));
        v2 = g.getVertex(2, 2);
        assertFalse(v1.isConnected(v2));

        v1 = g.getVertex(2, 2);
        v2 = g.getVertex(2, 1);
        assertFalse(v1.isConnected(v2));
        v2 = g.getVertex(1, 2);
        assertTrue(v1.isConnected(v2));
    }
}
