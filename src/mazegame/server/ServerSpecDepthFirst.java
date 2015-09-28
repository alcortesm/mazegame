// The serverspec used by the client to create servers that generate
// random maps.

package mazegame.server;

import java.util.NoSuchElementException;

import mazegame.core.Map;
import mazegame.core.DepthFirstAlgo;
import mazegame.core.End;
import mazegame.core.Place;
import mazegame.core.Hero;

public class ServerSpecDepthFirst implements ServerSpec {

    private Map map;
    private End end;
    private Hero hero;
    private int trailCapacity;

    public ServerSpecDepthFirst(int rows, int cols, int trailCapacity) {
        if (rows < 1) {
            throw new IllegalArgumentException("rows < 1");
        }
        if (cols < 1) {
            throw new IllegalArgumentException("columns < 1");
        }
        if (trailCapacity < 0) {
            throw new IllegalArgumentException("trailCapacity < 0");
        }
        if (rows % 2 == 0) {
            throw new IllegalArgumentException(
                    "Random maps require an odd number of rows");
        }
        if (cols % 2 == 0) {
            throw new IllegalArgumentException(
                    "Random maps require an odd number of columns");
        }
        map = new DepthFirstAlgo(rows, cols).generateMap();
        hero = new Hero(new Place(0, 0, map));
        end = new End(new Place(rows-1, cols-1, map));
        this.trailCapacity = trailCapacity;
    }

    public Map generateMap() { return map; }
    public End generateEnd() { return end; }
    public Hero generateHero() { return hero; }
    public int generateTrailCapacity() { return trailCapacity; }

    public String toString() {
        return "DEPTHFIRST";
    }
}
