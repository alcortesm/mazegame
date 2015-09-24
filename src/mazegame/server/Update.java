package mazegame.server;

import mazegame.core.Place;

public class Update {

    private Place place;
    private Icon icon;

    public Update(Place place, Icon icon) {
        if (place == null) {
            throw new NullPointerException("place");
        }
        if (icon == null) {
            throw new NullPointerException("icon");
        }
        this.place = place;
        this.icon = icon;
    }

    public int getRow() {
        return place.getRow();
    }

    public int getCol() {
        return place.getCol();
    }

    public Icon getIcon() {
        return icon;
    }
}
