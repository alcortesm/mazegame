// A wall in a map is a physical barrier that mobs cannot cross.
//
// A simple maze will be composed of wall and the space between them.
//
// TODO: There can be many walls in a maze and they will be all
// equal, consider to reuse the same object for all of them.

package mazegame;

class Wall extends Tile {
    static final String TEXT_ICON = "#";

    public String toString() {
        return TEXT_ICON;
    }
}
