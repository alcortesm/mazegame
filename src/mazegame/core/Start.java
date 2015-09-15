package mazegame.core;

import mazegame.server.Icon;

class Start extends Thing {

    private static Icon ICON = new Icon('s');

    Start(Place place) {
        super(place, ICON);
    }

    public char getIconChar() {
        return icon.getChar();
    }
}
