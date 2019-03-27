package random_problems;

import java.util.HashSet;
import java.util.Set;

/**
 * 1022. Smallest Integer Divisible by K
 * Medium
 * Given a positive integer K, you need find the smallest positive integer N such that N is divisible by K, and N
 * only contains the digit 1.
 *
 * Return the length of N.  If there is no such N, return -1.
 *
 * Example 1:
 *
 * Input: 1
 * Output: 1
 * Explanation: The smallest answer is N = 1, which has length 1.
 * Example 2:
 *
 * Input: 2
 * Output: -1
 * Explanation: There is no such positive integer N divisible by 2.
 * Example 3:
 *
 * Input: 3
 * Output: 3
 * Explanation: The smallest answer is N = 111, which has length 3.
 */
public class SmallestIntegerDivisibleByK {

    /**
     * Idea: if mod 5 or 2 is 0 - it's not possible.
     * Then start checking mods. By pigeon hols principle if we met the same mod for the second time - it means we
     * will do loops, so doesn't male sense to continue. Thus start forming 11.11 numbers and check mods, if it's
     * 0 - return i, if we met it before - return -1
     * @param K
     * @return
     */
    public int smallestRepunitDivByK(int K) {
        if (K % 2 == 0 || K % 5 == 0)
            return -1;

        Set<Integer> rems = new HashSet();
        int mod = 0;

        for (int i = 1; i <= K; i++) {
            mod = (10*mod + 1) % K;
            if (mod == 0)
                return i;

            if (rems.contains(mod))
                return -1;

            rems.add(mod);
        }

        return -1;
    }
}
