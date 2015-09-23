// RandomLists implementation using and array.
//
// Duplicated elements are allowed.

package mazegame.util;

import java.util.NoSuchElementException;
import java.util.Random;

public class RandomListArray<E> implements RandomList<E> {

    // Same default capacity as java.util.ArrayList
    // not really important
    private static final int DEFAULT_CAPACITY = 16;

    private E[] array;
    private int size;
    private Random random;

    public RandomListArray() {
        size = 0;
        // We whould like to initialize our array like this:
        //    array = new E[DEFAULT_CAPACITY];
        // but Java does not allow creating "generic" arrays.
        // The following 3 lines is a workaround:
        @SuppressWarnings("unchecked")
        E[] workaround = (E[]) new Object[DEFAULT_CAPACITY];
        this.array = workaround;
        this.random = new Random();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // creates a new array with double the capacity of the old one
    // and copies the data from the old one to the new one
    private void grow() {
        int newLength;
        newLength = array.length * 2;
        @SuppressWarnings("unchecked")
        E[] workaround = (E[]) new Object[newLength];
        // there are better ways to copy big arrays, but they are
        // not part of this course
        for (int i=0; i<array.length; i++) {
            workaround[i] = array[i];
        }
        this.array = workaround;
    }

    public void add(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        if (size == array.length) {
            grow();
        }
        array[size++] = e;
    }

    public void ensureCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        while (array.length <= capacity) {
            grow();
        }
    }

    public int capacity() {
        return array.length;
    }

    public void add(List<E> l) {
        if (l == null) {
            throw new NullPointerException();
        }
        int n = l.size();
        ensureCapacity(size + n);
        for (int i=0; i<n; i++) {
            add(l.get(i));
        }
    }

    public E remove() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int pos = random.nextInt(size);
        E retval = array[pos];
        // shift left
        for (int i=pos; i<size-1; i++) {
            array[i] = array[i+1];
        }
        array[size] = null; // helps the garbage collector
        size--;
        return retval;
    }

    public void clear() {
        // Creating a new empty array will help the garbage collector
        // a lot.
        @SuppressWarnings("unchecked")
        E[] workaround = (E[]) new Object[DEFAULT_CAPACITY];
        array = workaround;
        size = 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i=0; i<size; i++) {
            sb.append(array[i]);
            if (i != size-1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
