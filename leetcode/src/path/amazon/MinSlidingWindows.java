package path.amazon;

import java.util.HashMap;
import java.util.Map;

public class MinSlidingWindows {

    public String minWindow(String s, String t) {
        int[] windowCount = new int[256];
        int[] tCount = new int[256];
        int reqNum = 0;
        for(char c : t.toCharArray()) {
            if (tCount[c] == 0)
                reqNum++;
            tCount[c] += 1;
        }
        int foundCount = 0;
        int l = 0, r = 0;
        int[] res = new int[] {-1, 0, 0};
        while (r < s.length()) {
            char nextChar = s.charAt(r);
            windowCount[nextChar] += 1;
            if (tCount[nextChar] > 0 && tCount[nextChar] == windowCount[nextChar]) {
                foundCount++;
            }
            while (l <= r && foundCount == reqNum) {
                char leftChar = s.charAt(l);
                if (res[0] == -1 || r - l + 1 < res[0]) {
                    res[0] = r - l + 1;
                    res[1] = l;
                    res[2] = r;
                }
                windowCount[leftChar] -= 1;
                if (tCount[leftChar] > 0 && windowCount[leftChar] < tCount[leftChar]) {
                    foundCount--;
                }
                l++;
            }
            r++;
        }
        if (res[0] == -1) return "";
        else return s.substring(res[1], res[2] + 1);
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
