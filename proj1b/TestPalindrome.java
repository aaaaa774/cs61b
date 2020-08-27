
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
    public void testIsPalindrome(){
        Deque a = palindrome.wordToDeque("abcba");
        assertTrue(palindrome.isPalindrome(a));
        Deque b = palindrome.wordToDeque("abcde");
        assertFalse(palindrome.isPalindrome(b));
    }

    @Test
    public void testIsPalindrome2(){
        Deque a = palindrome.wordToDeque("flake");
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome(a, cc));
        Deque b = palindrome.wordToDeque("aaaa");
        assertFalse(palindrome.isPalindrome(b,cc));
    }
}