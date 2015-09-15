package mazegame;

import java.util.Scanner;

class Main {

    public static void main(String args[]) {
        Map map = MapGenerator.Test();
        Maze maze = new Maze(map);

        System.out.println("Welcome to the MazeGame.");
        System.out.println("Press the ENTER key to begin...");
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();

        boolean finish = false;
        do {
            System.out.println();
            System.out.println();
            System.out.println(maze);
            System.out.println();
            System.out.print("Enter a command and press ENTER: ");
            line = scan.nextLine();
            switch (line) {
                case "north": case "n":
                    if (maze.movePlayer(Mob.DIRECTION.NORTH)) {
                        System.out.println();
                        System.out.println("Moving NORTH...");
                    } else {
                        System.out.println();
                        System.out.println("Cannot move there!");
                    }
                    break;
                case "south": case "s":
                    if (maze.movePlayer(Mob.DIRECTION.SOUTH)) {
                        System.out.println();
                        System.out.println("Moving SOUTH...");
                    } else {
                        System.out.println();
                        System.out.println("Cannot move there!");
                    }
                    break;
                case "east": case "e":
                    if (maze.movePlayer(Mob.DIRECTION.EAST)) {
                        System.out.println();
                        System.out.println("Moving EAST...");
                    } else {
                        System.out.println();
                        System.out.println("Cannot move there!");
                    }
                    break;
                case "west": case "w":
                    if (maze.movePlayer(Mob.DIRECTION.WEST)) {
                        System.out.println();
                        System.out.println("Moving WEST...");
                    } else {
                        System.out.println();
                        System.out.println("Cannot move there!");
                    }
                    break;
                case "quit": case "q": case "exit": case "x":
                    System.out.println();
                    System.out.println("Exiting...");
                    finish = true;
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
            if (maze.isPlayerAtEnd()) {
                System.out.println();
                System.out.println(
                        "CONGRATULATIONS!, YOU BEAT THE GAME!");
                finish = true;
            }
        } while (! finish);

        System.exit(0);
    }
}
