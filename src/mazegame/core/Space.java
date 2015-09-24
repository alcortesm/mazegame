// A map is composed of Walls and Speces, both are subclasses of
// Tile.

package mazegame.core;

import mazegame.server.Icon;

public class Space implements Tile {

    public boolean isWalkable() {
        return true;
    }

    public Icon getIcon() {
        return Icon.EMPTY;
    }
}
