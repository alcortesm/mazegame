package mazegame.server;

import mazegame.util.Array;

public class ClientView {

    private Icon[][] icons;
    private boolean  isGameOver;
    private boolean  isHeroAlive;
    private String   lastMsgResult;

    public ClientView(Icon[][] icons,
            boolean isGameOver,
            boolean isHeroAlive,
            String lastMsgResult) {
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
        if (lastMsgResult == null) {
            throw new NullPointerException("lastMsgResult");
        }
        this.icons = icons;
        this.isGameOver = isGameOver;
        this.isHeroAlive = isHeroAlive;
        this.lastMsgResult = lastMsgResult;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public boolean isHeroAlive() {
        return isHeroAlive;
    }

    public String lastMsgResult() {
        return lastMsgResult;
    }

    public Icon[][] getTopView() {
        return icons;
    }


}
