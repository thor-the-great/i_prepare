package random_problems;

/**
 * 416. Partition Equal Subset Sum
 * Medium
 *
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets
 * such that the sum of elements in both subsets is equal.
 *
 * Note:
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 * Example 1:
 *
 * Input: [1, 5, 11, 5]
 *
 * Output: true
 *
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * Example 2:
 *
 * Input: [1, 2, 3, 5]
 *
 * Output: false
 *
 * Explanation: The array cannot be partitioned into equal sum subsets.
 */
public class PartitionEqualSubsetSum {

    /**
     * Idea - similar to knapsack problem. Count what is sum/2, then build array of booleans for that capacity. Then
     * start checking array elements one by one (backwards) and if current number is equals or lower than capacity -
     * mark this position and other possible ones as taken
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int N = nums.length;
        if (N == 0)
            return false;

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 == 1)
            return false;
        sum /= 2;

        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int i = 0; i < N; i++) {
            for (int j = sum; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
            if (dp[sum])
                return true;
        }

        return dp[sum];
    }

    public static void main (String[] args) {
        PartitionEqualSubsetSum obj = new PartitionEqualSubsetSum();
        int[] arr;

        arr = new int[] {
                1, 3, 11, 5, 6, 4
        };
        System.out.println(obj.canPartition(arr));

        arr = new int[] {
                1, 2, 5
        };
        System.out.println(obj.canPartition(arr));

        arr = new int[] {
                66,90,7,6,32,16,2,78,69,88,85,26,3,9,58,65,30,96,11,31,99,49,63,83,79,97,20,64,81,80,25,69,9,75,23,70,
                26,71,25,54,1,40,41,82,32,10,26,33,50,71,5,91,59,96,9,15,46,70,26,32,49,35,80,21,34,95,51,66,17,71,28,
                88,46,21,31,71,42,2,98,96,40,65,92,43,68,14,98,38,13,77,14,13,60,79,52,46,9,13,25,8
        };
        System.out.println(obj.canPartition(arr));
    }
}
