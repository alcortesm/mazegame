package mazegame.core;

public class TestMap implements MapGenerator {

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
