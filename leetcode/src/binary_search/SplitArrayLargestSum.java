package binary_search;

/**
 * 410. Split Array Largest Sum
 * Hard
 *
 * Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty
 * continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.
 *
 * Note:
 * If n is the length of array, assume the following constraints are satisfied:
 *
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * Examples:
 *
 * Input:
 * nums = [7,2,5,10,8]
 * m = 2
 *
 * Output:
 * 18
 *
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 */
public class SplitArrayLargestSum {

    /**
     * Do binary search - get max of all elements and the sum of all elements. Then start binary search between those
     * two, splitting array every time and check how many parts <= than mid we can have
     * @param nums
     * @param m
     * @return
     */
    public int splitArray(int[] nums, int m) {
        long sum = 0, max = 0;
        for (int n : nums) {
            sum += n;
            max = Math.max(max, n);
        }
        long l = max, r = sum;
        while (l <= r) {
            long mid = (l + r)/2;
            long c = 0, t = 1;
            for (int n : nums) {
                c+=n;
                if (c > mid) {
                    ++t;
                    if (t > m)
                        break;
                    c = n;
                }
            }
            if (t > m)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return (int)l;
    }

    public int splitArrayDP(int[] nums, int m) {
        int N = nums.length;
        int[][] dp = new int[N + 1][m + 1];
        int[] sum = new int[N + 1];
        //init dp array with infinity value - initially we optimize against max value
        for (int r = 0; r <=N; r++) {
            for (int c = 0; c <= m; c++) {
                dp[r][c] = Integer.MAX_VALUE;
            }
        }
        //this is prefix sum array for array elements
        for (int i = 1; i <=N; i++)
            sum[i] = sum[i - 1] + nums[i - 1];

        dp[0][0] = 0;

        for (int i = 1; i<=N; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j],
                            Math.max(dp[k][j - 1], sum[i] - sum[k]));
                }
            }
        }
        return dp[N][m];
    }
}
