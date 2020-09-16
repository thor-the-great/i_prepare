package random_problems;

import java.util.HashSet;
import java.util.Set;

/**
 * 421. Maximum XOR of Two Numbers in an Array
 * Medium
 *
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
 *
 * Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
 *
 * Could you do this in O(n) runtime?
 *
 * Example:
 *
 * Input: [3, 10, 5, 25, 2, 8]
 *
 * Output: 28
 *
 * Explanation: The maximum result is 5 ^ 25 = 28.
 *
 */
public class MaxXORInArray {

    /**
     * This line if(set.contains(tmp ^ prefix))
     * The tricky part here is that we need to be aware of a key property of XOR applying on the above line:
     * if A ^ B = C, then A ^ B ^ B = C ^ B, then A = C ^ B
     * Before executing that line, max stands for the maximum we can get if we consider only the most significant i - 1
     * bits, tmp stands for the potential max value we can get when considering the most significant i bits. How can we
     * get this tmp? The only way we can get this value is that we have two values A and B in the set (a set of most
     * significant i bits of each member), such that A ^ B equals to tmp. As mentioned earlier, A ^ B = tmp is
     * equivalent to A = tmp ^ B. Here is where that line comes in: set.contains(tmp ^ B).
     *
     * @param nums
     * @return
     */
    public int findMaximumXOR(int[] nums) {
        int max = 0, mask = 0;
        for (int i = 31; i >=0; i--) {
            int nextPartOfMask = (1<<i);
            mask |= nextPartOfMask;
            Set<Integer> set = new HashSet();
            for (int num : nums) {
                set.add(num & mask);
            }

            int greedyAssumption = max | nextPartOfMask;
            for (int leftPartAsPerMask : set) {
                if (set.contains(greedyAssumption ^ leftPartAsPerMask)) {
                    max = greedyAssumption;
                    break;
                }
            }
        }
        return max;
    }
}
