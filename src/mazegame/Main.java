package mazegame;

import mazegame.client.*;

class Main {

    public static void main(String args[]) {
        Client client = new Tui();
        client.run();
        System.exit(0);
    }
}
