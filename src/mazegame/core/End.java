package mazegame.core;

import mazegame.server.Icon;

public class End implements HasIcon, HasPlace {

    private Place place;

    public End(Place place) {
        if (place == null) {
            throw new NullPointerException("place");
        }
        if (! place.isWalkable()) {
            throw new IllegalArgumentException(
                    "place is not walkable");
        }
        this.place = place;
    }

    public Icon getIcon() {
        return Icon.END;
    }

    public Place getPlace() {
        return place;
    }
}
