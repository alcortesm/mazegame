// Maps are a 2D array of Tiles: Walls and Spaces.

package mazegame.core;

public interface Tile extends HasIcon {
    boolean isWalkable();
}
