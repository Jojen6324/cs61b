package hw4.puzzle;
import edu.princeton.cs.algs4.BlackFilter;
import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    int[][] tiles;
    final int BLANK = 0;

    public Board(int[][] t) {
        tiles = new int[t.length][t.length];
        for(int i = 0; i < t.length; i += 1) {
            System.arraycopy(t[i], 0, tiles[i], 0, t.length);
        }
    }
    public int tileAt(int i, int j) {
        return tiles[i][j];
    }
    public int size() {
        return tiles.length;
    }

    /* @Source http://joshh.ug/neighbors.html */
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }

        /* copy tiles from this.tiles */
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }

        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int hug = size();
        int total = 0;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tiles[rug][tug] == BLANK) {
                    continue;
                }
                int num = num(tug, rug);
                if (tiles[rug][tug] != num) {
                    total += 1;
                }
            }
        }
        return total;
    }

    private int num(int x, int y) {
        return y * size() + x + 1;
    }
    public int manhattan() {
        int hug = size();
        int total = 0;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tiles[rug][tug] == BLANK) {
                    continue;
                }
                int oriX = (tiles[rug][tug]- 1) % hug;
                int diffX = Math.abs(tug - oriX);
                int diffY = Math.abs(rug - (tiles[rug][tug]- oriX) / hug);
                total += diffX + diffY;
            }
        }
        return total;
    }
    public int estimatedDistanceToGoal() {
        return manhattan();
    }
    public boolean equals(Object y) {
        if (!y.getClass().equals(this.getClass())) {
            return false;
        }
        int hug = size();
        Board b = (Board) y;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (b.tiles[rug][tug] != this.tiles[rug][tug]){
                    return false;
                }
            }
        }
        return true;
    }


    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
