/*******************************************************************************
 *
 * This is the object class for Guitar Strings. Guitar Strings can be manipulated
 * (plucked, 'tic'ed, or sampled in this object.
 *
 ******************************************************************************/

public class GuitarString {
    private final RingBuffer guitarString; // creates a new ring buffer
    private final int n; // the capacity of ring buffer


    // creates a guitar string of the specified frequency,
    // using sampling rate of 44,100
    public GuitarString(double frequency) {
        double SAMPLING_RATE = 44100.0;
        n = (int) Math.ceil(SAMPLING_RATE / frequency);
        guitarString = new RingBuffer(n);

        // enqueues 0's in order to increment last counter
        for (int i = 0; i < n; i++) {
            guitarString.enqueue(0.0);
        }

    }

    // creates a guitar string whose size and initial values are given by
    // the specified array
    public GuitarString(double[] init) {
        n = init.length;
        guitarString = new RingBuffer(n);
        for (int i = 0; i < n; i++) {
            guitarString.enqueue(init[i]);
        }
    }

    // returns the number of samples in the ring buffer
    public int length() {
        return n;
    }

    // plucks the guitar string (by replacing the buffer with white noise)
    public void pluck() {
        for (int i = 0; i < n; i++) {
            guitarString.dequeue();
            // enqueues random value to represent white noise
            guitarString.enqueue(StdRandom.uniform(-0.5, 0.5));
        }
    }

    // advances the Karplus-Strong simulation one time step
    public void tic() {
        double DECAY_FACTOR = 0.996;
        // averages the first 2 values, multiplies them by the decay
        // and deletes the first value from the ring buffer
        double x = guitarString.dequeue();
        double y = guitarString.peek();
        guitarString.enqueue(((x + y) / 2) * DECAY_FACTOR);
    }

    // returns the current sample
    public double sample() {
        return guitarString.peek();
    }


    // tests and calls every constructor and instance method in this class
    public static void main(String[] args) {
        double[] x = { 1, 2, 3, 4, 5 };
        GuitarString a = new GuitarString(330);
        GuitarString b = new GuitarString(x);
        StdOut.println("A's Current Length: " + a.length());
        a.pluck();
        a.tic();
        StdOut.println("A's Current Sample: " + a.sample());
        StdOut.println("B's Current Length: " + b.length());
        b.pluck();
        b.tic();
        StdOut.println("B's Current Sample: " + b.sample());
    }

}


