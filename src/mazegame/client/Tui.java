package mazegame.client;

import java.util.Scanner;

import mazegame.server.*;


public class Tui implements Client {

    private Server server;

    public Tui() {
        server = new Server();
    }

    public void run() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to the MazeGame.");
        System.out.println("Press the ENTER key to begin...");
        String line = scan.nextLine();

        boolean exit = false;
        ClientView view = server.getClientView();
        do {
            System.out.println();
            System.out.println();
            System.out.print("Enter a command and press ENTER: ");
            line = scan.nextLine();
            switch (line) {
                case "north": case "n":
                    view = server.movePlayer(Direction.NORTH);
                    System.out.println();
                    System.out.println(view.lastCommandResultDescription());
                    break;
                case "quit": case "q": case "exit": case "x":
                    System.out.println();
                    System.out.println("Exiting...");
                    exit = true;
                    break;
                case "help": case "h":
                    System.out.println();
                    System.out.println("Goal:");
                    System.out.println("\tYour goal is to take the player from");
                    System.out.println("\tthe starting point to the exit of the maze.");
                    System.out.println();
                    System.out.println("Legend:");
                    System.out.println("\t#   a wall");
                    System.out.println("\t@   the player");
                    System.out.println("\ts   starting position");
                    System.out.println("\te   the exit of the maze");

                    System.out.println();
                    System.out.println("Commands:");
                    System.out.println("\th, help:   this help");
                    System.out.println("\tn, north:  go north");
                    System.out.println("\ts, south:  go south");
                    System.out.println("\te, east:   go east");
                    System.out.println("\tw, west:   go west");
                    System.out.println("\tq, quit:   quit the game");
                    System.out.println("\tx, exit:   alias for \"quit\"");
                    break;
                default:
                    System.out.println();
                    System.out.println("Unknown command");
            }
            if (view.isFinished()) {
                System.out.println();
                System.out.println(
                        "CONGRATULATIONS!, YOU BEAT THE GAME!");
                exit = true;
            }
        } while (! exit);

    }
}
