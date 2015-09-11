package mazegame;

class MapGenerator {

    static enum CTOR_OPTION {
        SMALLEST
    }

    private static Map CreateSmallestEmpty() {
        a = new Tile[Map.getMinRows()][Map.getMinColumns()];
        for (int r=0; r<a.length; r++) {
            for (int c=0; c<a[r].length; c++) {
                // put walls in the external borders
                if (r == 0 || r == a.length ||
                        c == 0 || c == a[r].length) {
                    a[r][c] = new Wall();
                } else { // and empty space in the center
                    a[r][c] = new Empty();
                }
            }
        }
        return new Map(a);
    }


}
