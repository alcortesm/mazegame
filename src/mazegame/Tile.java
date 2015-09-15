// A map is composed of several tiles arranged in 2D array.
//
// Example classes that implements Tile: Walls and Spaces.

package mazegame;

interface Tile extends HasIcon {
    boolean isWalkable();
}
