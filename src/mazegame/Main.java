package mazegame;

class Main {
    public static void main(String args[]) {
        Map m = MapGenerator.generate(
                MapGenerator.TYPE.SMALLEST_EMPTY);
        System.out.println(m);

        m = MapGenerator.generate(
                MapGenerator.TYPE.SMALL_EMPTY);
        System.out.println(m);

        System.exit(0);
    }
}
