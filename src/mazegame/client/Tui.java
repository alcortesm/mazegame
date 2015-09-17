package mazegame.client;

import java.util.Scanner;

import mazegame.server.Server;
import mazegame.server.ClientView;
import mazegame.server.Icon;
import mazegame.server.Direction;

public class Tui implements Client {

    private Server server;

    private static final String GREETING =
        System.lineSeparator() +
        "                  ###########################               " + System.lineSeparator() +
        "                  # Welcome to the MazeGame #               " + System.lineSeparator() +
        "                  ###########################               " + System.lineSeparator() +
        "                                                            " + System.lineSeparator() +
        "            A tiny roguelike game for teaching Java.        " + System.lineSeparator() +
        "                                                            " + System.lineSeparator() +
        "       (C) 2015 by Alberto Cortés (alcortesm@gmail.com)     " + System.lineSeparator() +
        "                                                            " + System.lineSeparator() +
        "                                                            " + System.lineSeparator() +
        "Press the ENTER key to begin...";

    private static final String CONGRATULATIONS =
        System.lineSeparator() +
        "                        CONGRATULATIONS!                    " + System.lineSeparator() +
        "                                                            " + System.lineSeparator() +
        "                           YOU WIN!                         " + System.lineSeparator() +
        System.lineSeparator();

    private static final String ASK_FOR_COMMAND =
        System.lineSeparator() +
        "Write a command and press ENTER (h for help): ";

    private static final String HELP =
        System.lineSeparator() +
        "Goal:" + System.lineSeparator() +
        "\tYour goal is to take the player from" + System.lineSeparator() +
        "\tthe starting point to the exit of the maze." + System.lineSeparator() +
        System.lineSeparator() +
        "Legend:" + System.lineSeparator() +
        "\t#   a wall" + System.lineSeparator() +
        "\t@   the player" + System.lineSeparator() +
        "\ts   starting position" + System.lineSeparator() +
        "\te   the exit of the maze" + System.lineSeparator() +
        System.lineSeparator() +
        "Commands:" + System.lineSeparator() +
        "\th, help:   this help" + System.lineSeparator() +
        "\tn, north:  go north" + System.lineSeparator() +
        "\ts, south:  go south" + System.lineSeparator() +
        "\te, east:   go east" + System.lineSeparator() +
        "\tw, west:   go west" + System.lineSeparator() +
        "\tq, quit:   quit the game" + System.lineSeparator() +
        "\tx, exit:   alias for \"quit\"" + System.lineSeparator() +
        System.lineSeparator();

    private static final String EXITING = System.lineSeparator() + "Exiting..." + System.lineSeparator()
        + System.lineSeparator();

    private static final String UNKNOWN_COMMAND = System.lineSeparator() + "Unknown command: \"";
    private static final String UNKNOWN_COMMAND_SUFIX = "\"" + System.lineSeparator();

    public Tui() {
        server = new Server();
    }

    public void run() {
        Scanner scan = new Scanner(System.in);

        System.out.print(GREETING);
        scan.nextLine();

        String line;
        boolean mustExit = false;
        ClientView view = server.getClientView();
        printClientView(view);
        do {
            if (view.isGameOver()) {
                System.out.print(CONGRATULATIONS);
                mustExit = true;
                continue;
            }
            System.out.print(ASK_FOR_COMMAND);
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
                    System.out.print(EXITING);
                    mustExit = true;
                    break;
                case "help": case "h":
                    System.out.print(HELP);
                    break;
                default:
                    System.out.print(UNKNOWN_COMMAND + line + UNKNOWN_COMMAND_SUFIX);
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
