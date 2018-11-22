package path.top_asked;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 3Sum
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique
 * triplets in the array which gives the sum of zero.
 *
 * Note:
 *
 * The solution set must not contain duplicate triplets.
 *
 * Example:
 *
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int sum = 0 - nums[i];
                //if (sum < 0) break;
                int left = i + 1;
                int right = nums.length - 1;
                while(left < right) {
                    if (sum == (nums[left] + nums[right])) {
                        List<Integer> l = new LinkedList();
                        l.add(nums[i]); l.add(nums[left]); l.add(nums[right]);
                        res.add(l);
                        while (left < right && nums[right] == nums[right-1])
                            right--;
                        while (left < right && nums[left] == nums[left+1])
                            left++;
                        left++;
                        right--;
                    } else if (sum > (nums[left] + nums[right])){
                        left++;
                    } else
                        right--;
                }
            }
        }

        return res;
    }
}
