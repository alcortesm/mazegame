package mazegame;

class Main {
    public static void main(String args[]) {
        Map map = MapGenerator.generate(
                MapGenerator.TYPE.SMALLEST_EMPTY);
        Maze maze = new Maze(map);
        System.out.println(maze);

        map = MapGenerator.generate(
                MapGenerator.TYPE.SMALL_EMPTY);
        maze = new Maze(map);
        System.out.println(maze);

        System.exit(0);
    }
}
