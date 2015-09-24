// Queue (also called a FIFO):
//
// - ordered collection
// - normally capacity is bounded (no infinite)
// - normally nulls are not allowed
// - normally duplicated elements are allowed

package mazegame.util;

import java.util.NoSuchElementException;

public interface Queue<E> {
    boolean isEmpty();
    void    enqueue(E e) throws NullPointerException;
    E       dequeue() throws NoSuchElementException;
    E       front() throws NoSuchElementException;
}
