// This is the main class of the game.
//
// Its most simple implementation holds a map, an end and a hero and
// take care of moving the hero around and geenrating the ClientView
// (the data structure that clients consume to render the maze to the
// player).

package mazegame.core;

import java.util.Random;
import java.util.ArrayList;

import mazegame.util.Direction;
import mazegame.server.ClientView;
import mazegame.server.Icon;

public class Maze {

    private Map map;
    private End end;
    private Hero hero;
    private Trail trail;

    public Maze(Map map, End end, Hero hero, int trailCapacity) {
        if (map == null) {
            throw new NullPointerException("map");
        }
        if (end == null) {
            throw new NullPointerException("end");
        }
        if (hero == null) {
            throw new NullPointerException("hero");
        }
        if (trailCapacity < 0) {
            throw new IllegalArgumentException("trailCapacity < 0");
        }
        this.map = map;
        this.end = end;
        this.hero = hero;
        if (trailCapacity == 0) {
            this.trail = new TrailFake();
        } else {
            this.trail = new TrailArray(trailCapacity);
        }
    }

    public boolean moveHero(Direction dir) {
        Place old = hero.getPlace();
        boolean lastMoveOk = hero.move(dir);
        if (lastMoveOk) {
            trail.add(old);
        }
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
        // add end
        icons = addEntityToIcons(end, icons);
        // add trail
        Footprint[] tracks = trail.getAll();
        for (int i=0; i<tracks.length; i++) {
            icons = addEntityToIcons(tracks[i], icons);
        }
        // add hero
        icons = addEntityToIcons(hero, icons);
        // isGameOver
        boolean isGameOver = hero.getPlace().equals(end.getPlace());
        // isHeroAlive
        boolean isHeroAlive = true;
        return new ClientView(icons, isGameOver, isHeroAlive);
    }
}
