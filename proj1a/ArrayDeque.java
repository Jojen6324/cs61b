/** Create a Deque using array as core data data structure */
public class ArrayDeque<T> {
    private int prevsize;
    private int nextsize;

    T[] a = (T[]) new Object[8];

    /** Create an empty Deque */
    public ArrayDeque() {
        prevsize = 0;
        nextsize = 0;
    }

    /** Initialize a Deque */
    public ArrayDeque(T item) {
        a[0] = item;
        prevsize = 0;
        nextsize = 1;
    }

    /** A helper method for resize */
    private void resize() {
        T[] temp = (T[]) new Object[a.length * 2];
        System.arraycopy(a, 0, temp, 0, nextsize);
        System.arraycopy(a, a.length - prevsize, temp, temp.length - prevsize, prevsize);
        a = temp;
    }

    /** A helper method for resize */
    private void desize() {
        T[] temp = (T[]) new Object[a.length / 2];
        System.arraycopy(a, 0, temp, 0, nextsize);
        System.arraycopy(a, a.length - prevsize, temp, temp.length - prevsize, prevsize);
        a = temp;
        System.out.println(a.length);
    }

    /** Add item at 0th position */
    public void addFirst(T item) {
        if (prevsize + nextsize == a.length) {
            resize();
        }
        prevsize += 1;
        a[a.length - prevsize] = item;
    }

    /** Add item on the end of the Deque */
    public void addLast(T item) {
        if (prevsize + nextsize == a.length) {
            resize();
        }
        a[nextsize] = item;
        nextsize += 1;
    }

    /** A method check whether an empty deque */
    public boolean isEmpty() {
        if (nextsize + prevsize == 0) {
            return true;
        }
        return false;
    }

    /** Return size of the deque */
    public int size() {
        return prevsize + nextsize;
    }

    public void printDeque() {
        if (isEmpty()) {
            System.out.println();
        }
        for (int i = 0; i < prevsize; i += 1) {
            System.out.print(a[a.length - prevsize + i].toString() + " ");
        }
        for (int i = 0; i < nextsize; i += 1) {
            System.out.print(a[i].toString() + " ");
        }
        System.out.println();
    }

    /** Remove item at the 0th position of the deque */
    public T removeFirst() {
        if (prevsize + nextsize < a.length * 0.25 && a.length > 16) {
            desize();
        }
        T ret;
        if (prevsize == 1 || prevsize == 0) {
            ret = a[0];
            T[] temp = (T[]) new Object[a.length];
            System.arraycopy(a, 1, temp, 0, a.length - 1);
            a = temp;
            nextsize -= 1;
            return ret;
        }
        ret = a[a.length - 1 - prevsize];
        a[a.length - 1 - prevsize] = null;
        prevsize -= 1;
        return ret;
    }

    /** Remove item on the end of the Deque */
    public T removeLast() {
        if (prevsize + nextsize < a.length * 0.25 && a.length > 16) {
            desize();
        }
        T ret = a[nextsize - 1];
        a[nextsize - 1] = null;
        nextsize -= 1;
        return ret;
    }

    /** Get item with index */
    public T get(int index) {
        if (index >= prevsize) {
            return a[index - prevsize];
        }
        return a[a.length - prevsize + index];
    }
}

