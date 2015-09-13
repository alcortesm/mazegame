package mazegame;

import java.util.Scanner;

class Main {

    public static void main(String args[]) {
        Map map = MapGenerator.generate(
                MapGenerator.TYPE.TEST);
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
                        System.out.println("Moving NORTH...");
                    } else {
                        System.out.println();
                        System.out.println("Cannot move there!");
                    }
                    break;
                case "east": case "e":
                    if (maze.movePlayer(Mob.DIRECTION.EAST)) {
                        System.out.println();
                        System.out.println("Moving NORTH...");
                    } else {
                        System.out.println();
                        System.out.println("Cannot move there!");
                    }
                    break;
                case "west": case "w":
                    if (maze.movePlayer(Mob.DIRECTION.WEST)) {
                        System.out.println();
                        System.out.println("Moving NORTH...");
                    } else {
                        System.out.println();
                        System.out.println("Cannot move there!");
                    }
                    break;
                case "quit": case "q": case "exit": case "ex":
                    System.out.println();
                    System.out.println("Exiting...");
                    finish = true;
                    break;
                default:
                    System.out.println();
                    System.out.println("Unknown command");
            }
        } while (! finish);

        System.exit(0);
    }
}
