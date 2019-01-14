package random_problems;
/**
 * 16. 3Sum Closest
 * Medium
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest
 * to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 *
 * Example:
 *
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 *
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest {
    /**
     * Idea - sort, then start something similar to binary search - moving 2 pointers and checking for optimial
     * solution.
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int N = nums.length;
        int res = nums[0] + nums[1] + nums[N - 1];
        int dif = Math.abs(target - res);

        for (int i = 0; i < N - 2; i++) {
            int runningRes = nums[i];
            int l = i + 1;
            int r = N - 1;
            while (l < r) {
                int s = runningRes + nums[l] + nums[r];
                if (s > target)
                    r--;
                else
                    l++;
                int dif2 = Math.abs(s - target);
                if (dif2 < dif) {
                    dif = dif2;
                    res = s;
                }
                else if (dif2 == 0)
                    return s;
            }
        }
        return res;
    }
}
