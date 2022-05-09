public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> ls = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i += 1) {
            ls.addLast(word.charAt(i));
        }
        return ls;
    }

    private boolean helper(Deque ls) {
        if (ls.size() == 0 || ls.size() == 1) {
            return true;
        } else if (ls.removeFirst().equals(ls.removeLast()) && helper(ls)) {
            return true;
        }
        return false;


    }

    public boolean isPalindrome(String word) {
        return helper(wordToDeque(word));
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int j = word.length() - 1;
        for (int i = 0; i < word.length(); i++, j--) {
            if (i == j) {
                continue;
            }
            if (!cc.equalChars(word.charAt(i), word.charAt(j))) {
                return false;
            }
        }
        return true;
    }
}
