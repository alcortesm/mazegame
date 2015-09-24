// Abstract class for all the serverspecs.
//
// The serverspecs are used by the client to create a server.

package mazegame.server;

import mazegame.core.Map;
import mazegame.core.End;
import mazegame.core.Hero;

public interface ServerSpec {
    Map generateMap();
    End generateEnd();
    Hero generateHero();
    int generateTrailCapacity();
}
