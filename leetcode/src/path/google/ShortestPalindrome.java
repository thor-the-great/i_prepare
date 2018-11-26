package path.google;

/**
 * Shortest Palindrome
 *
 * Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it. Find
 * and return the shortest palindrome you can find by performing this transformation.
 *
 * Example 1:
 *
 * Input: "aacecaaa"
 * Output: "aaacecaaa"
 * Example 2:
 *
 * Input: "abcd"
 * Output: "dcbabcd"
 *
 */
public class ShortestPalindrome {

    public String shortestPalindrome(String s) {
        //return shortestPalindromeBF(s);
        return shortestPalindromeKMP(s);
    }

    public static void main(String[] args) {
        ShortestPalindrome obj = new ShortestPalindrome();
        System.out.println(obj.shortestPalindrome("abacd"));
    }

    public String shortestPalindromeKMP(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        String tmp = s + "#" + sb.toString();
        return new StringBuilder(s.substring(buildKMP(tmp))).reverse().append(s).toString();
    }

    private int buildKMP(String s) {
        int[] table = new int[s.length()];
        int idx = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(idx)) {
                table[i] = table[i - 1] + 1;
                idx++;
            } else {
                idx = table[i - 1];
                while(idx > 0 && s.charAt(idx) != s.charAt(i) ) {
                    idx = table[idx - 1];
                }
                if (s.charAt(i) == s.charAt(idx))
                    idx++;
                table[i] = idx;
            }
        }
        return table[table.length - 1];
    }

    public String shortestPalindromeBF(String s) {
        int N = s.length();
        char[] rev = s.toCharArray();
        reverse(rev);
        for (int i = 0; i < N; i++) {
            boolean found = true;
            for (int j = 0; j < N - i; j++) {
                if (s.charAt(j) != rev[i + j]) {
                    found = false;
                    break;
                }
            }
            if (found) {
                String pref = new String(rev, 0, i);
                return pref + s;
            }
        }
        return "";
    }

    void reverse(char[] sChar) {
        int l = 0, r = sChar.length - 1;
        char t;
        while(l < r) {
            t = sChar[l];
            sChar[l] = sChar[r];
            sChar[r] = t;
            r--;
            l++;
        }
    }
}
