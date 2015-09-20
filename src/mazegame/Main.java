package mazegame;

import mazegame.client.Client;
import mazegame.client.Tui;
import mazegame.client.Language;

class Main {

    public static void main(String args[]) {
        CLOptions opts = null;
        try {
            opts = new CLOptions(args);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            usage();
            System.exit(1);
        }
        Client client = new Tui(opts.getLanguage(),
                opts.getServerSpec());
        client.run();
        System.exit(0);
    }

    private static void usage() {
        System.err.println("options:");
        System.err.println(
                "\t-l (ENGLISH|SPANISH)" + System.lineSeparator() +
                "\t\tsets the language for the user interface");
        System.err.println(
                "\t-s (TEST|EMPTY)" + System.lineSeparator() +
                "\t\t Map generation style");
        System.err.println(
                "\t-r number" + System.lineSeparator() +
                "\t\t number of rows in the map (ignored if using the TEST map)");
        System.err.println(
                "\t-c number" + System.lineSeparator() +
                "\t\t number of columns in the map (ignored if using the TEST map)");
    }
}
