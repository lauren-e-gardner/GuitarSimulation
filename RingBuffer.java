/*******************************************************************************
 *
 *  This is the object class for RingBuffer. The RingBuffer models the way energy
 *  travels back and forth in a GuitarString
 *
 ******************************************************************************/


public class RingBuffer {
    private int first; // index for the next dequeue or peek
    private int last; // index for the next enqueue
    private int size; // number of items in the buffer
    private double[] rb; // items in the buffer

    // creates an empty ring buffer with the specified capacity
    public RingBuffer(int capacity) {
        rb = new double[capacity];
        first = 0;
        size = 0;
        last = 0;
    }

    // return the capacity of this ring buffer
    public int capacity() {
        return rb.length;
    }

    // return number of items currently in this ring buffer
    public int size() {
        return size;
    }

    // is this ring buffer empty (size equals zero)
    public boolean isEmpty() {
        return (size() == 0);
    }

    // is this ring buffer full (size equals capacity)
    public boolean isFull() {
        return (size() == capacity());
    }

    // adds item x to the end of this ring buffer
    public void enqueue(double x) {
        if (isFull()) {
            throw new RuntimeException("RingBuffer is full.");
        }
        else {
            // if last is past a viable index, this resets it to zero
            if (last == rb.length) {
                last = 0;
            }
            rb[last] = x;
            last++;
            size++;
        }
    }

    // deletes and returns the item at the front of this ring buffer
    public double dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("RingBuffer is empty.");
        }
        else {
            double a;
            a = rb[first];
            first++;
            // if first is past a viable index, this resets it to zero
            if (first == rb.length) {
                first = 0;
            }
            size--;
            return a;
        }
    }

    // returns the item at the front of this ring buffer
    public double peek() {
        if (isEmpty()) {
            throw new RuntimeException("RingBuffer is empty.");
        }
        else return rb[first];
    }

    // tests and calls every instance method in this class
    public static void main(String[] args) {
        RingBuffer a = new RingBuffer(4);
        StdOut.println("Capacity of Buffer: " + a.capacity());
        StdOut.println("Current size: " + a.size());
        a.enqueue(1.0);
        a.enqueue(2.0);
        StdOut.println("Current size after 2 enqueues: " + a.size());
        StdOut.println("Deque: " + a.dequeue());
        StdOut.println("Current Peek: " + a.peek());
        if (a.isEmpty()) {
            StdOut.println("Buffer is empty");
        }
        if (a.isFull()) {
            StdOut.println("Buffer is full");
        }
    }

}
