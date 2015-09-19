package mazegame.server;

import mazegame.core.Map;

public class ServerSpecTest implements ServerSpec {

    public Map generateMap() {
        Icon[][] icons = new Icon[][]{
            {Icon.EMPTY, Icon.WALL, Icon.EMPTY, Icon.WALL, Icon.EMPTY, Icon.EMPTY, Icon.EMPTY, Icon.WALL},
            {Icon.EMPTY, Icon.WALL, Icon.EMPTY, Icon.EMPTY, Icon.EMPTY, Icon.WALL, Icon.EMPTY, Icon.EMPTY},
            {Icon.EMPTY, Icon.WALL, Icon.EMPTY, Icon.WALL, Icon.WALL, Icon.WALL, Icon.EMPTY, Icon.WALL},
            {Icon.EMPTY, Icon.EMPTY, Icon.EMPTY, Icon.WALL, Icon.EMPTY, Icon.EMPTY, Icon.EMPTY, Icon.EMPTY},
        };
        return new Map(icons);
    }
}
