// The serverspec used by the client to create servers that generate
// random maps.

package mazegame.server;

import java.util.NoSuchElementException;

import mazegame.core.Map;
import mazegame.core.PrimsAlgo;
import mazegame.core.Tile;
import mazegame.core.Space;
import mazegame.core.Wall;
import mazegame.core.End;
import mazegame.core.Place;
import mazegame.core.Hero;
import mazegame.util.RandomList;

public class ServerSpecRandom implements ServerSpec {

    private Map map;
    private End end;
    private Hero hero;
    private int trailCapacity;

    public ServerSpecRandom(int rows, int cols, int trailCapacity) {
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
        map = new PrimsAlgo(rows, cols).generateMap();
        RandomList<Place> walkables = map.getWalkables();
        end = new End(walkables.remove());
        try {
            hero = new Hero(walkables.remove());
        } catch (NoSuchElementException e) {
            // this happens when the maze is 1x1
            hero = new Hero(end.getPlace());
        }
        this.trailCapacity = trailCapacity;
    }

    public Map generateMap() { return map; }
    public End generateEnd() { return end; }
    public Hero generateHero() { return hero; }
    public int generateTrailCapacity() { return trailCapacity; }

    public String toString() {
        return "RANDOM";
    }

    private Tile[][] createTiles(int rows, int cols) {
        Tile wall = new Wall();
        Tile space = new Space();
        Tile[][] tiles = new Tile[rows][cols];
        for (int r=0; r<rows; r++) {
            for (int c=0; c<cols; c++) {
                tiles[r][c] = wall;
            }
        }
        tiles[0][0] = space;
        tiles[rows-1][cols-1] = space;
        return tiles;
    }
}
