import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author q
 * @create 2020-08-27 0:19
 */
public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> temp = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++){
            temp.addLast(word.charAt(i));
        }
        return temp;
    }

    // the first and last is same?
    private boolean isSame(Character a, Character b){
        return a == b;
    }

    public boolean isPalindrome(Deque<Character> word){
        if (word.size() <= 1) return true;
        Character a = word.removeFirst();
        Character b = word.removeLast();
        return isSame(a,b) && isPalindrome(word);
    }

    //is Palindrome off by one
    public boolean isPalindrome(Deque<Character> word, CharacterComparator cc){
        if (word.size() <= 1) return true;
        Character a = word.removeFirst();
        Character b = word.removeLast();
        return cc.equalChars(a,b) && isPalindrome(word,cc);
    }


    @Test
    public void testSt(){
        String a = "abcba";
        Deque aa = wordToDeque(a);
        System.out.println(isPalindrome(aa));
    }
}
