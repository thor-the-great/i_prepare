package path.top_asked;

import java.util.*;

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
        //two pointers technique
        List<List<Integer>> res = new ArrayList();
        Arrays.sort(nums);

        for (int f = 0; f < nums.length - 2; f++) {
            if (f == 0 || (f > 0 && nums[f] != nums[f - 1])) {
                int comp = 0 - nums[f];
                int l = f + 1, r = nums.length - 1;
                while (l < r) {
                    if (nums[l] + nums[r] == comp) {
                        List<Integer> list = new ArrayList();
                        list.add(nums[f]);
                        list.add(nums[l]);
                        list.add(nums[r]);
                        res.add(list);
                        while (l < r && nums[l] == nums[l + 1])
                            ++l;
                        while (l < r && nums[r] == nums[r - 1])
                            --r;
                        ++l;
                        --r;
                    } else if (nums[l] + nums[r] < comp) {
                        ++l;
                    } else
                        --r;
                }
            }
        }

        return res;
    }

    public List<List<Integer>> threeSumMapOfCompl(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        Arrays.sort(nums);
        Map<Integer, Integer> m = new HashMap();


        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int target = 0 - nums[i];
                m.clear();
                for (int j = i + 1; j < nums.length; j++) {
                    int comp = target - nums[j];
                    if (m.containsKey(nums[j])) {
                        if (m.get(nums[j]) != Integer.MAX_VALUE) {
                            List<Integer> l = new LinkedList();
                            l.add(nums[i]); l.add(nums[j]); l.add(nums[m.get(nums[j])]);
                            res.add(l);
                            m.put(nums[j], Integer.MAX_VALUE);
                        }
                    }
                    else {
                        m.put(comp, j);
                    }
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        ThreeSum obj = new ThreeSum();
        //List<List<Integer>> res = obj.threeSum(new int[] {-1,0,1,2,-1,-4});
        List<List<Integer>> res = obj.threeSum(new int[] {0,0,0,0});
        for(List<Integer> oneList : res) {
            oneList.forEach(n -> System.out.print(n + " "));
            System.out.print("\n");
        }


    }
}
