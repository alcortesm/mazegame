package mazegame.server;

import mazegame.util.Array;

public class ClientView {

    private Icon[][] icons;

    public ClientView(Icon[][] icons) {
        if (icons == null) {
            throw new NullPointerException("icons");
        }
        if (Array.hasNull(icons)) {
            throw new NullPointerException("map icons has nulls");
        }
        if (! Array.isRect(icons)) {
            throw new IllegalArgumentException(
                    "map is not rectangular");
        }
        this.icons = icons;
    }

    public boolean isGameOver() {
        return false;
    }

    public boolean isHeroAlive() {
        return true;
    }

    public String lastMsgResult() {
        return "TODO";
    }

    public Icon[][] getTopView() {
        return icons;
    }


}
