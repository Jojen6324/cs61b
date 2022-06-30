package lab14;

import edu.princeton.cs.algs4.StdAudio;
import lab14lib.Generator;

public class SawToothGenerator implements Generator {

    int period;
    int state;

    SawToothGenerator(int period) {
        this.period = period;
        state = 0;
    }

    public double next() {
        state = (state + 1);
        return normalize();
    }

    public double normalize() {
        return state % period * (2.0 / (period - 1)) - 1;
    }
}
