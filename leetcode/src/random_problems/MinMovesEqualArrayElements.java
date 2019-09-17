package random_problems;

/**
 * 453. Minimum Moves to Equal Array Elements
 * Easy
 *
 * Given a non-empty integer array of size n, find the minimum number of moves required to make
 * all array elements equal, where a move is incrementing n - 1 elements by 1.
 *
 * Example:
 *
 * Input:
 * [1,2,3]
 *
 * Output:
 * 3
 *
 * Explanation:
 * Only three moves are needed (remember each move increments two elements):
 *
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 */
public class MinMovesEqualArrayElements {

  /**
   * Idea - when we add 1 to all elements but one, it's equivalent to minus one from that one.
   * so we are finding number of moves we need to make all numbers equal to the min one. This can
   * be found by getting min and then subtracting that min from every number. This can be done in
   * one pass
   * @param nums
   * @return
   */
  public int minMoves(int[] nums) {
    int res = 0;
    int min = Integer.MAX_VALUE;
    for (int n : nums) {
      res += n;
      if (n < min)
        min = n;
    }
    return res - (min*nums.length);
  }
}
