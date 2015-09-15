package mazegame.core;

class Player extends Mob {

    private static Icon ICON = new Icon('@');

    Player(Place place) {
        super(place, ICON);
    }

    public char getIconChar() {
        return icon.getChar();
    }
}
