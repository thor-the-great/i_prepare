package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1046. Last Stone Weight
 * Easy
 *
 * We have a collection of rocks, each rock has a positive integer weight.
 *
 * Each turn, we choose the two heaviest rocks and smash them together.  Suppose the stones have
 * weights x and y with x <= y.  The result of this smash is:
 *
 * If x == y, both stones are totally destroyed;
 * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new
 * weight y-x.
 * At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are
 * no stones left.)
 *
 *
 *
 * Example 1:
 *
 * Input: [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
 * we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
 * we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.
 *
 *
 * Note:
 *
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 */
public class LastStoneWeight {

  /**
   * We build a pq in desc order, then pick two top elements and calculate the result of collision
   * If somerthing left - put the leftover stone back to pq.
   * Do thing untill pq size > 1. The last operation may leave no stones in pq, in this case
   * return 0, otherwise return the last stone
   * @param stones
   * @return
   */
  public int lastStoneWeight(int[] stones) {
    if (stones == null || stones.length == 0)
      return 0;
    PriorityQueue<Integer> pq = new PriorityQueue((Comparator<Integer>) (i1, i2)->i2-i1);
    for (int n : stones)
      pq.add(n);

    while(pq.size() > 1) {
      int s1 = pq.poll();
      int s2 = pq.poll();
      if (s1 > s2)
        pq.add(s1 - s2);
    }

    return pq.isEmpty() ? 0 : pq.peek();
  }
}
