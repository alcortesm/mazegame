package mazegame.core;

import java.util.Random;
import java.util.ArrayList;
import mazegame.server.Direction;

public class Maze {

    private Map map;
    private Start start;
    private End end;
    private Player player;

    public Maze(Map map) {
        if (map == null) {
            throw new NullPointerException("map");
        }
        this.map = map;
        ArrayList<Place> places = map.getWalkablePlaces();
        start = new Start(randomWalkablePlace(places));
        end = new End(randomWalkablePlace(places));
        player = new Player(start.getPlace());
    }

    private Place randomWalkablePlace(ArrayList<Place> places) {
        Random random = new Random();
        return places.get(random.nextInt(places.size()));
    }

    public String toString() {
        String s = map.toString();
        s = start.insertMeIntoMapString(s);
        s = end.insertMeIntoMapString(s);
        s = player.insertMeIntoMapString(s);
        return s;
    }

    public boolean movePlayer(Direction dir) {
        return player.move(dir);
    }

    public boolean isPlayerAtEnd() {
        return player.sharePlaceWith(end);
    }

    public Player getPlayer() { return player; }
    public Start getStart() { return start; }
    public End getEnd() { return end; }
    public Map getMap() { return map; }
}
