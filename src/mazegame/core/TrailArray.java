// Array implementation of a Trail.

package mazegame.core;

import mazegame.core.Footprint;
import mazegame.util.Queue;
import mazegame.util.QueueLinked;
import mazegame.server.Update;
import mazegame.server.Icon;

class TrailArray implements Trail {

    private Footprint[] tracks;
    private int capacity;
    private int size; // current number of elements in the collection
    private int oldest; // -1 if empty
    private int newest; // -1 if empty
    private QueueLinked<Update> updates;

    TrailArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity was <= 0");
        }
        this.capacity = capacity;
        this.tracks = new Footprint[capacity];
        this.size = 0;
        this.oldest = -1;
        this.newest = -1;
        updates = new QueueLinked<Update>();
    }

    public void add(Place p) {
        if (p == null) {
            throw new NullPointerException("p");
        }
        if (size != capacity) {
            newest = (++newest) % capacity;
            tracks[newest] = new Footprint(p);
            if (size == 0) {
                oldest = 0;
            }
            size++;
            // updates
            Update u = new Update(p, Icon.FOOTPRINT);
            updates.enqueue(u);
        } else {
            updateRemoveTrail();
            tracks[oldest] = new Footprint(p);
            newest = oldest;
            oldest = (++oldest) % capacity;
            updateAddTrail();
        }
    }

    private void updateRemoveTrail() {
        Footprint[] tracks = getAll();
        for (int i=0; i<tracks.length; i++) {
            Footprint current = tracks[i];
            Place p = current.getPlace();
            Map m = p.getMap();
            int r = p.getRow();
            int c = p.getCol();
            Tile t = m.getTile(r, c);
            Update u = new Update(p, t.getIcon());
            updates.enqueue(u);
        }
    }

    private void updateAddTrail() {
        Footprint[] tracks = getAll();
        for (int i=0; i<tracks.length; i++) {
            Footprint current = tracks[i];
            Place p = current.getPlace();
            Update u = new Update(p, Icon.FOOTPRINT);
            updates.enqueue(u);
        }
    }

    public Footprint[] getAll() {
        Footprint[] a = new Footprint[size];
        if (size == 0) {
            return a;
        }
        if (newest >= oldest) {
            for (int i=newest, j=0; i>=oldest; i--, j++) {
                a[j] = tracks[i];
            }
        } else {
            int j=0;
            for (int i=newest; i>=0; i--, j++) {
                a[j] = tracks[i];
            }
            for (int i=capacity-1; i>=oldest; i--, j++) {
                a[j] = tracks[i];
            }
        }
        return a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TrailArray[");
        sb.append("capacity=" + capacity);
        sb.append(", size=" + size);
        sb.append(", oldest=" + oldest);
        sb.append(", newest=" + newest);
        sb.append("]");
        return sb.toString();
    }

    public void update(Queue<Update> dest) {
        while (! updates.isEmpty()) {
            dest.enqueue(updates.dequeue());
        }
    }
}
