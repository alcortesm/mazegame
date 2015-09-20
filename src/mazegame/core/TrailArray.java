package mazegame.core;

import mazegame.core.Footprint;

class TrailArray implements Trail {

    private Footprint[] tracks;
    private int capacity;
    private int size; // current number of elements in the collection
    private int oldest;
    private int newest;

    TrailArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity was <= 0");
        }
        this.capacity = capacity;
        this.tracks = new Footprint[capacity];
        this.size = 0;
        // -1 means there is no oldest or newest yet
        this.oldest = -1;
        this.newest = -1;
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
        } else {
            tracks[oldest] = new Footprint(p);
            newest = oldest;
            oldest = (++oldest) % capacity;
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
}
