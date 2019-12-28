package intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * 163. Missing Ranges
 * Medium
 *
 * Share
 * Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return
 * its missing ranges.
 *
 * Example:
 *
 * Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
 * Output: ["2", "4->49", "51->74", "76->99"]
 */
public class MissingRanges {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList();
        int N = nums.length;
        if (N == 0) {
            res.add(getRangeStr(lower, upper));
            return res;
        }
        //check if we have [lower, nums[0]] missing range
        if (nums[0] > lower) {
            res.add(getRangeStr(lower, nums[0] - 1));
        }
        //keep the current left edge of the interval we checked so far
        long left = nums[0];
        for (int i = 0; i < N; i++) {
            //if we checked everything between lower..upper there is no sense to iterate farther
            if (left > upper)
                break;
            //if there is at least one number missing between last checked and nums[i] we need to add it to result
            if ((long)  nums[i] - left > 1) {
                res.add(getRangeStr((int)left + 1, nums[i] - 1));
            }
            left = nums[i];
        }

        //check if we have [nums[N - 1] to upper] missing range
        if (upper > nums[N - 1])
            res.add(getRangeStr(nums[N - 1] + 1, upper));

        return res;
    }

    String getRangeStr(int left, int right) {
        //if there is only one number missing - return just it
        if (left == right)
            return Integer.toString(left);
        //if there are multiple numbers missing - form correct string
        StringBuilder sb = new StringBuilder();
        sb.append(left).append("->").append(right);
        return sb.toString();
    }
}
