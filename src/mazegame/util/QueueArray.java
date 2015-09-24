// Queue implemented as an array.
//
// This aprticular implementation:
// - the constructor receives the max capacity
// - has bounded capacity (enqueue throws IllegalStateException)
//
// This is usually called a BUFFER.

package mazegame.util;

import java.util.NoSuchElementException;

public class QueueArray<E> implements Queue<E> {

    private static final int DEFAULT_CAPACITY = 16;

    private E[] array;
    private int first; // if empty, first is -1.
    private int last;  // if empty, last is -1.

    public QueueArray(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException();
        }
        @SuppressWarnings("unchecked")
        E[] workaround = (E[]) new Object[capacity];
        array = workaround;
        first = -1;
        last = -1;
    }

    public QueueArray() {
        this(DEFAULT_CAPACITY);
    }

    public boolean isEmpty() {
        return first == -1;
    }

    private boolean isFull() {
        return next(last) == first;
    }

    private int next(int i) {
        return (++i % array.length);
    }

    public void enqueue(E e)
        throws NullPointerException, IllegalStateException {
        if (e == null) {
            throw new NullPointerException();
        }
        if (isFull()) {
            throw new IllegalStateException();
        }
        if (isEmpty()) {
            first = 0;
        }
        last = next(last);
        array[last] = e;
    }

    public E dequeue() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E retval = array[first];
        array[first] = null; // to help the garbage collector
        if (first == last) {
            first = -1;
            last = -1;
        } else {
            first = next(first);
        }
        return retval;
    }

    public E front() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return array[first];
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("");
        if (! isEmpty()) {
            for (int i=first; i!=last; i=next(i)) {
                sb.insert(0, array[i]);
                sb.insert(0, ", ");
            }
            sb.insert(0, array[last]);
            sb.append(" ");
        }
        sb.insert(0, "Queue {");
        sb.append("(front)}");
        return sb.toString();
    }
}
