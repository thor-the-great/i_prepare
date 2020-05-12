package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * 1441. Build an Array With Stack Operations
 * Easy
 *
 * Given an array target and an integer n. In each iteration, you will read a number from  list =
 * {1,2,3..., n}.
 *
 * Build the target array using the following operations:
 *
 * Push: Read a new element from the beginning list, and push it in the array.
 * Pop: delete the last element of the array.
 * If the target array is already built, stop reading more elements.
 * You are guaranteed that the target array is strictly increasing, only containing numbers
 * between 1 to n inclusive.
 *
 * Return the operations to build the target array.
 *
 * You are guaranteed that the answer is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: target = [1,3], n = 3
 * Output: ["Push","Push","Pop","Push"]
 * Explanation:
 * Read number 1 and automatically push in the array -> [1]
 * Read number 2 and automatically push in the array then Pop it -> [1]
 * Read number 3 and automatically push in the array -> [1,3]
 * Example 2:
 *
 * Input: target = [1,2,3], n = 3
 * Output: ["Push","Push","Push"]
 * Example 3:
 *
 * Input: target = [1,2], n = 4
 * Output: ["Push","Push"]
 * Explanation: You only need to read the first 2 numbers and stop.
 * Example 4:
 *
 * Input: target = [2,3,4], n = 4
 * Output: ["Push","Pop","Push","Push","Push"]
 *
 *
 * Constraints:
 *
 * 1 <= target.length <= 100
 * 1 <= target[i] <= 100
 * 1 <= n <= 100
 * target is strictly increasing.
 */
public class BuildArrayWithStackOperations {

  /**
   * We can accumulate result using simple simulation. Start with 0 and start incrementing
   * (reading from the list). Each next increment if it's less than next element in target should
   * be discarded. This means pair of "Push" (increment) and "Pop" (discard). Then one more
   * operation to leave the number in the list - "Push".
   * Do such iterations for every number from target.
   *
   * O(n) - iterate over each number from 0 to n. O(1) space - no extra data structure used.
   * @param target
   * @param n
   * @return
   */
  public List<String> buildArray(int[] target, int n) {
    int cur = 0;
    List<String> res = new ArrayList();
    for (int t : target) {
      while (++cur < t) {
        res.add("Push");
        res.add("Pop");
      }
      res.add("Push");
    }
    return res;
  }
}
