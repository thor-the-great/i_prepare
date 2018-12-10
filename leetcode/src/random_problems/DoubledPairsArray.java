package random_problems;

import java.util.TreeMap;

/**
 * 954. Array of Doubled Pairs
 * Difficulty: Medium
 * Given an array of integers A with even length, return true if and only if it is possible to reorder it such that
 * A[2 * i + 1] = 2 * A[2 * i] for every 0 <= i < len(A) / 2.
 *
 *
 *
 * Example 1:
 *
 * Input: [3,1,3,6]
 * Output: false
 * Example 2:
 *
 * Input: [2,1,2,6]
 * Output: false
 * Example 3:
 *
 * Input: [4,-2,2,-4]
 * Output: true
 * Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
 * Example 4:
 *
 * Input: [1,2,4,16,8,4]
 * Output: false
 *
 *
 * Note:
 *
 * 0 <= A.length <= 30000
 * A.length is even
 * -100000 <= A[i] <= 100000
 */
public class DoubledPairsArray {
    /**
     * Idea - check every number for the pair. If we start from the lowest then pair can be only greater. If no pair
     * found - it's not possible.
     * Initially we count nums and store counts in TreeMap. For every number we check count for it and pair, set 0
     * (remove) if it's zero.
     * Catch: 0 must be present twice cause pair for 0 is 0. 
     *
     * @param A
     * @return
     */
    public boolean canReorderDoubled(int[] A) {
        TreeMap<Integer, Integer> map = new TreeMap();
        for (int n : A) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        while (!map.isEmpty()) {
            int key = map.firstKey();
            int pair = key >= 0 ? 2*key : key / 2;
            if ((key == 0 && map.get(key) >= 2)
                    || (key != 0 && map.containsKey(pair)))  {
                adjustQty(map, key);
                adjustQty(map, pair);
            }
            else
                return false;
        }
        return true;
    }

    void adjustQty(TreeMap<Integer, Integer> m, int key) {
        int c = m.get(key);
        if (c == 1)
            m.remove(key);
        else
            m.put(key, c - 1);
    }

    public static void main(String[] args) {
        DoubledPairsArray obj = new DoubledPairsArray();
        System.out.println(obj.canReorderDoubled(new int[] {4, -2, 2, -4}));
    }
}
