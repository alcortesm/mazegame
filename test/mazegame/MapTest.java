package mazegame;

import org.junit.Test;
import static org.junit.Assert.*;
import java.text.ParseException;

public class MapTest {

    private static final int REASONABLE_DIM = 3;

    @Test(expected=NullPointerException.class)
    public void CtorShouldThrowOnNull() {
        new Map(null);
    }

    private Tile[][] buildTileArray() {
        return buildTileArray(5, 5);
    }

    private Tile[][] buildTileArray(int rows, int columns) {
        Tile[][] a = new Tile[rows][columns];
        for (int r=0; r<a.length; r++) {
            for (int c=0; c<a[r].length; c++) {
                a[r][c] = new Space();
            }
        }
        return a;
    }

    @Test
    public void ShuldNotThrowWithCorrect() {
        new Map(buildTileArray());
    }

    @Test(expected=NullPointerException.class)
    public void CtorShouldThrowOnNullRow1() {
        Tile[][] a = buildTileArray();
        a[0] = null;
        new Map(a);
    }

    @Test(expected=NullPointerException.class)
    public void CtorShouldThrowOnNullRow2() {
        Tile[][] a = buildTileArray();
        a[1] = null;
        new Map(a);
    }

    @Test(expected=NullPointerException.class)
    public void CtorShouldThrowOnNullLastRow3() {
        Tile[][] a = buildTileArray();
        a[2] = null;
        new Map(a);
    }

    @Test(expected=NullPointerException.class)
    public void CtorShouldThrowOnNullElements1() {
        Tile[][] a = buildTileArray();
        a[0][2] = null;
        new Map(a);
    }

    @Test(expected=NullPointerException.class)
    public void CtorShouldThrowOnNullElements2() {
        Tile[][] a = buildTileArray();
        a[1][2] = null;
        new Map(a);
    }

    @Test(expected=NullPointerException.class)
    public void CtorShouldThrowOnNullElements3() {
        Tile[][] a = buildTileArray();
        a[2][1] = null;
        new Map(a);
    }

    @Test(expected=IllegalArgumentException.class)
    public void CtorShouldThrowOnZeroRows() {
        Tile[][] a = new Tile[0][1];
        new Map(a);
    }

    @Test(expected=IllegalArgumentException.class)
    public void CtorShouldThrowOnZeroColumns() {
        Tile[][] a = new Tile[1][0];
        new Map(a);
    }

    @Test(expected=IllegalArgumentException.class)
    public void CtorShouldThrowOnNonRectangular0() {
        Tile[][] a = buildTileArray();
        a[0] = new Tile[2];
        for (int c=0; c<a[0].length; c++) {
            a[0][c] = new Space();
        }
        new Map(a);
    }

    @Test(expected=IllegalArgumentException.class)
    public void CtorShouldThrowOnNonRectangular1() {
        Tile[][] a = buildTileArray();
        a[1] = new Tile[2];
        for (int c=0; c<a[1].length; c++) {
            a[1][c] = new Space();
        }
        new Map(a);
    }

    @Test(expected=IllegalArgumentException.class)
    public void CtorShouldThrowOnNonRectangular2() {
        Tile[][] a = buildTileArray();
        a[2] = new Tile[2];
        for (int c=0; c<a[2].length; c++) {
            a[2][c] = new Space();
        }
        new Map(a);
    }

    @Test
    public void ShouldReturnCorrectNums() {
        {
            int rows = 1;
            int columns = 1;
            Map m = new Map(buildTileArray(rows, columns));
            assertTrue(m.getNumRows() == rows);
            assertTrue(m.getNumColumns() == columns);
        }

        {
            int rows = 2;
            int columns = 1;
            Map m = new Map(buildTileArray(rows, columns));
            assertTrue(m.getNumRows() == rows);
            assertTrue(m.getNumColumns() == columns);
        }

        {
            int rows = 1;
            int columns = 2;
            Map m = new Map(buildTileArray(rows, columns));
            assertTrue(m.getNumRows() == rows);
            assertTrue(m.getNumColumns() == columns);
        }

        {
            int rows = 23;
            int columns = 54;
            Map m = new Map(buildTileArray(rows, columns));
            assertTrue(m.getNumRows() == rows);
            assertTrue(m.getNumColumns() == columns);
        }
    }

    @Test
    public void shouldGetTiles() {
        Tile[][] a = new Tile[1][1];
        for (int r = 0; r<a.length; r++) {
            for (int c = 0; c<a[r].length; c++) {
                a[r][c] = new Space();
            }
        }
        Map m = new Map(a);
        for (int r = 0; r<a.length; r++) {
            for (int c = 0; c<a[r].length; c++) {
                assertTrue("row " + r + ", column " + c,
                        a[r][c] == m.getTile(r, c));
            }
        }
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowIfNoWalkables() {
         Tile[][] a = new Tile[12][15];
        for (int r = 0; r<a.length; r++) {
            for (int c = 0; c<a[r].length; c++) {
                a[r][c] = new Wall();
            }
        }
        Map m = new Map(a);
    }
}
