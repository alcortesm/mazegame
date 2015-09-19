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
