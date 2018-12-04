package path.linkedin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. Permutations II
 * Medium
 *
 *
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 * Example:
 *
 * Input: [1,1,2]
 * Output:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 */
public class PermutationsII {

    int[] nums;
    List<List<Integer>> res;
    boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList();
        if (nums.length == 0)
            return res;
        Arrays.sort(nums);
        this.nums = nums;
        used = new boolean[nums.length];
        helper(new ArrayList<Integer>());
        return res;
    }

    void helper(List<Integer> list) {
        if (list.size() == nums.length) {
            List<Integer> l = new ArrayList(list);
            res.add(l);
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1])
                continue;
            used[i] = true;
            list.add(nums[i]);
            helper(list);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        PermutationsII obj = new PermutationsII();
        List<List<Integer>> perms = obj.permuteUnique(new int[] {1, 1, 2});

        for (List<Integer> one : perms) {
            System.out.print("[ ");
            one.forEach(i->System.out.print(i + " "));
            System.out.print("] \n");
        }
    }
}
