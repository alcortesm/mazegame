// Space is what you will find in a map between walls.
//
// Mobs will be able to walk over space, but not across walls.
//
// A space is not necesarily empty, it can have objects or mobs in
// it.

// TODO: as there are going to be many spaces in the maze, and they
// will probably be all equal, maybe it will be a good idea to use
// the same object for all of them.

package mazegame;

class Space extends Tile {
    static final String TEXT_ICON = " ";

    public String toString() {
        return TEXT_ICON;
    }
}

