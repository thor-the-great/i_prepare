package arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * 659. Split Array into Consecutive Subsequences
 * Medium
 *
 * Given an array nums sorted in ascending order, return true if and only if you can split it into 1 or more
 * subsequences such that each subsequence consists of consecutive integers and has length at least 3.
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,3,3,4,5]
 * Output: True
 * Explanation:
 * You can split them into two consecutive subsequences :
 * 1, 2, 3
 * 3, 4, 5
 *
 * Example 2:
 *
 * Input: [1,2,3,3,4,4,5,5]
 * Output: True
 * Explanation:
 * You can split them into two consecutive subsequences :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 *
 * Example 3:
 *
 * Input: [1,2,3,4,4,5]
 * Output: False
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10000
 */
public class SplitArrayIntoConsecutiveSubsequences {

    /**
     * Any element can be added to some sequence in one of following ways:
     * - it starts the seq
     * - it in the meiddle
     * - it ends the seq
     *
     * count frequency of every element (by value). Create empty map of sequences where num is key and value is number
     * of sequences.
     * For each element on a second loop we do following:
     * - if we have used all this element freq - continue, probably it's used in one of sequences
     * - if this element has unused freq and it's part of any sequence - decrement freq by 1, start new seq
     * for n + 1 - decrement seq for n and increment for n + 1.
     * - if we can start new seq here (freq(n + 1) > 0 and freq(n + 2) > 0) - start it
     * decrement freq by 1 for n, n + 1 and n + 2. then start new seq for n + 3.
     * - if all above false - return immediately
     *
     * brilliant idea!
     * Took me a while to understand "appendfreq"
     * Here is how I see "appendfreq"
     *
     * eg: [1,2,3,4, 5]
     * // i =1
     * we fall in 3 case "start of a new subsequence"
     * we make 2, 3 freq 0
     * and put <4, 1> in appendfreq, this mean I have 1 subsequence can continue from 4
     *
     * //i =2, 3
     * we continue
     *
     * //i = 4
     * we fall in 2 case since <4, 1> is in appendfreq
     * now this subsequence should end in 5
     * so we decreace <4, 1> to <4, 0> since we no longer have subsequence can continue from 4
     * and we put <5, 1> in appendfreq since now we have a subsequence can continue from 5
     *
     * Hope this help
     * @param nums
     * @return
     */
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap(), seq = new HashMap();

        for (int n : nums)
            freq.put(n, freq.getOrDefault(n, 0) + 1);

        for (int n : nums) {
            //if the ele is already used in some sequence
            if (freq.get(n) == 0)
                continue;
            //if the ele can be added in the last consecutive sequence
            if (seq.getOrDefault(n, 0) > 0) {
                freq.put(n, freq.get(n) - 1);
                seq.put(n, seq.getOrDefault(n, 0) - 1);
                seq.put(n + 1, seq.getOrDefault(n + 1, 0) + 1);
            }
            //this ele should form a consecutive sequence by itself since it cannot be appended to a previous sequence
            else if (freq.getOrDefault(n + 1, 0) > 0 && freq.getOrDefault(n + 2, 0) > 0) {
                freq.put(n, freq.get(n) - 1);
                freq.put(n + 1, freq.get(n + 1) - 1);
                freq.put(n + 2, freq.get(n + 2) - 1);
                seq.put(n + 3, seq.getOrDefault(n + 3, 0) + 1);
            } else //doesn't belong to any consecutive sequence
                return false;
        }
        return true;
    }
}
