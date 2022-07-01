import edu.princeton.cs.algs4.MinPQ;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BinaryTrie implements Serializable {

    private static class Node implements Serializable, Comparable<Node> {
        Node left;
        Node right;
        Character item;
        int priority;
        Node(char item, int pri, Node l, Node r) {
            this.item = item;
            priority = pri;
            left = l;
            right = r;
        }


        @Override
        public int compareTo(Node o) {
            return this.priority - o.priority;
        }
    }

    /* Check whether a char */
    private boolean isLeaf(Node n) {
        return n.right == null;
    }

    Node root;
    HashMap<Character, BitSequence> table = new HashMap<>();
    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        MinPQ<Node> queue = new MinPQ<>();
        Set<Character> keys = frequencyTable.keySet();
        /* Set each char as Node */
        for (Character c : keys) {
            queue.insert(new Node(c, frequencyTable.get(c), null, null));
        }

        /* Build Trie */
        root = queue.min();
        while (queue.size() != 1) {
            Node min1 = queue.delMin();
            Node min2 = queue.delMin();
            root = new Node(Character.MAX_HIGH_SURROGATE,
                    min1.priority + min2.priority, min1, min2);
            queue.insert(root);
        }
    }

    public Match longestPrefixMatch(BitSequence querySequence) {
        Node r = root;
        Match match = null;
        String bit = "";
        for (int i = 0; i < querySequence.length(); i += 1) {
            int b = querySequence.bitAt(i);
            if (b == 0) {
                r = r.left;
            } else {
                r = r.right;
            }
            bit = bit + b;
            if (isLeaf(r)) {
                match = new Match(new BitSequence(bit), r.item);
                break;
            }
        }
        return match;
    }

    public Map<Character, BitSequence> buildLookupTable() {
        order(root, "");
        return table;
    }

    /* Preorder function for Build Lookup-table */
    private void order(Node r, String s) {
        if (r == null) {
            return;
        } else if (isLeaf(r)) {
            table.put(r.item, new BitSequence(s));
            return;
        }
        order(r.left, s + 0);
        order(r.right, s + 1);
    }
}
