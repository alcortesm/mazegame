// Random List: Unordered collection of elements.
//
// Removing will remove a random element.
//
// Inserting nulls is not allowed.
//
// Implementation should decide if duplicated elements are allowed.
// But we recommend to allow them as add and set do not throw
// exceptions for that case and do not have a return value.

package mazegame.util;

import java.util.NoSuchElementException;

public interface RandomList<E> {
    int     size();
    boolean isEmpty();

    void    add(E e) throws NullPointerException;
    void    add(List<E> l) throws NullPointerException;
    E       remove() throws NoSuchElementException;
    void    clear();
}
