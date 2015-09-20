package mazegame.core;

import mazegame.server.Icon;

public abstract class Entity implements HasIcon, HasPlace {

    protected Place place;

    public Entity(Place place) {
        if (place == null) {
            throw new NullPointerException("place");
        }
        if (! place.isWalkable()) {
            throw new IllegalArgumentException(
                    "place is not walkable");
        }
        this.place = place;
    }

    public Place getPlace() {
        return place;
    }
}
