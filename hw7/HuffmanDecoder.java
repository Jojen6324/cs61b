public class HuffmanDecoder {
    public static void main(String[] args) {
        /* Read .huf file */
        ObjectReader or = new ObjectReader(args[0]);
        Object t = or.readObject();
        Object b = or.readObject();
        Object l = or.readObject();

        BinaryTrie trie = (BinaryTrie) t;
        BitSequence bits = (BitSequence) b;
        int size = (int) l;
        char[] ori = new char[size];

        /* Decode bits from huf file */
        for (int i = 0; i < size; i += 1) {
            Match match = trie.longestPrefixMatch(bits);
            ori[i] = match.getSymbol();
            bits = bits.allButFirstNBits(match.getSequence().length());
        }
        FileUtils.writeCharArray(args[1], ori);
    }
}
