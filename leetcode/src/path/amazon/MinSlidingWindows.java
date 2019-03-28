package path.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. Minimum Window Substring
 * Hard
 *
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in
 * complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinSlidingWindows {

    /**
     * Idea: using sliding window. Count num of every character that is in t, number of such unique characters is
     * stored as charNum. Then extending sliding window unless charNum reached. Then move left pointer unless
     * we reach charNum - 1
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        int[] windowCount = new int[128];
        int[] tCount = new int[128];
        int reqNum = 0;

        for (char ch : t.toCharArray()) {
            if (tCount[ch] == 0)
                reqNum++;
            tCount[ch]++;
        }

        int[] res = new int[] {-1, -1, Integer.MAX_VALUE};
        int p = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i);
            if (tCount[idx] > 0 ) {
                windowCount[idx]++;

                if (windowCount[idx] == tCount[idx])
                    count++;

                if (count == reqNum) {
                    while (count == reqNum && p <= i) {
                        int len = i - p + 1;
                        if (res[2] > len) {
                            res[0] = p;
                            res[1] = i;
                            res[2] = len;
                        }
                        char pChar = s.charAt(p);
                        if (tCount[pChar] > 0) {
                            windowCount[pChar]--;
                            if (windowCount[pChar] < tCount[pChar])
                                count--;
                        }
                        p++;
                    }
                }
            }
        }

        if (res[2] != Integer.MAX_VALUE)
            return s.substring(res[0], res[1] + 1);
        return "";
    }

    public String minWindowMap(String s, String t) {
        Map<Character, Integer> windowCount = new HashMap();
        Map<Character, Integer> tCount = new HashMap();
        for(char c : t.toCharArray()){
            if(tCount.containsKey(c)){
                tCount.put(c,tCount.get(c)+1);
            }else{
                tCount.put(c,1);
            }
        }

        int reqNum = tCount.size();
        int foundCount = 0;
        int l = 0, r = 0;
        int[] res = new int[] {-1, 0, 0};
        while (r < s.length()) {
            char nextChar = s.charAt(r);
            windowCount.put(nextChar, windowCount.getOrDefault(nextChar, 0) + 1);
            if (tCount.containsKey(nextChar) && tCount.get(nextChar) == windowCount.get(nextChar)) {
                foundCount++;
            }
            while (l <= r && foundCount == reqNum) {
                char leftChar = s.charAt(l);
                if (res[0] == -1 || r - l + 1 < res[0]) {
                    res[0] = r - l + 1;
                    res[1] = l;
                    res[2] = r;
                }
                windowCount.put(leftChar, windowCount.get(leftChar) - 1);
                if (tCount.containsKey(leftChar) && windowCount.get(leftChar) < tCount.get(leftChar)) {
                    foundCount--;
                }
                l++;
            }
            r++;
        }
        if (res[0] == -1) return "";
        else return s.substring(res[1], res[2] + 1);
    }

    public static void main(String[] args) {
        MinSlidingWindows obj = new MinSlidingWindows();
        System.out.println(obj.minWindow("ADOBECODEBANC", "ABC")); //"BANC"
    }
}
