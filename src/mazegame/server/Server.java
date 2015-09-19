package mazegame.server;

import mazegame.core.Maze;
import mazegame.core.Map;
import mazegame.server.ServerSpec;

public class Server {

    private Maze maze;

    public Server(ServerSpec spec) {
        Map map = spec.generateMap();
        this.maze = new Maze(map);
    }

    public ClientView moveHero(Direction dir) {
        return getClientView();
    }

    public ClientView getClientView() {
        return maze.getClientView();
    }
}
