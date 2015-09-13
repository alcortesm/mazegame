package mazegame;

import java.util.Scanner;

class Main {

    public static void main(String args[]) {
        Map map = MapGenerator.generate(
                MapGenerator.TYPE.SMALL_EMPTY);
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
                    System.out.println();
                    System.out.println("Moving NORTH...");
                    break;
                case "south": case "s":
                    System.out.println();
                    System.out.println("Moving SOUTH...");
                    break;
                case "east": case "e":
                    System.out.println();
                    System.out.println("Moving EAST...");
                    break;
                case "west": case "w":
                    System.out.println();
                    System.out.println("Moving WEST...");
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
