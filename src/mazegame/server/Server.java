package mazegame.server;

import mazegame.core.Maze;
import mazegame.core.Map;
import mazegame.server.ServerSpec;
import mazegame.core.End;
import mazegame.core.Hero;
import mazegame.util.Direction;

public class Server {

    private Maze maze;

    public Server(ServerSpec spec) {
        Map map = spec.generateMap();
        End end = spec.generateEnd();
        Hero hero = spec.generateHero();
        int trailCapacity = spec.generateTrailCapacity();
        this.maze = new Maze(map, end, hero, trailCapacity);
    }

    public boolean moveHero(Direction dir) {
        return maze.moveHero(dir);
    }

    public ClientView getClientView() {
        return maze.getClientView();
    }
}
