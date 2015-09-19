package mazegame.server;

import mazegame.core.Map;
import mazegame.core.Tile;
import mazegame.core.Space;
import mazegame.core.Wall;
import mazegame.core.End;
import mazegame.core.Place;

public class ServerSpecTest implements ServerSpec {

    private Map map;
    private End end;

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
    }

    public Map generateMap() {
        return map;
    }

    public End generateEnd() {
        return end;
    }
}
