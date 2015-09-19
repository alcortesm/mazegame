package mazegame.core;

import mazegame.server.Icon;
import mazegame.util.Array;

public class Map {

    private Tile[][] tiles;

    public Map(Tile[][] tiles) {
        if (tiles == null) {
            throw new NullPointerException("tiles");
        }
        if (Array.hasNull(tiles)) {
            throw new NullPointerException("map tiles has nulls");
        }
        if (! Array.isRect(tiles)) {
            throw new IllegalArgumentException(
                    "map is not rectangular");
        }
        this.tiles = tiles;
    }

    int getNumRows() {
        return tiles.length;
    }

    int getNumCols() {
        return tiles[0].length;
    }

    Tile getTile(int r, int c) {
        if (r < 0) {
            throw new IllegalArgumentException("r < 0");
        }
        if (r > getNumRows()) {
            throw new IllegalArgumentException(
                    "r > number of rows in the map");
        }
        if (c < 0) {
            throw new IllegalArgumentException("c < 0");
        }
        if (c > getNumCols()) {
            throw new IllegalArgumentException(
                    "c > number of columns in the map");
        }
        return tiles[r][c];
    }

    Icon[][] getIcons() {
        Icon[][] icons = new Icon[getNumRows()][getNumCols()];
        for (int r=0; r<getNumRows(); r++) {
            for (int c=0; c<getNumCols(); c++) {
                icons[r][c] = tiles[r][c].getIcon();
            }
        }
        return icons;
    }
}
