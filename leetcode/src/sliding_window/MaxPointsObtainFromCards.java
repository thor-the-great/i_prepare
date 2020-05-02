package sliding_window;

/**
 * 1423. Maximum Points You Can Obtain from Cards
 * Medium
 *
 * There are several cards arranged in a row, and each card has an associated number of points The points are given in
 * the integer array cardPoints.
 *
 * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
 *
 * Your score is the sum of the points of the cards you have taken.
 *
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 *
 *
 *
 * Example 1:
 *
 * Input: cardPoints = [1,2,3,4,5,6,1], k = 3
 * Output: 12
 * Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will
 * maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
 * Example 2:
 *
 * Input: cardPoints = [2,2,2], k = 2
 * Output: 4
 * Explanation: Regardless of which two cards you take, your score will always be 4.
 * Example 3:
 *
 * Input: cardPoints = [9,7,7,9,7,7,9], k = 7
 * Output: 55
 * Explanation: You have to take all the cards. Your score is the sum of points of all cards.
 * Example 4:
 *
 * Input: cardPoints = [1,1000,1], k = 1
 * Output: 1
 * Explanation: You cannot take the card in the middle. Your best score is 1.
 * Example 5:
 *
 * Input: cardPoints = [1,79,80,1,1,1,200,1], k = 3
 * Output: 202
 *
 *
 * Constraints:
 *
 * 1 <= cardPoints.length <= 10^5
 * 1 <= cardPoints[i] <= 10^4
 * 1 <= k <= cardPoints.length
 */
public class MaxPointsObtainFromCards {

  /**
   * Instead of checking two parts of the array from left and right ends we can decrement the central part from the
   * overall sum. Trying different combination is like moving this central part from left to right, which is
   * essentially a sliding window approach.
   *
   * For example, let's mark the part of the array that is participated in the point's score
   * [1,2,3,4,5,6,1], k =3
   * possible combinations:
   * 1,2,3 = 6; 1,2,3,[4,5,6,1]
   * 1,2,1 = 4; 1,2,[3,4,5,6,]1
   * 1,6,1 = 8; 1,[2,3,4,5,]6,1
   * 5,6,1 = 12; [1,2,3,4,]5,6,1
   *
   * as you can see excluded part of the array is simillar to a sliding window, we just need to count part of the
   * array outside the window
   *
   * O(n) time - iterate array twice - one time to calculate overall sum, second time to check every possible solution
   * O(1) space - few state variables were used
   * @param cardPoints
   * @param k
   * @return
   */
  public int maxScore(int[] cardPoints, int k) {
    int N = cardPoints.length;

    int sum = 0;
    for (int n : cardPoints)
      sum += n;
    if (N == k)
      return sum;
    int l = 0, max = Integer.MIN_VALUE, cur = 0, i = 0;
    for (; i < N - k - 1; i++)
      cur+=cardPoints[i];

    for (; i < N; i++) {
      cur+= cardPoints[i];
      max = Math.max(max, sum - cur);
      cur-=cardPoints[l++];
    }
    return max;
  }
}
