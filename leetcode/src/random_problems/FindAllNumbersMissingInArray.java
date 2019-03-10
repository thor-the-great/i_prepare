package random_problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 448. Find All Numbers Disappeared in an Array
 * Easy
 *
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear
 * once.
 *
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 *
 * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra
 * space.
 *
 * Example:
 *
 * Input:
 * [4,3,2,7,8,2,3,1]
 *
 * Output:
 * [5,6]
 *
 */
public class FindAllNumbersMissingInArray {

    /**
     * Idea: mark every element as index by negating it if value is >= 0. Those that are at indexes that are missing
     * we'll be positive so we can collect it to the result list
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList();
        int N = nums.length;

        for (int i = 0; i < N; i++) {
            int idx = nums[i];
            idx = idx < 0 ? (-idx) : idx;
            idx--;
            if (nums[idx] >= 0 )
                nums[idx] = - nums[idx];
        }

        for (int i = 0; i < N; i++) {
            if (nums[i] > 0)
                res.add(i + 1);
        }

        return res;
    }
}
