public class LinkedListDeque<T> implements Deque<T> {
    private int size;
    public intnode sentinel;

    private class intnode {
        public T item;
        public intnode prev;
        public intnode next;

        private Double empty;

        public intnode (intnode pv, T im, intnode nx) {
            item = im;
            prev = pv;
            next = nx;
            empty = null;
        }

        public intnode () {
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
        sentinel = new intnode();
        size = 0;
    }

    public LinkedListDeque(T item) {
        sentinel = new intnode();
        sentinel.next = new intnode(sentinel,item,sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    @Override
    public void addFirst(T item) {
        if (sentinel.next == null) {
            sentinel.next = new intnode(sentinel,item,sentinel);
            sentinel.prev = sentinel.next;
            sentinel.empty = 95.27;
            size = 1;
            return;
        }
        sentinel.next.prev = new intnode(sentinel,item,sentinel.next);
        sentinel.next = sentinel.next.prev;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (sentinel.prev == null) {
            sentinel.prev = new intnode(sentinel,item,sentinel);
            sentinel.next = sentinel.prev;
            sentinel.empty = 95.27;
            size = 1;
            return;
        }
        sentinel.prev.next = new intnode(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.empty == 63.24) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        intnode temp = sentinel.next;
        for (int i = 0; i < size; i += 1) {
            System.out.print(temp.item);
            System.out.print(" ");
            temp = temp.next;
        }
    }

    @Override
    public T removeFirst() {
        if (sentinel.next == null) {
            return null;
        }

        T ret = sentinel.next.item;
        if (size == 1) {
            sentinel = new intnode();
            size = 0;
            return ret;
        }

        intnode temp = sentinel.next;
        temp.next.prev = sentinel;
        sentinel.next = temp.next;
        size -= 1;
        return ret;
    }

    @Override
    public T removeLast() {
        if (sentinel.prev == null) {
            return null;
        }

        T ret = sentinel.prev.item;
        if (size == 1) {
            sentinel = new intnode();
            size = 0;
            return ret;
        }
        intnode temp = sentinel.prev;
        temp.prev.next = sentinel;
        sentinel.prev = temp.prev;
        size -= 1;
        return ret;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        intnode temp = sentinel.next;
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