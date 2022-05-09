public class OffByN implements CharacterComparator{

    private int diff;

    public OffByN(int n) {
        this.diff = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (x - y == diff || y - x == diff) {
            return true;
        }
        return false;
    }
}