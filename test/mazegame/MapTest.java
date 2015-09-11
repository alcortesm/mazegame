package mazegame;

import org.junit.Test;
import static org.junit.Assert.*;
import java.text.ParseException;

public class MapTest {

    @Test
    public void MinRowsMustBePositive() {
        assertTrue(Map.getMinRows() > 0);
    }

    @Test
    public void MinColumnsMustBePositive() {
        assertTrue(Map.getMinColumns() > 0);
    }

    @Test(expected=NullPointerException.class)
    public void CtorShouldThrowOnNull() {
        new Map(null);
    }

    private Tile[][] buildTileArray() {
        return buildTileArray(
                reasonableDimForTest(), reasonableDimForTest());
    }

    private int reasonableDimForTest() {
        int maxDim = Math.max(Map.getMinRows(), Map.getMinColumns());
        return Math.max(3, maxDim);
    }

    private Tile[][] buildTileArray(int rows, int columns) {
        Tile[][] a = new Tile[rows][columns];
        for (int r=0; r<a.length; r++) {
            for (int c=0; c<a[r].length; c++) {
                a[r][c] = new Wall();
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
        Tile[][] a = new Tile[0][Map.getMinColumns()];
        new Map(a);
    }

    @Test(expected=IllegalArgumentException.class)
    public void CtorShouldThrowOnTooFewRows() {
        Tile[][] a = buildTileArray(
                Map.getMinRows()-1, Map.getMinColumns());
        new Map(a);
    }

    @Test(expected=IllegalArgumentException.class)
    public void CtorShouldThrowOnTooFewColumns() {
        Tile[][] a = buildTileArray(
                Map.getMinRows(), Map.getMinColumns()-1);
        new Map(a);
    }

    @Test(expected=IllegalArgumentException.class)
    public void CtorShouldThrowOnNonRectangular0() {
        Tile[][] a = buildTileArray();
        a[0] = new Tile[Map.getMinColumns()+1];
        for (int c=0; c<a[0].length; c++) {
            a[0][c] = new Wall();
        }
        new Map(a);
    }

    @Test(expected=IllegalArgumentException.class)
    public void CtorShouldThrowOnNonRectangular1() {
        Tile[][] a = buildTileArray();
        a[1] = new Tile[Map.getMinColumns()+1];
        for (int c=0; c<a[1].length; c++) {
            a[1][c] = new Wall();
        }
        new Map(a);
    }

    @Test(expected=IllegalArgumentException.class)
    public void CtorShouldThrowOnNonRectangular2() {
        Tile[][] a = buildTileArray();
        a[2] = new Tile[Map.getMinColumns()+1];
        for (int c=0; c<a[2].length; c++) {
            a[2][c] = new Wall();
        }
        new Map(a);
    }

    @Test
    public void ShouldReturnCorrectNums() {
        {
            int rows = Map.getMinRows();
            int columns = Map.getMinColumns();
            Map m = new Map(buildTileArray(rows, columns));
            assertTrue(m.getNumRows() == rows);
            assertTrue(m.getNumColumns() == columns);
        }

        {
            int rows = Map.getMinRows()+1;
            int columns = Map.getMinColumns();
            Map m = new Map(buildTileArray(rows, columns));
            assertTrue(m.getNumRows() == rows);
            assertTrue(m.getNumColumns() == columns);
        }

        {
            int rows = Map.getMinRows();
            int columns = Map.getMinColumns()+1;
            Map m = new Map(buildTileArray(rows, columns));
            assertTrue(m.getNumRows() == rows);
            assertTrue(m.getNumColumns() == columns);
        }

        {
            int rows = 2*Map.getMinRows();
            int columns = 3*Map.getMinColumns();
            Map m = new Map(buildTileArray(rows, columns));
            assertTrue(m.getNumRows() == rows);
            assertTrue(m.getNumColumns() == columns);
        }
    }

    @Test
    public void shouldGetTiles() {
        Tile[][] a = new Tile[Map.getMinRows()][Map.getMinColumns()];
        for (int r = 0; r<a.length; r++) {
            for (int c = 0; c<a[r].length; c++) {
                a[r][c] = new Wall();
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
}
