package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 1239. Maximum Length of a Concatenated String with Unique Characters
Medium

Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.

Return the maximum possible length of s.

Example 1:

Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
Maximum length is 4.
Example 2:

Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible solutions are "chaers" and "acters".
Example 3:

Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26
 
Constraints:

1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lower case English letters.

https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
 */
public class MaximumLengthofConcatenatedStringwithUniqueCharacters {

    /**
     * for each string:
     * - check if it does't have duplicates inside it, if yes - discard it
     * - calculate mask where each present char is a 1 bit. Concatenate it with each other such maks that we have, if
     * such mask exists - discard it, if no - add both masks to get summary of chars in both segments. Keep rolling max of bits set in
     * one mask
     */
    public int maxLength(List<String> arr) {
        int max = 0;
        List<Integer> dp = new ArrayList();
        dp.add(0);
        for (String word : arr) {
            int duplMask = 0;
            boolean foundDupl = false;
            for (char ch : word.toCharArray()) {
                int tmp = 1 << (ch - 'a');
                if ((duplMask&tmp) > 0) {
                    foundDupl = true;
                    break;
                }
                duplMask|=tmp;
            }
            if (foundDupl) {
                continue;
            }
            
            for (int i = dp.size() - 1; i >= 0; i--) {
                int mask = dp.get(i);
                if ((mask&duplMask) > 0) {
                    continue;
                }
                int newMask = dp.get(i) | duplMask;
                dp.add(newMask);
                max = Math.max(max, Integer.bitCount(newMask));
            }
        }
        return max;
    }
}
