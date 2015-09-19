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

    @Test
    public void firstIndexOfMustWork() {
        String[] a;
        int p;
        String t;
        int expected;
        int output;
        {
            a = new String[]{"a"};
            p = 0;
            t = "a";
            expected = 0;
            output = Array.firstIndexOf(t, a, p);
            assertTrue("expected = " + expected +
                    ", output = " + output,
                    expected == output);
        }
        {
            a = new String[]{"a"};
            p = 0;
            t = "z";
            expected = -1;
            output = Array.firstIndexOf(t, a, p);
            assertTrue("expected = " + expected +
                    ", output = " + output,
                    expected == output);
        }
        {
            a = new String[]{"a", "b"};
            p = 0;
            t = "a";
            expected = 0;
            output = Array.firstIndexOf(t, a, p);
            assertTrue("expected = " + expected +
                    ", output = " + output,
                    expected == output);
        }
        {
            a = new String[]{"a", "b"};
            p = 0;
            t = "b";
            expected = 1;
            output = Array.firstIndexOf(t, a, p);
            assertTrue("expected = " + expected +
                    ", output = " + output,
                    expected == output);
        }
        {
            a = new String[]{"a", "b"};
            p = 0;
            t = "z";
            expected = -1;
            output = Array.firstIndexOf(t, a, p);
            assertTrue("expected = " + expected +
                    ", output = " + output,
                    expected == output);
        }
        {
            a = new String[]{"a", "b"};
            p = 1;
            t = "a";
            expected = -1;
            output = Array.firstIndexOf(t, a, p);
            assertTrue("expected = " + expected +
                    ", output = " + output,
                    expected == output);
        }
        {
            a = new String[]{"a", "b"};
            p = 1;
            t = "b";
            expected = 1;
            output = Array.firstIndexOf(t, a, p);
            assertTrue("expected = " + expected +
                    ", output = " + output,
                    expected == output);
        }
        {
            a = new String[]{"a", "b"};
            p = 1;
            t = "z";
            expected = -1;
            output = Array.firstIndexOf(t, a, p);
            assertTrue("expected = " + expected +
                    ", output = " + output,
                    expected == output);
        }
    }

    @Test(expected=NullPointerException.class)
    public void firstIndexOfMustThrowOnNullTarget() {
        Array.firstIndexOf(null, new String[]{"a", "b"}, 0);
    }

    @Test(expected=NullPointerException.class)
    public void firstIndexOfMustThrowOnNullArray() {
        Array.firstIndexOf("a", null, 0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void firstIndexOfMustThrowOnNegativeIndex1() {
        Array.firstIndexOf("a", new String[]{"a", "b"}, -1);
    }

    @Test(expected=IllegalArgumentException.class)
    public void firstIndexOfMustThrowOnNegativeIndex2() {
        Array.firstIndexOf("a", new String[]{"a", "b"}, -15);
    }

    @Test
    public void firstIndexOfMustNegOnPositionTooBig1() {
        assertTrue(Array.firstIndexOf(
                    "a", new String[]{"a", "b"}, 2) == -1);
    }

    @Test
    public void firstIndexOfMustNegOnPositionTooBig2() {
        assertTrue(Array.firstIndexOf(
                "a", new String[]{"a", "b"}, 15) == -1);
    }

    @Test
    public void firstIndexOfMustNegOnPositionTooBig3() {
        assertTrue(Array.firstIndexOf(
                    "a", new String[]{}, 0) == -1);
    }
}
