// Stack implementation using an array

package mazegame.util;

import java.util.EmptyStackException;

public class StackArray<E> implements Stack<E> {

    private static final int DEFAULT_CAPACITY = 16;

    private E[] array;
    private int top; // -1 means empty stack

    public StackArray(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException();
        }

        top = -1;
        // we would like to initialize the attribute like this:
        //    this.array = new E[capacity];
        // but Java does not allow to create "generic" arrays
        // so we use this (unsafe) workaround.
        @SuppressWarnings("unchecked")
        E[] workaround = (E[]) new Object[capacity];
        array = workaround;
    }

    public StackArray() {
        this(DEFAULT_CAPACITY);
    }

    public boolean isEmpty() {
        return top == -1;
    }

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

    public void push(E e) throws NullPointerException {
        if (e == null) {
            throw new NullPointerException();
        }
        if (top == array.length-1) {
            grow();
        }
        array[++top] = e;
    }

    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E retval = array[top];
        array[top--] = null; // to help the garbage collector
        return retval;
    }

    public E peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array[top];
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i<=top; i++) {
            sb.append(array[i].toString());
            if (i != top) {
                sb.append(", ");
            } else {
                sb.append(" ");
            }
        }
        sb.insert(0, "Stack {");
        sb.append("(top)}");
        return sb.toString();
    }
}
