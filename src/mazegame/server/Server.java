package mazegame.server;

import mazegame.core.Map;
import mazegame.core.MapGenerator;
import mazegame.core.Maze;

public class Server {

    private Maze maze;

    public ClientView movePlayer(Direction dir) {
        if (maze == null) {
            throw new IllegalStateException(
                    "The maze has not yet been created");
        }
        maze.movePlayer(dir);
        return new ClientView(maze);
    }

    public ClientView getClientView() {
        return new ClientView(maze);
    }

    public Server() {
        Map map = MapGenerator.Test();
        maze = new Maze(map);
    }
}
