package path.top_asked;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 4Sum
 * Medium
 *
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that
 * a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 *
 * Note:
 *
 * The solution set must not contain duplicate quadruplets.
 *
 * Example:
 *
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 *
 * A solution set is:
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 */
public class FourSum {

    List<List<Integer>> res;
    int[] arr;

    /**
     * Idea: recursively solve 2sum, then 3sum and 4sum
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        res = new ArrayList();
        int N = nums.length;
        if (N < 4)
            return res;

        arr = nums;
        Arrays.sort(nums);

        int max = nums[N - 1];
        if (max * 4 < target)
            return res;

        for (int i = 0; i < N - 3; i++) {
            int el = nums[i];

            if (i > 0 && nums[i - 1] == nums[i]) continue;
            if (4 * el > target) break;
            if (4 * el == target) {
                if (i + 3 < N && nums[i + 3] == el)
                    res.add(Arrays.asList(el, el, el, el));
                break;
            }

            //call to 3 sum
            threeSum(i, target - el);
        }
        return res;
    }

    void threeSum(int idx, int target) {
        int N = arr.length;

        for (int i = idx + 1; i < N - 2; i++) {
            int el = arr[i];

            if (i > idx + 1 && arr[i - 1] == arr[i]) continue;
            if (3 * el > target) break;
            if (3 * el == target) {
                if (i + 2 < N && arr[i + 2] == el)
                    res.add(Arrays.asList(arr[idx], el, el, el));
                break;
            }

            //call to 2 sum
            twoSum(idx, i, target - el);
        }
    }

    void twoSum(int idx, int idx2, int target) {
        if (2 * arr[idx2 + 1] > target)
            return;
        int N = arr.length;

        int l = idx2 + 1;
        int r = N - 1;
        int s;
        while (l < r) {
            s = arr[l] + arr[r];
            if (s == target) {
                res.add(Arrays.asList(arr[idx], arr[idx2], arr[l], arr[r]));
                while (l < r && arr[l + 1] == arr[l]) l++;
                while (l < r && arr[r - 1] == arr[r]) r--;
                l++;
                r--;
            } else if (s > target) {
                r--;
            } else
                l++;
        }
    }

    public static void main(String[] args) {
        FourSum obj = new FourSum();
        List<List<Integer>> res = obj.fourSum(new int[] {
                -1,0,1,2,-1,-4
        }, -1); //[[-4,0,1,2],[-1,-1,0,1]]

        for (List<Integer> row: res ) {
            System.out.print("[ ");
            row.forEach(i->System.out.print(i + ", "));
            System.out.print(" ]\n");
        }
    }
}
