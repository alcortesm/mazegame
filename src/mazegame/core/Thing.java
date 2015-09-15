// Things are "game objects" on the map. Example: the starting point
// the end point, the player, a monster, a coin.
//
// They are located in a Place and they can not move (see the Mob
// class for mobile things).
//
// They are always visible (they have an Icon).

package mazegame.core;

import mazegame.server.Icon;

abstract class Thing implements HasIcon {

    protected Place place;
    protected Icon icon;

    Thing(Place place, Icon icon) {
        if (place == null) {
            throw new NullPointerException("place");
        }
        if (icon == null) {
            throw new NullPointerException("icon");
        }
        this.place = place;
        this.icon = icon;
    }

    Place getPlace() {
        return place;
    }

    // Returns the src string, which is assumed to be a
    // map represented as an string, modified with the icon
    // at the position specified by the place.
    String insertMeIntoMapString(String src) {
        StringBuilder sb = new StringBuilder(src);
        sb.setCharAt(getMapStringIndex(), this.icon.getChar());
        return sb.toString();
    }

    // Returns the string index of a place
    // in the string representation of its map
    private int getMapStringIndex() {
        // A row holds as many chars as the number of columns
        // plus 2 (the two east and west wall characters)
        // plus the eol charactters
        int rowLength = place.getMap().getNumColumns() + 2 +
            System.lineSeparator().length();
        int index = 0;
        // add the proper number of rows (+1 as the first row will
        // always be the north wall).
        index += (place.getRow() + 1) * rowLength;
        // add the proper number of columns (+1 as the first column
        // will always be the east wall character.
        index += place.getColumn() + 1;
        return index;
    }

    boolean isAt(Place place) {
        return this.getPlace().equals(place);
    }

    boolean sharePlaceWith(Thing other) {
        return isAt(other.getPlace());
    }
}
