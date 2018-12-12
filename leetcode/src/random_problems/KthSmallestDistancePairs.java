package random_problems;

import java.util.Arrays;

/**
 * 719. Find K-th Smallest Pair Distance
 * Hard
 *
 * Given an integer array, return the k-th smallest distance among all the pairs. The distance of a pair (A, B) is
 * defined as the absolute difference between A and B.
 *
 * Example 1:
 * Input:
 * nums = [1,3,1]
 * k = 1
 * Output: 0
 * Explanation:
 * Here are all the pairs:
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * Then the 1st smallest distance pair is (1,1), and its distance is 0.
 * Note:
 * 2 <= len(nums) <= 10000.
 * 0 <= nums[i] < 1000000.
 * 1 <= k <= len(nums) * (len(nums) - 1) / 2.
 *
 */
public class KthSmallestDistancePairs {

    /**
     * binary search based on distance plus sliding window to count number of pairs
     * - define max dif (arr[N - 1] - arr[0] after sorting) and min dif - 0. Start doing binary search and counting how
     * many elements of array make diff from 0 to this value (mid). If this number is more than k - mid is too high
     * and we make big = mid. Otherwise low = mid
     *
     * @param nums
     * @param k
     * @return
     */
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int N = nums.length;
        int biggestDif = nums[N - 1] - nums[0];
        int smallestDif = 0;
        while (smallestDif < biggestDif) {
            int mid = (smallestDif + biggestDif) / 2;
            int c = 0;
            int l = 0, r = 1;
            while (r < N) {
                while (nums[r] - nums[l] > mid)
                    l++;
                c += r - l;
                r++;
                if (c >= k) {
                    biggestDif = mid;
                    break;
                }
            }
            if (c < k)
                smallestDif = mid + 1;
        }
        return smallestDif;
    }


    public static void main(String[] args) {
        KthSmallestDistancePairs obj = new KthSmallestDistancePairs();
        int[] arr;
        arr = new int[] {1,3, 6, 4, 5, 2, 8, 11};
        System.out.println(obj.smallestDistancePair(arr, 10));
    }
}
