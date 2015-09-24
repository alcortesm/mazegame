// A fake trail to use when the user specify a 0 trail.
//
// The point is to introduce the student to fake objects for avoiding
// if in the control logic.

package mazegame.core;

import mazegame.core.Footprint;
import mazegame.util.Queue;
import mazegame.server.Update;

class TrailFake implements Trail {

    TrailFake() {}
    public void add(Place place) {}
    public Footprint[] getAll() { return new Footprint[0]; }
    public void update(Queue<Update> updates) { return; }
}
