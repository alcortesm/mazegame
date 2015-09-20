package mazegame.core;

import mazegame.server.Icon;

public class Hero extends Mob {

    public Hero(Place place) {
        super(place);
    }

    public Icon getIcon() {
        return Icon.HERO;
    }
}
