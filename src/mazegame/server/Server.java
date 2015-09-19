package mazegame.server;

import mazegame.core.Maze;

public class Server {

    private Maze maze;

    public Server() {
        this.maze = new Maze();
    }

    public ClientView moveHero(Direction dir) {
        return new ClientView();
    }

    public ClientView getClientView() {
        return new ClientView();
    }
}
