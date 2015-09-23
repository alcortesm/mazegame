// Lists implementation using and array.
//
// It will try to save memory by keeping the array capacity
// close to the current size of the list.
//
// Duplicated elements are allowed.

package mazegame.util;

import java.util.NoSuchElementException;

public class ListArray<E> implements List<E> {

    // java.util.ArrayList default is 16
    private static final int DEFAULT_CAPACITY = 16;
    private static final int MIN_CAPACITY = DEFAULT_CAPACITY;

    private E[] array;
    private int size;

    public ListArray(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        size = 0;
        // We whould like to initialize our array like this:
        //    array = new E[DEFAULT_CAPACITY];
        // but Java does not allow creating "generic" arrays.
        // The following 3 lines is a workaround:
        @SuppressWarnings("unchecked")
        E[] workaround = (E[]) new Object[capacity];
        this.array = workaround;
    }

    public ListArray() {
        this(DEFAULT_CAPACITY);
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

    public void add(int i, E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == array.length) {
            grow();
        }
        // shift elements to the right
        for (int j=size-1; j>=i; j--) {
            array[j+1] = array[j];
        }
        // insert new element
        array[i] = e;
        size++;
    }

    public void add(E e) {
        add(size, e);
    }

    public E get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[i];
    }

    public void set(int i, E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }
        array[i] = e;
    }

    public void clear() {
        // Creating a new empty array will help the garbage collector
        // a lot.
        @SuppressWarnings("unchecked")
        E[] workaround = (E[]) new Object[DEFAULT_CAPACITY];
        array = workaround;
        size = 0;
    }

    // creates a new array half the size of the old one
    // and copies all the elements of the old one into the new one.
    private void shrink() {
        int newLength;
        if (array.length / 2 < MIN_CAPACITY) {
            newLength = MIN_CAPACITY;
        } else {
            newLength = array.length / 2;
        }
        //System.out.println("shrink! " + array.length + " --> " + newLength);

        @SuppressWarnings("unchecked")
        E[] workaround = (E[]) new Object[newLength];
        // there are better ways to copy big arrays, but they are
        // not part of this course
        for (int i=0; i<size; i++) {
            workaround[i] = array[i];
        }
        this.array = workaround;
    }

    public E remove(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        }
        E retval = array[i];
        // shift elements to the left
        for (int j=i; j<(size-1); j++) {
            array[j] = array[j+1];
        }
        size--;
        array[size] = null; // helps the garbage collector a bit
        if ((array.length > MIN_CAPACITY)
                && (size <= (array.length / 4))) {
            shrink();
        }
        return retval;
    }

    public int indexOf(E e) {
        for (int i=0; i<size; i++) {
            if (e.equals(array[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    public boolean contains(E e) {
        try {
            indexOf(e);
        } catch (NoSuchElementException ex) {
            return false;
        }
        return true;
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
