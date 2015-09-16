// A wall in a map is a physical barrier that mobs cannot cross.
//
// A simple map will be composed of walls and the space between them.
//
// TODO: There can be many walls in a maze and they will be all
// equal, consider to reuse the same object for all of them.

package mazegame.core;

import mazegame.server.Icon;

public class Wall implements Tile {

    private static final Icon ICON = new Icon('#');

    public boolean isWalkable() {
        return false;
    }

    public char getIconChar() {
        return ICON.getChar();
    }

    public Icon getIcon() {
        return ICON;
    }
}
