package mazegame.client;

import java.util.Scanner;

import mazegame.server.Server;
import mazegame.server.ClientView;
import mazegame.server.Icon;
import mazegame.server.Direction;

public class Tui implements Client {

    private Localizer localizer;
    private Server server;

    // we are breaking dependency injection here by
    // not passing a Localizer, but I think it is worth
    // it to keep the client package API short and clean.
    public Tui(Language lang) {
        if (lang == null) {
            throw new NullPointerException("lang");
        }
        localizer = new Localizer(lang);
        server = new Server();
    }

    public void run() {
        Scanner scan = new Scanner(System.in);

        System.out.print(localizer.get(MsgToUsr.GREETING));
        scan.nextLine();

        String line;
        boolean mustExit = false;
        ClientView view = server.getClientView();
        printClientView(view);
        do {
            if (view.isGameOver()) {
                System.out.print(localizer.get(MsgToUsr.CONGRATULATIONS));
                mustExit = true;
                continue;
            }
            System.out.print(localizer.get(MsgToUsr.ASK_FOR_COMMAND));
            line = scan.nextLine();
            if (line.length() == 0) {
                continue;
            }
            switch (line) {
                case "north": case "n":
                    view = server.movePlayer(Direction.NORTH);
                    printClientView(view);
                    break;
                case "south": case "s":
                    view = server.movePlayer(Direction.SOUTH);
                    printClientView(view);
                    break;
                case "east": case "e":
                    view = server.movePlayer(Direction.EAST);
                    printClientView(view);
                    break;
                case "west": case "w":
                    view = server.movePlayer(Direction.WEST);
                    printClientView(view);
                    break;
                case "quit": case "q": case "exit": case "x":
                    System.out.print(localizer.get(MsgToUsr.EXITING));
                    mustExit = true;
                    break;
                case "help": case "h":
                    System.out.print(localizer.get(MsgToUsr.HELP));
                    break;
                default:
                    System.out.print(localizer.get(MsgToUsr.UNKNOWN_COMMAND) +
                            line +
                            localizer.get(MsgToUsr.UNKNOWN_COMMAND_SUFIX));
            }
        } while (! mustExit);
    }

    private static char iconToChar(Icon icon) {
        switch (icon) {
            case EMPTY:
                return ' ';
            case WALL:
                return '#';
            case END:
                return 'e';
            case HERO:
                return '@';
            default:
                throw new IllegalArgumentException(icon.toString());
        }
    }

    private static void printClientView(ClientView view) {
        // print last command result
        System.out.println();
        System.out.println(view.lastMsgResult());

        // print maze
        System.out.println();
        // we will need this for printing exterior walls
        char wall = iconToChar(Icon.WALL);
        // print north wall
        Icon[][] topView = view.getTopView();
        int numRows = topView.length;
        int numCols = topView[0].length;
        for (int c = 0; c<numCols+2; c++) {
            System.out.print(wall);
        }
        System.out.println();
        // print tiles and objects in the maze
        for (int r = 0; r<numRows; r++) {
            System.out.print(wall); // west wall
            for (int c = 0; c<numCols; c++) {
                System.out.print(iconToChar(topView[r][c]));
            }
            System.out.print(wall); // east wall
            System.out.println();
        }
        // print south wall
        for (int c = 0; c<numCols+2; c++) {
            System.out.print(wall);
        }
        System.out.println();
    }
}
