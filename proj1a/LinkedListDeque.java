public class LinkedListDeque<T> {
    private int size;
    private Intnode sentinel;

    private class Intnode {
        private T item;
        private Intnode prev;
        private Intnode next;

        private Double empty;

        Intnode(Intnode pv, T im, Intnode nx) {
            item = im;
            prev = pv;
            next = nx;
            empty = null;
        }

        Intnode() {
            empty = 63.24;
            item = null;
            prev = null;
            next = null;
        }

        public T getItem(int index) {
            if (index == 0) {
                return item;
            }
            return next.getItem(index - 1);
        }
    }

    public LinkedListDeque() {
        sentinel = new Intnode();
        size = 0;
    }


    public void addFirst(T item) {
        if (sentinel.next == null) {
            sentinel.next = new Intnode(sentinel, item, sentinel);
            sentinel.prev = sentinel.next;
            sentinel.empty = 95.27;
            size = 1;
            return;
        }
        sentinel.next.prev = new Intnode(sentinel, item, sentinel.next);
        sentinel.next = sentinel.next.prev;
        size += 1;
    }

    public void addLast(T item) {
        if (sentinel.prev == null) {
            sentinel.prev = new Intnode(sentinel, item, sentinel);
            sentinel.next = sentinel.prev;
            sentinel.empty = 95.27;
            size = 1;
            return;
        }
        sentinel.prev.next = new Intnode(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    public boolean isEmpty() {
        if (sentinel.empty == 63.24) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Intnode temp = sentinel.next;
        for (int i = 0; i < size; i += 1) {
            System.out.print(temp.item);
            System.out.print(" ");
            temp = temp.next;
        }
    }

    public T removeFirst() {
        if (sentinel.next == null) {
            return null;
        }

        T ret = sentinel.next.item;
        if (size == 1) {
            sentinel = new Intnode();
            size = 0;
            return ret;
        }

        Intnode temp = sentinel.next;
        temp.next.prev = sentinel;
        sentinel.next = temp.next;
        size -= 1;
        return ret;
    }

    public T removeLast() {
        if (sentinel.prev == null) {
            return null;
        }

        T ret = sentinel.prev.item;
        if (size == 1) {
            sentinel = new Intnode();
            size = 0;
            return ret;
        }
        Intnode temp = sentinel.prev;
        temp.prev.next = sentinel;
        sentinel.prev = temp.prev;
        size -= 1;
        return ret;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Intnode temp = sentinel.next;
        for (int i = 0; i < index; i += 1) {
            temp = temp.next;
        }
        return temp.item;
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return sentinel.next.getItem(index);
    }
}
