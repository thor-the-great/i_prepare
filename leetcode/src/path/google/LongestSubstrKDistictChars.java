package path.google;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstrKDistictChars {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s.length() == 0 ) return 0;
        int[] counts = new int[128];
        int c = 0, j= 0, res = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (counts[ch] == 0) {
                counts[ch] = 1;
                c++;
                while (c > k) {
                    char ch2 = s.charAt(j);
                    int newCount = counts[ch2] - 1;
                    counts[ch2] = newCount;
                    if (newCount == 0)
                        c--;
                    j++;
                }
            } else {
                counts[ch] += 1;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    public int lengthOfLongestSubstringKDistinctMap(String s, int k) {
        if (s.length() == 0 ) return 0;
        Map<Character, Integer> map = new HashMap();
        int l = 0, j= 0, res = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!map.containsKey(ch)) {
                map.put(ch, 1);
                while (map.size() > k) {
                    char ch2 = s.charAt(j);
                    int newCount = map.get(ch2) - 1;
                    if (newCount == 0)
                        map.remove(ch2);
                    else
                        map.put(ch2, newCount);
                    j++;
                }
            }
            else {
                map.put(ch, map.get(ch) + 1);
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        LongestSubstrKDistictChars obj = new LongestSubstrKDistictChars();
        System.out.println(obj.lengthOfLongestSubstringKDistinctMap("eceba", 2));
        System.out.println(obj.lengthOfLongestSubstringKDistinctMap("ababffzzeee", 2));
    }
}
