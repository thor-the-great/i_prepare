package arrays;

/**
 * 1497. Check If Array Pairs Are Divisible by k
 * https://leetcode.com/problems/check-if-array-pairs-are-divisible-by-k/
 *
 * Medium
 *
 * Given an array of integers arr of even length n and an integer k.
 *
 * We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.
 *
 * Return True If you can find a way to do that or False otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
 * Output: true
 * Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).
 * Example 2:
 *
 * Input: arr = [1,2,3,4,5,6], k = 7
 * Output: true
 * Explanation: Pairs are (1,6),(2,5) and(3,4).
 * Example 3:
 *
 * Input: arr = [1,2,3,4,5,6], k = 10
 * Output: false
 * Explanation: You can try all possible pairs to see that there is no way to divide arr into 3 pairs each with sum divisible by 10.
 * Example 4:
 *
 * Input: arr = [-10,10], k = 2
 * Output: true
 * Example 5:
 *
 * Input: arr = [-1,1,-2,2,-3,3,-4,4], k = 3
 * Output: true
 *
 *
 * Constraints:
 *
 * arr.length == n
 * 1 <= n <= 10^5
 * n is even.
 * -10^9 <= arr[i] <= 10^9
 * 1 <= k <= 10^5
 *
 */
public class CheckIfArrayPairsAreDivisibleByK {

  /**
   * If pair is divisible by k it means the sum is divisible. We can skip the whole value and operate only with % k.
   * We can do %k for each number and then compare the sum, if any creates number (num1 + num2)%k == 0.
   * In reallity any number can be joined with other any num2. So the only thing we are interested is frequency.
   *
   * O(n) time
   * O(k) space
   *
   * @param arr
   * @param k
   * @return
   */
  public boolean canArrange(int[] arr, int k) {
    int[] freq = new int[k];
    for (int n : arr) {
      int num = (n % k);
      if (num >= 0)
        ++freq[num];
      else {
        ++freq[(num + k)%k];
      }
    }

    if (freq[0] % 2 != 0)
      return false;

    for (int i = 1; i < k - 1; i++) {
      if (freq[i] != freq[k - i])
        return false;
    }
    return true;
  }
}
