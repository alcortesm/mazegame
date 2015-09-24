// This class holds all the information a client needs to renderize
// the game status to the player.

package mazegame.server;

import mazegame.util.Array;

public class ClientView {

    private Icon[][] icons;
    private boolean  isGameOver;
    private boolean  isHeroAlive;

    public ClientView(Icon[][] icons,
            boolean isGameOver,
            boolean isHeroAlive) {
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
        this.isGameOver = isGameOver;
        this.isHeroAlive = isHeroAlive;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean isHeroAlive() {
        return isHeroAlive;
    }

    public Icon[][] getTopView() {
        return icons;
    }
}
