package mazegame.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayTest {

    @Test
    public void hasNullOk() {
        Object[][] a;
        Object o = new Object();
        {
            a = new Object[1][1];
            assertTrue(Array.hasNull(a));
        }
        {
            a = new Object[2][2];
            assertTrue(Array.hasNull(a));
        }
        {
            a = new Object[40][12];
            assertTrue(Array.hasNull(a));
        }
        {
            a = new Object[40][12];
            assertTrue(Array.hasNull(a));
        }
        {
            a = new Object[][]{
                {null, o}
            };
            assertTrue(Array.hasNull(a));
        }
        {
            a = new Object[][]{
                {o, null}
            };
            assertTrue(Array.hasNull(a));
        }
        {
            a = new Object[][]{
                {null},
                {o}
            };
            assertTrue(Array.hasNull(a));
        }
        {
            a = new Object[][]{
                {o},
                {null}
            };
            assertTrue(Array.hasNull(a));
        }
        {
            a = new Object[][]{
                {o, o, o},
                {o, null},
                {o, o, o, o, o}
            };
            assertTrue(Array.hasNull(a));
        }
        {
            a = new Object[][]{
                {o, o, o},
                {o, o},
                {o, o, o, o, null, o}
            };
            assertTrue(Array.hasNull(a));
        }
        {
            a = new Object[][]{
                {o, o, o},
                {o, o},
                {o, o, o},
                {o, o},
                {o, o, o},
                {o, o},
                {o, o, o, o, o, null}
            };
            assertTrue(Array.hasNull(a));
        }
        {
            a = new Object[][]{
                {o, o, o},
                {o, o},
                {null},
                {o, o},
                {o, o, o},
                {o, o},
                {o, o, o, o, o, o}
            };
            assertTrue(Array.hasNull(a));
        }
        {
            a = new Object[][]{
                {o, o, o},
                {o, o},
                {o, o, o, o, o},
                {},
                {o, o, o},
                {o, o},
                {o, o, null, o, o, o}
            };
            assertTrue(Array.hasNull(a));
        }
        {
            a = new Object[][]{
                {},
                {o, o},
                {o, o, o, o, o},
                {},
                {o, o, o},
                {o, o},
                {o, o, null, o, o, o}
            };
            assertTrue(Array.hasNull(a));
        }
        {
            a = new Object[][]{
                {o, o, o, o, o, o, null, o, o},
                {},
            };
            assertTrue(Array.hasNull(a));
        }
        {
            a = new Object[][]{
                {o, o, o, o},
                {o, null, o, o},
                {o, o, null, o},
                {null, o, o, o},
            };
            assertTrue(Array.hasNull(a));
        }
    }

    @Test
    public void isRectOk() {
        Object[][] a;
        {
            a = new Object[1][1];
            assertTrue(Array.isRect(a));
        }
        {
            a = new Object[1][2];
            assertTrue(Array.isRect(a));
        }
        {
            a = new Object[2][1];
            assertTrue(Array.isRect(a));
        }
        {
            a = new Object[2][2];
            assertTrue(Array.isRect(a));
        }
        {
            a = new Object[3][5];
            assertTrue(Array.isRect(a));
        }
        {
            a = new Object[1][15];
            assertTrue(Array.isRect(a));
        }
        {
            a = new Object[15][1];
            assertTrue(Array.isRect(a));
        }
    }

    @Test(expected=NullPointerException.class)
    public void isRectMustThrowOnNull() {
        Array.isRect(null);
    }

    @Test
    public void isRectMustDetectZeroDim() {
        Object[][] a;
        {
            a = new Object[0][0];
            assertFalse(Array.isRect(a));
        }
    }

    @Test
    public void isRectMustDetectZeroRows() {
        Object[][] a;
        {
            a = new Object[0][1];
            assertFalse(Array.isRect(a));
        }
        {
            a = new Object[0][13];
            assertFalse(Array.isRect(a));
        }
    }

    @Test
    public void isRectMustDetectZeroCols() {
        Object[][] a;
        {
            a = new Object[1][0];
            assertFalse(Array.isRect(a));
        }
        {
            a = new Object[13][0];
            assertFalse(Array.isRect(a));
        }
    }

    @Test
    public void isRectMustDetectNonRects() {
        Object[][] a;
        {
            a = new Object[][]{
                {},
                {null}
            };
            assertFalse(Array.isRect(a));
        }
        {
            a = new Object[][]{
                {null},
                {}
            };
            assertFalse(Array.isRect(a));
        }
        {
            a = new Object[][]{
                {null},
                {null, null}
            };
            assertFalse(Array.isRect(a));
        }
        {
            a = new Object[][]{
                {null},
                {null, null},
                {null, null, null}
            };
            assertFalse(Array.isRect(a));
        }
        {
            a = new Object[][]{
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null, null},
            };
            assertFalse(Array.isRect(a));
        }
        {
            a = new Object[][]{
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null},
            };
            assertFalse(Array.isRect(a));
        }
        {
            a = new Object[][]{
                {null, null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
            };
            assertFalse(Array.isRect(a));
        }
        {
            a = new Object[][]{
                {null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
            };
            assertFalse(Array.isRect(a));
        }
        {
            a = new Object[][]{
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
            };
            assertFalse(Array.isRect(a));
        }
        {
            a = new Object[][]{
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
            };
            assertFalse(Array.isRect(a));
        }
        {
            a = new Object[][]{
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {},
                {null, null, null},
                {null, null, null},
                {null, null, null},
            };
            assertFalse(Array.isRect(a));
        }
        {
            a = new Object[][]{
                {},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
            };
            assertFalse(Array.isRect(a));
        }
        {
            a = new Object[][]{
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {}
            };
            assertFalse(Array.isRect(a));
        }
    }
}
