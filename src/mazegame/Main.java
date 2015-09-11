package mazegame;

class Main {
    public static void main(String args[]) {
        Map m = MapGenerator.create(
                MapGenerator.TYPE.SMALLEST_EMPTY);
        System.out.println(m);
        System.exit(0);
    }
}
