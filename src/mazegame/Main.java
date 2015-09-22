package mazegame;

import mazegame.client.Client;
import mazegame.client.gui.Gui;
import mazegame.client.tui.Tui;
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
        Client client;
        ClientChoice clientChoice = opts.getClientChoice();
        switch (clientChoice) {
            case TUI:
                client = new Tui(
                        opts.getLanguage(), opts.getServerSpec());
                break;
            case GUI:
                client = new Gui(opts.getServerSpec());
                break;
            default:
                throw new UnsupportedOperationException(
                        "Unsupported client choice: " +
                        clientChoice.toString());
        }
        client.run();
    }

    private static void usage() {
        System.err.println("options:");
        System.err.println(
                "\t-l (ENGLISH|SPANISH)" + System.lineSeparator() +
                "\t\tsets the language for the user interface");
        System.err.println();
        System.err.println(
                "\t-s (TEST|EMPTY)" + System.lineSeparator() +
                "\t\t Map generation style");
        System.err.println();
        System.err.println(
                "\t-r number" + System.lineSeparator() +
                "\t\t number of rows in the map (ignored if using the TEST map)");
        System.err.println();
        System.err.println(
                "\t-c number" + System.lineSeparator() +
                "\t\t number of columns in the map (ignored if using the TEST map)");
        System.err.println();
        System.err.println(
                "\t-ui (TUI|GUI)" + System.lineSeparator() +
                "\t\t choose between a textual client or a graphical one.");
        System.err.println();
        System.err.println(
                "\tdefaults: -l " + CLOptions.DEFAULT_LANGUAGE +
                " -s " + CLOptions.DEFAULT_SERVER_SPEC +
                " -r " + CLOptions.DEFAULT_ROWS +
                " -c " + CLOptions.DEFAULT_COLS +
                " -t " + CLOptions.DEFAULT_TRAIL_CAPACITY +
                " -ui " + CLOptions.DEFAULT_CLIENT_CHOICE);
    }
}
