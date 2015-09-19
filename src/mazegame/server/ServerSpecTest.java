package mazegame.server;

import mazegame.core.Map;
import mazegame.core.Tile;
import mazegame.core.Space;
import mazegame.core.Wall;

public class ServerSpecTest implements ServerSpec {

    public Map generateMap() {
        Tile w = new Wall();
        Tile s = new Space();
        Tile[][] tiles = {
            {s, w, s, w, s, s, s, w},
            {s, w, s, s, s, w, s, s},
            {s, w, s, w, w, w, s, w},
            {s, s, s, w, s, s, s, s},
        };
        return new Map(tiles);
    }
}
