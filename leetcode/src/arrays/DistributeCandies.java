package arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * 575. Distribute Candies
 * Easy
 *
 * Given an integer array with even length, where different numbers in this array represent different kinds of candies.
 * Each number means one candy of the corresponding kind. You need to distribute these candies equally in number
 * to brother and sister. Return the maximum number of kinds of candies the sister could gain.
 * Example 1:
 * Input: candies = [1,1,2,2,3,3]
 * Output: 3
 * Explanation:
 * There are three different kinds of candies (1, 2 and 3), and two candies for each kind.
 * Optimal distribution: The sister has candies [1,2,3] and the brother has candies [1,2,3], too.
 * The sister has three different kinds of candies.
 * Example 2:
 * Input: candies = [1,1,2,3]
 * Output: 2
 * Explanation: For example, the sister has candies [2,3] and the brother has candies [1,1].
 * The sister has two different kinds of candies, the brother has only one kind of candies.
 * Note:
 *
 * The length of the given array is in range [2, 10,000], and will be even.
 * The number in given array is in range [-100,000, 100,000].
 */
public class DistributeCandies {

    /**
     * Idea - we count different kinds of candies, then the problematic ones are with qty 1. We need to give one of
     * each kind to a sister, after that it doesn't matter. So if the n/2 is less then number of different kinds - we
     * can just do n/2 at best, otherwise - we give number of different kinds.
     * Save every kind in a set, then set.size() gives the number of kinds
     * @param candies
     * @return
     */
    public int distributeCandies(int[] candies) {
        int N = candies.length;

        Set<Integer> set = new HashSet();
        for (int cand : candies)
            set.add(cand);

        return Math.min(N/2, set.size());
    }
}
