package mazegame.core;

import mazegame.core.Footprint;

class TrailFake implements Trail {

    TrailFake() {}
    public void add(Place place) {}
    public Footprint[] getAll() { return new Footprint[0]; }
}
