package mazegame.server;

import mazegame.core.Map;

public class ServerSpecTest implements ServerSpec {

    public Map generateMap() {
        Icon[][] icons = new Icon[][]{
            {Icon.WALL}
        };
        return new Map(icons);
    }
}
