// There is a hero for each maze.
//
// The player will move the hero through the map until it gets to the
// end entity.
//
// Heroes are mobile, that is why this class inherits from Mob.
//
// The maze class will take care of moving the hero, generating
// the footprints and adding them to the trail.

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
