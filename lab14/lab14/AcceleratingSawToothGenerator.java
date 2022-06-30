package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    int period;
    int state;

    double factor;

    public AcceleratingSawToothGenerator(int period, double factor) {
        this.period = period;
        this.factor = factor;
        state = 0;
    }

    public double next() {
        state = (state + 1);
        if (state == period - 1) {
            period = (int) Math.round(period * factor);
            state = 0;
        }
        return normalize();
    }

    public double normalize() {
        return state % period * (2.0 / (period - 1)) - 1;
    }
}
