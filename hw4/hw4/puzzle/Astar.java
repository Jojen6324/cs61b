package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;


public class Astar {

    MinPQ<Node> queue;

    /* A class for search node */
    protected static class Node implements Comparable<Node> {
        WorldState world;
        int move;
        Node prev;

        int toGoal;

        Node(WorldState w, int m, Node p) {
            world = w;
            move = m;
            prev = p;
            toGoal = w.estimatedDistanceToGoal();
        }

        @Override
        public int compareTo(Node o) {
            return (toGoal + this.move) - (o.toGoal + o.move);
        }
    }

    /* initialize */
    Astar(WorldState w) {
        queue = new MinPQ<>();
        queue.insert(new Node(w, 0, null));

    }

    public Node Search() {
        Node del = queue.delMin();
        while (!del.world.isGoal()) {
            for (WorldState w : del.world.neighbors()) {
                if (del.prev!= null && w.equals(del.prev.world)) {
                    continue;
                }
                queue.insert(new Node(w, del.move + 1, del));
            }
            del = queue.delMin();
        }
        return del;
    }
}
