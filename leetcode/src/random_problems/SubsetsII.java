package random_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. Subsets II
 * Medium
 *
 *
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: [1,2,2]
 * Output:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 */
public class SubsetsII {

    List<List<Integer>> res = new ArrayList();
    int[] arr;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        res.clear();
        arr = nums;
        if (nums.length == 0)
            return res;
        Arrays.sort(arr);
        helper(new ArrayList(), 0);
        return res;
    }

    void helper(List<Integer> l, int startIndex) {
        List<Integer> retList = new ArrayList(l);
        res.add(retList);
        for (int i = startIndex; i < arr.length; i++) {
            if (i == startIndex || arr[i] != arr[i - 1] ) {
                l.add(arr[i]);
                helper(l, i + 1);
                l.remove(l.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        SubsetsII obj = new SubsetsII();
        List<List<Integer>> res = obj.subsetsWithDup(new int[] {1, 2, 2});
        for (List<Integer> l : res) {
            l.forEach(i->System.out.print(i + " "));
            System.out.print("\n");
        }
    }
}
