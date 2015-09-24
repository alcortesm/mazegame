// A map is composed of spaces and walls.

package mazegame.core;

import mazegame.server.Icon;

public class Wall implements Tile {

    public boolean isWalkable() {
        return false;
    }

    public Icon getIcon() {
        return Icon.WALL;
    }
}
