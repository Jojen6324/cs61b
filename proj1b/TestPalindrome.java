import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String w1 = "racecar";
        assertTrue(palindrome.isPalindrome(w1));

        String w2 = "a";
        assertTrue(palindrome.isPalindrome(w2));

        String w3 = "cat";
        assertFalse(palindrome.isPalindrome(w3));

        String w4 = "horse";
        assertFalse(palindrome.isPalindrome(w4));

        String w5 = "noon";
        assertTrue(palindrome.isPalindrome(w5));
    }
}
