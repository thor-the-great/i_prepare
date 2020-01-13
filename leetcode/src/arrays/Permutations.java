package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 *
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class Permutations {

    /**
     * Idea is - divide into prefix, current and suffix. Starting from current swap every element from suffix
     * with current, then increment current and call next recursion, then revert swapped elements. Stop when
     * current reached end of the array
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        helper(nums, 0, res);
        return res;
    }

    void helper(int[] arr, int pointer, List<List<Integer>> res) {
        if (pointer >= arr.length) {
            List<Integer> list = new ArrayList();
            for (int n : arr)
                list.add(n);
            res.add(list);
            return;
        }

        for (int i = pointer; i < arr.length; i++) {
            swap(pointer, i, arr);
            helper(arr, pointer + 1, res);
            swap(pointer, i, arr);
        }
    }

    void swap(int i, int j, int[] arr) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
