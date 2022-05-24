package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
public class PercolationStats {
    private final double[] data;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        data = new double[T];
        for (int i = 0; i < T; i += 1) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int random = StdRandom.uniform(N);
                int random1 = StdRandom.uniform(N);
                if (p.isOpen(random, random1)) {
                    continue;
                }
                p.open(random, random1);
            }
            data[i] = p.numberOfOpenSites() / (float) (N * N);
        }

    }  // perform T independent experiments on an N-by-N grid
    public double mean() {
        return StdStats.mean(data);
    }                                          // sample mean of percolation threshold
    public double stddev()  {
        return StdStats.stddev(data);
    }                                       // sample standard deviation of percolation threshold
    public double confidenceLow() {
        double mean = mean();
        return mean - (1.96 * stddev()) / Math.sqrt(data.length);
    }                                 // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        double mean = mean();
        return mean + (1.96 * stddev()) / Math.sqrt(data.length);
    }                               // high endpoint of 95% confidence interval
}
