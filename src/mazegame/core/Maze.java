package mazegame.core;

import java.util.Random;
import java.util.ArrayList;

import mazegame.server.Direction;
import mazegame.server.ClientView;
import mazegame.server.Icon;

public class Maze {

    private Map map;
    private End end;

    public Maze(Map map, End end) {
        if (map == null) {
            throw new NullPointerException("map");
        }
        if (end == null) {
            throw new NullPointerException("end");
        }
        this.map = map;
        this.end = end;
    }

    public boolean moveHero(Direction dir) {
        return true;
    }

    public ClientView getClientView() {
        Icon[][] icons = map.getIcons();
        Place endPlace = end.getPlace();
        System.out.println(endPlace);
        icons[endPlace.getRow()][endPlace.getCol()] = end.getIcon();
        return new ClientView(icons);
    }
}
