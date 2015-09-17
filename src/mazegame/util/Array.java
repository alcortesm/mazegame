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

}
