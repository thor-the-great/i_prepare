/**
 * Even Split New
 *     Recursion Arrays Numbers
 * Given an array of integers, determine if it is possible to split
 * the array into two parts such that the sum of all elements in each part is the same.
 *
 * Examples:
 *
 * splitArray({1,2,3,4}) ==> true
 * splitArray({1,2,4}) ==> false
 */
public class SplitArrayEqualSum {

    /**
     * Calculate the overall sum. Target will be sum / 2. Do the recursive calls and on each step either include or
     * skip current element. Check if we have reached the target.
     * O(2^n) time
     * @param arr
     * @return
     */
    public static boolean splitArray(int[] arr) {
        if (arr == null || arr.length < 2)
            return false;

        int sum = 0;
        for (int n : arr)
            sum += n;

        if (sum % 2 != 0)
            return false;

        return helper(arr, 0, 0, sum/2);
    }

    static boolean helper(int[] arr, int idx, int cur, int target) {
        if (idx >= arr.length) {
            return false;
        }

        if (cur == target || cur + arr[idx] == target)
            return true;

        return helper(arr, idx + 1, cur, target) || helper(arr, idx + 1, cur + arr[idx], target);
    }

    /**
     * Similar to knapsack problem, 0/1 Knapsack pattern
     * @param arr
     * @return
     */
    public static boolean splitArrayDP(int[] arr) {
        if (arr == null || arr.length < 2)
            return false;
        int sum = 0;
        for (int n : arr)
            sum += n;

        if (sum % 2 == 1)
            return false;

        sum /= 2;

        int N = arr.length;
        boolean[][] dp = new boolean[N][1 + sum];

        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }

        dp[0][arr[0]] = true;

        for (int n = 1; n < N; n++) {
            for (int s = 1; s <= sum; s++) {
                if (dp[n - 1][s])
                    dp[n][s] = dp[n - 1][s];
                else {
                    if (s >= arr[n]) {
                        dp[n][s] = dp[n - 1][s - arr[n]];
                    }
                }
            }
        }

        return dp[N - 1][sum];
    }
}
