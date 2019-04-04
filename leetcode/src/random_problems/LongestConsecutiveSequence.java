package random_problems;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. Longest Consecutive Sequence
 * Hard
 *
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *
 * Your algorithm should run in O(n) complexity.
 *
 * Example:
 *
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet();
        for(int n : nums)
            set.add(n);

        int max = 0;
        int curr = 0;
        for(int n : nums) {
            if (!set.contains(n - 1)) {
                curr = 1;
                while(set.contains(curr + n)) {
                    curr++;
                }
                max = Math.max(max, curr);
            }
        }

        return max;
    }
}
