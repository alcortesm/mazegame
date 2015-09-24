// A Map is a collection of tiles.
//
// The easiest way to store the tiles is an 2D array.
//
// A Map is a way to access the 2D internal array in an easiest way.

package mazegame.core;

import mazegame.server.Icon;
import mazegame.util.Array;
import mazegame.util.OpaqueSack;
import mazegame.util.OpaqueSackArray;

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

    public OpaqueSack<Place> getWalkables() {
        int rows = tiles.length;
        int cols = tiles[0].length;
        OpaqueSack<Place> list = new OpaqueSackArray<Place>();
        for (int r=0; r<rows; r++) {
            for (int c=0; c<cols; c++) {
                if (tiles[r][c].isWalkable()) {
                    list.add(new Place(r, c, this));
                }
            }
        }
        return list;
    }
}
