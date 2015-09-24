// This interface allows for entities and tiles to be inspected
// by the clients.

package mazegame.core;

import mazegame.server.Icon;

interface HasIcon {
    Icon getIcon();
}
