// Stack (also called a LIFO):
//
// - ordered collection
// - normally capacity is bounded (not infinite)
// - normally nulls are not allowed
// - normally duplicated elements are allowed

package mazegame.util;

import java.util.EmptyStackException;

public interface Stack<E> {
    boolean isEmpty();
    void    push(E e) throws NullPointerException;
    E       pop() throws EmptyStackException;
    E       peek() throws EmptyStackException;
}
