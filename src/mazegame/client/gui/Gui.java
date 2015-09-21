package mazegame.client.gui;

import mazegame.client.Client;
import mazegame.server.ServerSpec;
import mazegame.server.Server;

public class Gui implements Client {

    private Server server;

    public Gui(ServerSpec serverSpec) {
        server = new Server(serverSpec);
    }

    public void run() {
        System.out.println("TODO: write the GUI");
    }
}
