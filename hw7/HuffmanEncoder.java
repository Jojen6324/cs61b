import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        Map<Character, Integer> ret = new HashMap<>();
        for (char c : inputSymbols) {
            if (ret.containsKey(c)) {
                ret.put(c, ret.get(c) + 1);
            } else {
                ret.put(c, 0);
            }
        }
        return ret;
    }
    public static void main(String[] args) {
        String filename = args[0];
        char[] chars = FileUtils.readFile(filename);
        Map<Character, Integer> frequencyTable = buildFrequencyTable(chars);
        BinaryTrie trie = new BinaryTrie(frequencyTable);
        ObjectWriter ow = new ObjectWriter(filename + ".huf");
        ow.writeObject(trie);
        HashMap<Character, BitSequence> bitTable =
                (HashMap<Character, BitSequence>) trie.buildLookupTable();
        LinkedList<BitSequence> bits = new LinkedList<>();
        for (char c : chars) {
            bits.addLast(bitTable.get(c));
        }
        BitSequence b = BitSequence.assemble(bits);
        ow.writeObject(b);
        ow.writeObject(chars.length);
    }
}
