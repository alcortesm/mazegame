// Queue implemented as a linked list.
//
// This aprticular implementation:
// - is infinite

package mazegame.util;

import java.util.NoSuchElementException;

public class QueueLinked<E> implements Queue<E> {

    private class Node<E> {
        E datum;
        Node<E> next;
        Node(E datum, Node<E> next) {
            this.datum = datum;
            this.next = next;
        }
    }

    private Node<E> first; // if empty, first is null.
    private Node<E> last;  // if empty, last is null.

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(E e)
        throws NullPointerException {
        if (e == null) {
            throw new NullPointerException();
        }
        Node<E> newLast = new Node<E>(e, null);
        if (isEmpty()) {
            first = newLast;
        } else {
            last.next = newLast;
        }
        last = newLast;
    }

    public E dequeue() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E retval = first.datum;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        return retval;
    }

    public E front() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return first.datum;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("");
        if (! isEmpty()) {
            for (Node<E> current=first;
                   current!=last;
                   current=current.next) {
                sb.insert(0, current.datum);
                sb.insert(0, ", ");
            }
            sb.insert(0, last.datum);
            sb.append(" ");
        }
        sb.insert(0, "Queue {");
        sb.append("(front)}");
        return sb.toString();
    }
}
