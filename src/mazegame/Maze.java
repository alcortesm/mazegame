package mazegame;

import java.util.Random;
import java.util.ArrayList;

class Maze {

    private Map map;
    private Start start;
    private End end;
    private Player player;

    Maze(Map map) {
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

    boolean movePlayer(Mob.DIRECTION dir) {
        return player.move(dir);
    }

    boolean isPlayerAtEnd() {
        return player.sharePlaceWith(end);
    }
}
