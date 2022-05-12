package synthesizer;


import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    private class QueueIterator implements Iterator<T> {
        int wizPos;
        int cap;

        private ArrayRingBuffer<T> rbc;
        QueueIterator() {
            wizPos = first;
            cap = 0;
        }

        public boolean hasNext() {
            return cap < capacity;
        }

        public T next() {
            if (wizPos == rb.length) {
                wizPos = 0;
            }
            T returnItem = rb[wizPos];
            wizPos += 1;
            cap += 1;
            return returnItem;
        }

    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
        first = 0;
        last = 0;
        this.fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("There is no room");
        }
        if (last == rb.length) {
            last = 0;
        }
        rb[last] = x;
        last += 1;
        this.fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (this.isEmpty()) {
            throw new RuntimeException("Can't dequeue from a empty queue");
        }
        T returnItem = rb[first];
        rb[first] = null;
        this.fillCount -= 1;
        first += 1;
        if (isEmpty()) {
            first = 0;
            last = 0;
        }

        if (first == rb.length) {
            first = 0;
        }
        return returnItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("No items in the ring");
        }
        return rb[first];
    }

    public Iterator<T> iterator() {
        return new QueueIterator();
    }
}
