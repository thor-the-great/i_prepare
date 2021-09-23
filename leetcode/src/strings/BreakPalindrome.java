package strings;

/**
 * 1328. Break a Palindrome
 * Medium
 *
 * Given a palindromic string palindrome, replace exactly one character by any lowercase English letter so that the
 * string becomes the lexicographically smallest possible string that isn't a palindrome.
 *
 * After doing so, return the final string.  If there is no way to do so, return the empty string.
 *
 *
 *
 * Example 1:
 *
 * Input: palindrome = "abccba"
 * Output: "aaccba"
 * Example 2:
 *
 * Input: palindrome = "a"
 * Output: ""
 *
 *
 * Constraints:
 *
 * 1 <= palindrome.length <= 1000
 * palindrome consists of only lowercase English letters.
 */
public class BreakPalindrome {

    /**
     * Ther are following cases possible:
     * there on only one char in string, in this case ther eis no anser cause any of such string after replacement
     * will be a palindrome anyway
     * string has multiple unique characters - in this case we just replace first occurance of char with 'a' if it's
     * not 'a' already
     * string has all same chars and it's not 'a' - in this case replace first char with 'a'
     * string contains only 'a' - in this case we need to repalce the last char with next char wich is 'b'
     *
     * another catch is - to check different chars we don't have to go over every chars in string. Because it's a
     * palindrome we can just check up to the middle index (excluded).
     *
     * O(n) time - iterate over string once, O(n) space - need to allocate new char array (stringbuider) to replace a
     * char and generate a string out of it
     * @param palindrome
     * @return
     */
    public String breakPalindrome(String palindrome) {
        int N = palindrome.length();
        if (N == 1) {
            return "";
        }
        
        char[] strAsArray = palindrome.toCharArray();
        for (int i = 0; i < N / 2; i++) {
            if (strAsArray[i] != 'a') {
                strAsArray[i] = 'a';
                return new String(strAsArray);
            }
        }
        
        strAsArray[N - 1] = 'b';
        return new String(strAsArray);
    }
}
