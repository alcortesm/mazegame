package mazegame.server;

import mazegame.core.Map;
import mazegame.core.End;

public interface ServerSpec {
    Map generateMap();
    End generateEnd();
}
