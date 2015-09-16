// Space is what you will find in a map between walls.
//
// Mobs will be able to walk over space, but not across walls.
//
// A space is not necesarily empty, it can have objects or mobs in
// it (see the Place class).

// TODO: as there are going to be many spaces in the maze, and they
// will probably be all equal, maybe it will be a good idea to use
// the same object for all of them.

package mazegame.core;

import mazegame.server.Icon;

class Space implements Tile {

    private static final Icon ICON = new Icon(' ');

    public boolean isWalkable() {
        return true;
    }

    public char getIconChar() {
        return ICON.getChar();
    }

    public Icon getIcon() {
        return ICON;
    }
}
