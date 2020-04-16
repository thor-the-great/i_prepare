package path.linkedin;

public class PalindromicSubstrings {

    /**
     * For each position in the string make it as a possible center of the palindrome and
     * go to left and right from center.
     * O(n^2) time
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        if (s == null || s.isEmpty())
            return 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += getPalindromicCount(s, i, i);
            if (i + 1 < s.length()) {
                res+= getPalindromicCount(s, i, i + 1);
            }
        }

        return res;
    }

    int getPalindromicCount(String s, int rc, int lc) {
        int res = 0;
        while (rc >= 0 && lc < s.length()) {
            if (s.charAt(rc) == s.charAt(lc)) {
                ++res;
                --rc; ++lc;
            }
            else {
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        PalindromicSubstrings obj = new PalindromicSubstrings();
        //System.out.println(obj.countSubstrings("aaab"));

        System.out.println(obj.countSubstrings("abc"));

        System.out.println(obj.countSubstrings("aaa"));
    }
}
