package path.google;

/**
 * Valid Palindrome
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 *
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 *
 * Example 1:
 *
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * Example 2:
 *
 * Input: "race a car"
 * Output: false
 *
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetter(ch) || Character.isDigit(ch)) {
                sb.append(Character.toLowerCase(ch));
            }
        }
        int N = sb.length();
        if (N < 2) return true;
        int r = N/2, l;
        if (N % 2 == 1)
            l = r;
        else
            l = r - 1;
        while (l >= 0) {
            if (sb.charAt(l) != sb.charAt(r))
                return false;
            l--;
            r++;
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
