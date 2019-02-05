package random_problems;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 220. Contains Duplicate III
 * Medium
 *
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the
 * absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1], k = 3, t = 0
 * Output: true
 * Example 2:
 *
 * Input: nums = [1,0,1,1], k = 1, t = 2
 * Output: true
 * Example 3:
 *
 * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
 * Output: false
 *
 */
public class ContainsDuplicateIII {

    long w;

    /**
     * Idea - use baskets to sort elements, each basket is of size t. When searching check current, -1 and + 1 baskets
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0)
            return false;
        int N = nums.length;
        if (N == 0)
            return false;

        Map<Long, Long> b = new HashMap();

        w = (long) t + 1;

        for (int i = 0; i < N; i++) {
            int el = nums[i];
            long grp = getId(el);
            if (b.containsKey(grp))
                return true;

            if (b.containsKey(grp - 1) && Math.abs(el - b.get(grp - 1)) < w)
                return true;

            if (b.containsKey(grp + 1) && Math.abs(el - b.get(grp + 1)) < w)
                return true;

            b.put(grp, (long) el);

            if (b.size() > k)
                b.remove(getId(nums[i - k]));
        }

        return false;
    }

    private long getId(long x) {
        if (x < 0)
            return ((x + 1)/w) - 1;
        else
            return x / w;
    }


    public boolean containsNearbyAlmostDuplicateSelftBalancedBST(int[] nums, int k, int t) {
        int N = nums.length;
        if (N == 0)
            return false;

        TreeSet<Integer> bst = new TreeSet();
        for (int i = 0; i < N; i++) {
            int el = nums[i];
            Integer n = bst.ceiling(el);
            if (n != null && n <= el + t)
                return true;

            n = bst.floor(el);
            if (n != null && n +t >= el)
                return true;

            bst.add(el);
            if (bst.size() > k)
                bst.remove(nums[i - k]);
        }

        return false;
    }
}
