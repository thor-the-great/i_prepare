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
}
