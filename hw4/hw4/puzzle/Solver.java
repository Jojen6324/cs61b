package hw4.puzzle;

import java.util.ArrayDeque;

public class Solver {

    private final ArrayDeque<WorldState> solution;
    private final int move;
    public Solver(WorldState initial) {
        Astar a = new Astar(initial);
        Astar.Node so = a.Search();
        solution = new ArrayDeque<>();
        move = so.move;

        while (so != null) {
            solution.addFirst(so.world);
            so = so.prev;
        }
    }
    public int moves() {
        return move;
    }
    public Iterable<WorldState> solution() {
        return solution;
    }
}
