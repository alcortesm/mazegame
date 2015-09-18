package mazegame;

import mazegame.client.Client;
import mazegame.client.Tui;
import mazegame.client.Language;

class Main {

    public static void main(String args[]) {
        if (args.length != 0) {
            usage();
            System.exit(1);
        }
        Client client = new Tui();
        client.run();
        System.exit(0);
    }

    private static void usage() {
        System.err.println("usage:");
        System.err.println(
                "\tThis program does not admit any command line argument");
    }
}
