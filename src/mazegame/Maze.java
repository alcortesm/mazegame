package mazegame;

import java.util.Random;
import java.util.ArrayList;

class Maze {
    private Map map;
    private Place start;
    private Place end;
    private Mob player;

    private static final char START_ICON = 's';
    private static final char END_ICON = 'e';
    private static final char PLAYER_ICON = '@';

    Maze(Map map) {
        if (map == null) {
            throw new NullPointerException("map");
        }
        this.map = map;
        ArrayList<Place> places = map.getWalkablePlaces();
        start = randomWalkablePlace(places);
        end = randomWalkablePlace(places);
        player = new Mob(start);
    }

    private Place randomWalkablePlace(ArrayList<Place> places) {
        Random random = new Random();
        return places.get(random.nextInt(places.size()));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(map.toString());
        sb.setCharAt(start.getMapStringIndex(), START_ICON);
        sb.setCharAt(end.getMapStringIndex(), END_ICON);
        sb.setCharAt(player.getMapStringIndex(), PLAYER_ICON);
        return sb.toString();
    }

    boolean movePlayer(Mob.DIRECTION dir) {
        return player.move(dir);
    }
}
