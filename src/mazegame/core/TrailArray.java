package mazegame.core;

import mazegame.core.Footprint;

class TrailArray implements Trail {

    private Footprint last;

    TrailArray() {}

    public void add(Place p) {
        last = new Footprint(p);
    }

    public Footprint[] getAll() {
        if (last == null) {
            return new Footprint[0];
        }
        Footprint[] a = new Footprint[1];
        a[0] = last;
        return a;
    }
}
