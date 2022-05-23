package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] world;
    private WeightedQuickUnionUF union;

    private int top;

    private int bottom;

    private int openSize;
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        world = new int[N][N];
        union = new WeightedQuickUnionUF(N * N + 2);
        openSize = 0;
        top = N * N + 2 - 2;
        bottom = N * N + 2 - 1;
        for (int i = 0; i < N; i += 1) {
            for (int k = 0; k < N; k += 1) {
                world[i][k] = 0;
            }
        }
    }               // create N-by-N grid, with all sites initially blocked

    public void open(int row, int col) {
        occur(row, col);
        if (isOpen(row, col)) {
            return;
        }
        if (row == 0) {
            union.union(top, xyToInt(row, col));
        }
        if (row == world.length - 1 && (!percolates() || isFull(row, col))) {
            union.union(bottom, xyToInt(row, col));
        }
        world[row][col] = 1;
        unionAround(row, col, row - 1, col);
        unionAround(row, col, row + 1, col);
        unionAround(row, col, row, col - 1);
        unionAround(row, col, row, col + 1);
        openSize += 1;
    }      // open the site (row, col) if it is not open already


    private void unionAround(int row, int col, int row1, int col1) {
        try {
            if (world[row1][col1] == 1) {
                union.union(xyToInt(row, col), xyToInt(row1, col1));
            }
        } catch (IndexOutOfBoundsException e) {
            return;
        }
    }

    public boolean isOpen(int row, int col) {
        occur(row, col);
        return world[row][col] == 1;
    } // is the site (row, col) open?

    private int xyToInt(int row, int col) {
        return row * world.length + col;
    }
    public boolean isFull(int row, int col) {
        occur(row, col);
        return union.connected(xyToInt(row, col), top);
    } // is the site (row, col) full?
    public int numberOfOpenSites() {
        return openSize;
    }           // number of open sites

    private void occur(int row, int col) {
        if (row < 0 || col < 0 || row >= world.length || col >= world.length) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    public boolean percolates() {
        return union.connected(top, bottom);
    }  // does the system percolate?

    public static void main(String[] args) {
        Percolation p = new Percolation(1);

        p.open(0, 0);
    }  // use for unit testing (not required)
}
