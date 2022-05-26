package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] utility = new int[M];
        for (Oomage o : oomages) {
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            utility[bucketNum] += 1;
        }

        for (int i : utility) {
            if (i < oomages.size() / 50 || i > oomages.size() / 2.5) {
                return false;
            }
        }
        return true;
    }
}
