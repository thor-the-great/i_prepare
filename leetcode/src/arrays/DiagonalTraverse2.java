package arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 1424. Diagonal Traverse II
 * Medium
 *
 * Given a list of lists of integers, nums, return all elements of nums in diagonal order as shown in the below images.
 *
 *
 * Example 1:
 *
 *
 *
 * Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,4,2,7,5,3,8,6,9]
 * Example 2:
 *
 *
 *
 * Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
 * Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
 * Example 3:
 *
 * Input: nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
 * Output: [1,4,2,5,3,8,6,9,7,10,11]
 * Example 4:
 *
 * Input: nums = [[1,2,3,4,5,6]]
 * Output: [1,2,3,4,5,6]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i].length <= 10^5
 * 1 <= nums[i][j] <= 10^9
 * There at most 10^5 elements in nums.
 */
public class DiagonalTraverse2 {

  /**
   * Catch - for numbers on one diagonal sum of index of the list and index in the list is the same.
   * Another catch - if we swipe from top to bottom from left to right then elements are traversed in reversed order
   * for each diagonal. This means if we use stack then poping from the stack will give us correct final order
   *
   * O(n) time
   * O(n) space
   * @param nums
   * @return
   */
  public int[] findDiagonalOrder(List<List<Integer>> nums) {
    int count = 0;
    List<Stack<Integer>> list = new ArrayList();
    for (int i = 0; i < nums.size(); i++) {
      List<Integer> oneList = nums.get(i);
      for (int j = 0; j < oneList.size(); j++) {
        //this is id of the diagonal
        int idx = i + j;
        //check if we haven't checked this diagonal before
        if (list.size() < idx + 1) {
          list.add(new Stack());
        }
        list.get(idx).push(oneList.get(j));
        ++count;
      }
    }
    //now traverse the list of stacks to form the final array
    int[] res = new int[count];
    int p = 0;
    for (Stack<Integer> stack : list) {
      while(!stack.isEmpty()) {
        res[p++] = stack.pop();
      }
    }
    return res;
  }
}
