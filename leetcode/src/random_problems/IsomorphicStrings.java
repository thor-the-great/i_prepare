package random_problems;

/**
 * 205. Isomorphic Strings
 * Easy
 *
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character but a character may map to itself.
 *
 * Example 1:
 *
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 *
 * Input: s = "paper", t = "title"
 * Output: true
 * Note:
 * You may assume both s and t have the same length.
 */
public class IsomorphicStrings {

    /**
     * Idea: Check mapping - chars at same position must map to the same position char from another string. Count of
     * mappings must be the same for both strings
     * catch - do the +1, this allows us to avoid Arrays.fill(-1) time because index starts from 0, so our first
     * index will be 1
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        int[] m1 = new int[128];
        int[] m2 = new int[128];
        for (int i = 0; i < s.length(); i++) {
            int ch1 = s.charAt(i);
            int ch2 = t.charAt(i);
            if (m1[ch1] != m2[ch2]) {
                return false;
            } else {
                m1[ch1] = i + 1;
                m2[ch2] = i + 1;
            }
        }
        return true;
    }

    public boolean isIsomorphicBasedOnMaps(String s, String t) {
        Map<Character, Integer> map1 = new HashMap(), map2 = new HashMap();
        
        int cur1 = 0, cur2 = 0;
        for (int i = 0; i < s.length(); i++) {
            char sCh = s.charAt(i), tCh = t.charAt(i);
            if (!map1.containsKey(sCh)) {
                map1.put(sCh, cur1);
                cur1++;
            }
            if (!map2.containsKey(tCh)) {
                map2.put(tCh, cur2);
                cur2++;
            }
            if (map1.get(sCh) != map2.get(tCh)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IsomorphicStrings obj = new IsomorphicStrings();
        System.out.println(obj.isIsomorphic("ab", "aa"));
    }
}
