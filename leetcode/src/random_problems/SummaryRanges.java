package random_problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 228. Summary Ranges
 * Medium
 *
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 *
 * Example 1:
 *
 * Input:  [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
 * Example 2:
 *
 * Input:  [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
 */
public class SummaryRanges {

    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList();
        int N = nums.length;
        if (N == 0)
            return res;

        int prevIdx = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                addToResult(res, sb, nums, prevIdx, i - 1);
                prevIdx = i;
            }
        }
        addToResult(res, sb, nums, prevIdx, N - 1);
        return res;
    }

    void addToResult(List<String> res, StringBuilder sb, int[] nums, int prevIdx, int lastIdx) {
        sb.setLength(0);
        sb.append(nums[prevIdx]);
        if (prevIdx < lastIdx) {
            sb.append("->").append(nums[lastIdx]);
        }
        res.add(sb.toString());
    }
}
