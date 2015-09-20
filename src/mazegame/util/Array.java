package mazegame.util;

public class Array {

    // Returns if its parameter is an array storing any null.
    public static <T> boolean hasNull(T[][] a) {
        if (a == null) {
            throw new NullPointerException("a");
        }
        for (int r=0; r<a.length; r++) {
            for (int c=0; c<a[r].length; c++) {
                if (a[r][c] == null) {
                    return true;
                }
            }
        }
        return false;
    }

    // Returns if its parameter is a rectangular array.
    //
    // Arrays with some 0 dimension are considered not rectangular
    // for the purpose of this program.
    public static <T> boolean isRect(T[][] a) {
        if (a == null) {
            throw new NullPointerException("a");
        }
        int numRows = a.length;
        if (numRows == 0) {
            return false;
        }
        int numColsOfFirstRow = a[0].length;
        if (numColsOfFirstRow == 0) {
            return false;
        }
        for (int r=1; r<numRows; r++) {
            if (a[r].length != numColsOfFirstRow) {
                return false;
            }
        }
        return true;
    }

    // returns the index of the first occurence of target t in
    // the array a, starting from position p towards the end.
    // returns -1 if t is not found.
    public static <T> int firstIndexOf(T t, T[] a, int p) {
        if (a == null) {
            throw new NullPointerException("a");
        }
        if (p < 0) {
            throw new IllegalArgumentException("p < 0");
        }
        if (t == null) {
            throw new NullPointerException("s");
        }
        for (int i=p; i<a.length; i++) {
            if (a[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    // Remove from a[n] to a[n+c] (both included).
    public static String[] remove(String[] a, int n, int c) {
        if (a == null) {
            throw new NullPointerException("a");
        }
        if (c < 0) {
            throw new IllegalArgumentException("c < 0");
        }
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }
        if (n >= a.length) {
            throw new IllegalArgumentException("n >= a.length");
        }
        if (n+c > a.length) {
            throw new IllegalArgumentException("n+c >= a.length");
        }
        if (c == 0) {
            return a;
        }
        int newLen = a.length - c;
        String[] r = new String[newLen];
        for (int i=0; i<n; i++) {
            r[i] = a[i];
        }
        for (int i=n+c; i<a.length; i++) {
            r[i-2] = a[i];
        }
        return r;
    }
}
