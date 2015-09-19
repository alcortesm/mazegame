package mazegame.core;

import mazegame.server.Icon;
import mazegame.util.Array;

public class Map {

    private Icon[][] icons;

    public Map(Icon[][] icons) {
        if (icons == null) {
            throw new NullPointerException("icons");
        }
        if (Array.hasNull(icons)) {
            throw new NullPointerException("map icons has nulls");
        }
        if (! Array.isRect(icons)) {
            throw new IllegalArgumentException(
                    "map is not rectangular");
        }
        this.icons = icons;
    }

    int getNumRows() {
        return icons.length;
    }

    int getNumCols() {
        return icons[0].length;
    }

    Icon getIcon(int r, int c) {
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
        return icons[r][c];
    }

    Icon[][] getIcons() {
        return icons;
    }
}
