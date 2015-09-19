package mazegame.server;

import mazegame.core.Maze;
import mazegame.core.Map;
import mazegame.server.ServerSpec;
import mazegame.core.End;

public class Server {

    private Maze maze;

    public Server(ServerSpec spec) {
        Map map = spec.generateMap();
        End end = spec.generateEnd();
        this.maze = new Maze(map, end);
    }

    public ClientView moveHero(Direction dir) {
        return getClientView();
    }

    public ClientView getClientView() {
        return maze.getClientView();
    }
}
