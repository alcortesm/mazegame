package mazegame.server;

import mazegame.core.Map;
import mazegame.core.Tile;
import mazegame.core.Space;
import mazegame.core.Wall;
import mazegame.core.End;
import mazegame.core.Place;
import mazegame.core.Hero;

public class ServerSpecTest implements ServerSpec {

    private Map map;
    private End end;
    private Hero hero;

    public ServerSpecTest() {
        Tile w = new Wall();
        Tile s = new Space();
        Tile[][] tiles = {
            {s, w, s, w, s, s, s, w},
            {s, w, s, s, s, w, s, s},
            {s, w, s, w, w, w, s, w},
            {s, s, s, w, s, s, s, s},
        };
        this.map = new Map(tiles);
        this.end = new End(new Place(3, 4, map));
        this.hero = new Hero(new Place(0, 0, map));
    }

    public Map generateMap() { return map; }

    public End generateEnd() { return end; }

    public Hero generateHero() { return hero; }
}
