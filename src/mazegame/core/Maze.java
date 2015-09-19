package mazegame.core;

import java.util.Random;
import java.util.ArrayList;

import mazegame.server.Direction;
import mazegame.server.ClientView;

public class Maze {

    private Map map;

    public Maze(Map map) {
        if (map == null) {
            throw new NullPointerException("map");
        }
        this.map = map;
    }

    public boolean moveHero(Direction dir) {
        return true;
    }

    public ClientView getClientView() {
        return new ClientView(map.getIcons());
    }
}
