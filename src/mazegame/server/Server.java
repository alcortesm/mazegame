package mazegame.server;

import mazegame.core.Maze;

public class Server {

    public Server() {}

    public ClientView moveHero(Direction dir) {
        return new ClientView();
    }

    public ClientView getClientView() {
        return new ClientView();
    }
}
