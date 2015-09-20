package mazegame.server;

import mazegame.core.Maze;
import mazegame.core.Map;
import mazegame.server.ServerSpec;
import mazegame.core.End;
import mazegame.core.Hero;

public class Server {

    private Maze maze;

    public Server(ServerSpec spec) {
        Map map = spec.generateMap();
        End end = spec.generateEnd();
        Hero hero = spec.generateHero();
        int trailCapacity = spec.generateTrailCapacity();
        this.maze = new Maze(map, end, hero, trailCapacity);
    }

    public ClientView moveHero(Direction dir) {
        maze.moveHero(dir);
        return getClientView();
    }

    public ClientView getClientView() {
        return maze.getClientView();
    }
}
