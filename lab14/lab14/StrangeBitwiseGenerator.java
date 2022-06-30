package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    int period;
    int state;

    public StrangeBitwiseGenerator(int period) {
        this.period = period;
        state = 0;
    }

    public double next() {
        state = (state + 1);
        int weirdState = state & (state >> 7) % period;
        return normalize(weirdState);
    }

    public double normalize(int state) {
        return state % period * (2.0 / (period - 1)) - 1;
    }
}
