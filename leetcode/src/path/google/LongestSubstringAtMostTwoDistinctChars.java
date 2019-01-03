package path.google;

/**
 * 159. Longest Substring with At Most Two Distinct Characters
 * Hard
 *
 * Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.
 *
 * Example 1:
 *
 * Input: "eceba"
 * Output: 3
 * Explanation: t is "ece" which its length is 3.
 * Example 2:
 *
 * Input: "ccaabbb"
 * Output: 5
 * Explanation: t is "aabbb" which its length is 5.
 *
 */
public class LongestSubstringAtMostTwoDistinctChars {
    /**
     * Idea - using sliding window, two pointers. Move left pointer forward and keep count of number of occurrences
     * for each character and number of unique characters. If Number of characters became 3 - start moving right pointer
     * and decrement counter unless we have only 2 unique characters again
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int N = s.length();
        if (N <= 2)
            return N;
        //keep counts of each character
        int[] m = new int[128];
        int size = 0;
        int l = 0;
        //fill initial array to start
        while (l < N) {
            char ch = s.charAt(l);
            if (m[ch] == 0 && size == 2)
                break;
            if (m[ch] == 0)
                size++;
            m[ch]++;
            l++;
        }
        //edge case - if we exited due to index overflow and not because we found 3 distinct characters
        if (l == N)
            return N;
        //this is right pointer in a sliding window
        int r = 0;
        //max length of sequence so far
        int max = l;
        while (l < N) {
            char next = s.charAt(l);
            //if we found 3-rd distinct character
            if (m[next] == 0 && size == 2) {
                max = Math.max(max, l - r);
                //move right pointer forward
                while (size == 2) {
                    char rem = s.charAt(r);
                    int newCount = m[rem] - 1;
                    if (newCount == 0)
                        size--;
                    m[rem]--;
                    r++;
                }
            }
            if (m[next] == 0)
                size++;
            m[next]++;
            l++;
        }
        //special case for the last iteration in loop - it's not caught inside the loop
        if (size == 2) {
            max = Math.max(max, l - r);
        }
        return max;
    }

    public static void main (String[] args) {
        LongestSubstringAtMostTwoDistinctChars obj = new LongestSubstringAtMostTwoDistinctChars();
        System.out.println(obj.lengthOfLongestSubstringTwoDistinct("ccaa"));
        System.out.println(obj.lengthOfLongestSubstringTwoDistinct("ccaab"));
        System.out.println(obj.lengthOfLongestSubstringTwoDistinct("ccaabbb"));
        System.out.println(obj.lengthOfLongestSubstringTwoDistinct("accaaabaacbvb"));
    }
}
