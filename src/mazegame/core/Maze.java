package mazegame.core;

import java.util.Random;
import java.util.ArrayList;

import mazegame.server.Direction;
import mazegame.server.ClientView;
import mazegame.server.Icon;

public class Maze {

    private Map map;
    private End end;
    private Hero hero;
    private boolean lastMoveOk;

    public Maze(Map map, End end, Hero hero) {
        if (map == null) {
            throw new NullPointerException("map");
        }
        if (end == null) {
            throw new NullPointerException("end");
        }
        if (hero == null) {
            throw new NullPointerException("hero");
        }
        this.map = map;
        this.end = end;
        this.hero = hero;
        lastMoveOk = true;
    }

    public boolean moveHero(Direction dir) {
        lastMoveOk = hero.move(dir);
        return lastMoveOk;
    }

    private Icon[][] addEntityToIcons(Entity e, Icon[][] icons) {
        Place place = e.getPlace();
        icons[place.getRow()][place.getCol()] = e.getIcon();
        return icons;
    }

    public ClientView getClientView() {
        // get floor map
        Icon[][] icons = map.getIcons();
        // add end and hero
        icons = addEntityToIcons(end, icons);
        icons = addEntityToIcons(hero, icons);
        // isGameOver
        boolean isGameOver = hero.getPlace().equals(end.getPlace());
        // isHeroAlive
        boolean isHeroAlive = true;
        // lastMsgResult
        String lastMsgResult = lastMoveOk ? "OK" : "Cannot go there!";
        return new ClientView(icons, isGameOver,
                isHeroAlive, lastMsgResult);
    }
}
