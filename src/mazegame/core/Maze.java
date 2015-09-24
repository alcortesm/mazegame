// This is the main class of the game.
//
// Its most simple implementation holds a map, an end and a hero and
// take care of moving the hero around and geenrating the ClientView
// (the data structure that clients consume to render the maze to the
// player).

package mazegame.core;

import java.util.Random;

import mazegame.util.Direction;
import mazegame.server.ClientView;
import mazegame.server.Icon;
import mazegame.server.Update;
import mazegame.util.Queue;
import mazegame.util.QueueArray;

public class Maze {

    private Map map;
    private End end;
    private Hero hero;
    private boolean lastMoveOk;
    private Trail trail;
    private Queue<Update> updates;

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
        lastMoveOk = true;
        if (trailCapacity == 0) {
            this.trail = new TrailFake();
        } else {
            this.trail = new TrailArray(trailCapacity);
        }
        updates = new QueueArray<Update>();
    }

    public boolean moveHero(Direction dir) {
        Place old = hero.getPlace();
        lastMoveOk = hero.move(dir);
        if (lastMoveOk) {
            trail.add(old);
            // updates:
            Tile oldHeroTile =
                map.getTile(old.getRow(), old.getCol());
            Update oldHero = new Update(old, oldHeroTile.getIcon());
            Update newHero =
                new Update(hero.getPlace(), hero.getIcon());
            updates.enqueue(oldHero);
            updates.enqueue(newHero);
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
        boolean isGameOver = isGameOver();
        // isHeroAlive
        boolean isHeroAlive = true;
        return new ClientView(icons, isGameOver, isHeroAlive);
    }

    public boolean isGameOver() {
        return hero.getPlace().equals(end.getPlace());
    }

    public Queue<Update> getUpdates() {
        Queue<Update> retval = updates;
        updates = new QueueArray<Update>();
        return retval;
    }
}
