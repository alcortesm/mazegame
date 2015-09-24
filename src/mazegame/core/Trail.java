// A trail is a collection of footprints.
//
// This is a very specific type of collection, so specific that it
// does not make sense to include it in the mazegame.util package.
//
// A trail has a fixed capacity (not shown here as interfaces cannot
// define ctors). Fixing this using an abstract class will be too
// restricive for implementations that do not use arrays.
//
// You can only add to a trail, not extract. When the trail is full,
// subsequent add will delete the oldest footprint in it.
//
// Get from an array does not make sense on a one to one basic, you
// either get all the elements to show them or none.

package mazegame.core;

import mazegame.util.Queue;
import mazegame.server.Update;

public interface Trail {
    void        add(Place p);
    Footprint[] getAll();
    void        update(Queue<Update> updates);
}
