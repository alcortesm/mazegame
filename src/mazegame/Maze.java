package mazegame;

import java.util.Random;
import java.util.ArrayList;

class Maze {
    private Map map;
    private Place start;
    private Place end;

    private static final char START_ICON = 's';
    private static final char END_ICON = 'e';

    Maze(Map map) {
        if (map == null) {
            throw new NullPointerException("map");
        }
        this.map = map;
        ArrayList<Place> places = map.getWalkablePlaces();
        start = randomWalkablePlace(places);
        end = randomWalkablePlace(places);
    }

    private Place randomWalkablePlace(ArrayList<Place> places) {
        Random random = new Random();
        return places.get(random.nextInt(places.size()));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(map.toString());
        int width = map.getNumColumns() + 2 +
            System.lineSeparator().length();
        int startIndex = ((start.getRow()+1) * width) +
            start.getColumn() + 1;
        int endIndex = ((end.getRow()+1) * width) +
            end.getColumn() + 1;
        sb.setCharAt(startIndex, START_ICON);
        sb.setCharAt(endIndex, END_ICON);
        return sb.toString();
    }
}
