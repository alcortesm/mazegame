// List: Ordered collection of elements.
//
// Ints are used as indexes for the elements, therefore lists can not
// address more than Integer.MAX_VALUE elements. Negative indexes
// are not be allowed.
//
// Lists are zero-indexed collections, this means the first element
// has index 0, the second element has index 1...
//
// Inserting nulls is not allowed.
//
// Implementation should decide if duplicated elements are allowed.
// But we recommend to allow them as add and set do not throw
// exceptions for that case and do not have a return value.

package mazegame.util;

import java.util.NoSuchElementException;

public interface List<E> {
    int     size();
    boolean isEmpty();

    void    add(E e) throws NullPointerException; // at the end
    void    add(int i, E e) throws NullPointerException, IndexOutOfBoundsException;
    E       remove(int i) throws IndexOutOfBoundsException;
    void    clear();

    E       get(int i) throws IndexOutOfBoundsException;
    void    set(int i, E e) throws NullPointerException, IndexOutOfBoundsException;

    boolean contains(E e);
    int     indexOf(E e) throws NoSuchElementException;

    String  toString();
}
