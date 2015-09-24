// The serverspec used by the client to create a server that uses the
// test map.

package mazegame.server;

import mazegame.core.Map;
import mazegame.core.TestMap;
import mazegame.core.End;
import mazegame.core.Place;
import mazegame.core.Hero;

public class ServerSpecTest implements ServerSpec {

    private Map map;
    private End end;
    private Hero hero;
    private int trailCapacity;

    public ServerSpecTest(int trailCapacity) {
        if (trailCapacity < 0) {
            throw new IllegalArgumentException("trailCapacity < 0");
        }
        this.map = new TestMap().generateMap();
        this.end = new End(new Place(3, 4, map));
        this.hero = new Hero(new Place(0, 0, map));
        this.trailCapacity = trailCapacity;
    }

    public Map generateMap() { return map; }
    public End generateEnd() { return end; }
    public Hero generateHero() { return hero; }
    public int generateTrailCapacity() { return trailCapacity; }

    public String toString() {
        return "TEST";
    }
}
