// The maze will be populated with "entities".
//
// Entities will have a place in the maze and an Icon so they can
// be rendered by the clients.
//
// Entities are static, this is, their place can not be changed once
// the object is constructed. The Mob subclass allows for moving
// entities.

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
