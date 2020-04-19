package random_problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 1414. Find the Minimum Number of Fibonacci Numbers Whose Sum Is K
 *
 * Difficulty: Medium
 * Given the number k, return the minimum number of Fibonacci numbers whose sum is equal to k, whether a Fibonacci
 * number could be used multiple times.
 *
 * The Fibonacci numbers are defined as:
 *
 * F1 = 1
 * F2 = 1
 * Fn = Fn-1 + Fn-2 , for n > 2.
 * It is guaranteed that for the given constraints we can always find such fibonacci numbers that sum k.
 *
 *
 * Example 1:
 *
 * Input: k = 7
 * Output: 2
 * Explanation: The Fibonacci numbers are: 1, 1, 2, 3, 5, 8, 13, ...
 * For k = 7 we can use 2 + 5 = 7.
 * Example 2:
 *
 * Input: k = 10
 * Output: 2
 * Explanation: For k = 10 we can use 2 + 8 = 10.
 * Example 3:
 *
 * Input: k = 19
 * Output: 3
 * Explanation: For k = 19 we can use 1 + 5 + 13 = 19.
 *
 *
 * Constraints:
 *
 * 1 <= k <= 10^9
 */
public class FindMinimumNumberOfFibonacciSumIsK {

  /**
   * Count fibonacci up to the k. Then start count back using list of
   * numbers calculated previously
   * @param k
   * @return
   */
  public int findMinFibonacciNumbers(int k) {
    List<Integer> fibNums = new ArrayList();
    fibNums.add(1);
    //calculate fibonacci numbers up to k, put to the list in increasing order
    int cur = 1, prev = 1;
    while ((cur + prev) <= k) {
      int next = cur + prev;
      fibNums.add(next);
      prev = cur;
      cur = next;
    }
    //start iterating over the list from right to left (from highest to lowest number)
    int res = 0, pos = fibNums.size() - 1;
    //while k is not reached
    while (k > 0) {
      //get the highest fibonacci that we haven't checked yet
      int num = fibNums.get(pos);
      //if it's less than leftover of k - this number can be used in result
      if (k >= num) {
        k -= num;
        ++res;
      }
      //if the same number cannot be used for next iteration - move pointer to the next (lowest) fibonacci
      if (k < num && pos > 0)
        --pos;
    }
    return res;
  }
}
