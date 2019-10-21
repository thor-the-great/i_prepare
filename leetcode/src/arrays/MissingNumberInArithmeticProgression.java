package arrays;

/**
 * 1228. Missing Number In Arithmetic Progression
 * User Accepted: 1637
 * User Tried: 1704
 * Total Accepted: 1681
 * Total Submissions: 3457
 * Difficulty: Easy
 * In some array arr, the values were in arithmetic progression: the values arr[i+1] - arr[i] are all equal for every
 * 0 <= i < arr.length - 1.
 *
 * Then, a value from arr was removed that was not the first or last value in the array.
 *
 * Return the removed value.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [5,7,11,13]
 * Output: 9
 * Explanation: The previous array was [5,7,9,11,13].
 * Example 2:
 *
 * Input: arr = [15,13,12]
 * Output: 14
 * Explanation: The previous array was [15,14,13,12].
 *
 *
 * Constraints:
 *
 * 3 <= arr.length <= 1000
 * 0 <= arr[i] <= 10^5
 */
public class MissingNumberInArithmeticProgression {

    public int missingNumber(int[] arr) {
        int N = arr.length;
        //find left right diffs
        int leftDif = arr[1] - arr[0];
        int rightDif = arr[N - 1] - arr[N - 2];
        //if they are not the same this means missing number is on the border and dif includes that
        //number. This means that we have dif + 2*dif and actual dif is leftDif + rightDif = dif + 2*dif = 3 dif =>
        //dif = sum/3
        if (leftDif != rightDif) {
            leftDif = (leftDif + rightDif) / 3;
        }
        int res = 0;
        for (int i = 0; i < N - 1; i++) {
            //if difference between two elements is not dif missing must be between i  and i + 1
            if (arr[i + 1] - arr[i] != leftDif) {
                res = arr[i] + leftDif;
                break;
            }
        }
        return res;
    }
}
