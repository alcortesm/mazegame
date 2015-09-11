// Space is what you will find in a map between walls.
//
// Mobs will be able to walk over space, but not across walls.
//
// A space is not necesarily empty, it can have objects or mobs in
// it.

package mazegame;

// TODO: as there are going to be many spaces in the maze, and they
// will probably be all equal, maybe it will be a good idea to use
// the same object for all of them.
class Space extends Tile {

    public String toString() {
        return " ";
    }
}

