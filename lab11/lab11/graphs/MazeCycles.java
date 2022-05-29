package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        dfs(0, 0);
    }

    private void dfs(int v, int p) {
        marked[v] = true;
        announce();
        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                dfs(w, v);
            }
            if (marked[w] && w != p) {
                edgeTo[v] = p;
            }
        }
    }

    // Helper methods go here
}

