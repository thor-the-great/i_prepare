package path.linkedin;

import java.util.Random;

/**
 * 528. Random Pick with Weight
 * Medium
 * 126
 * 161
 *
 *
 * Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which
 * randomly picks an index in proportion to its weight.
 *
 * Note:
 *
 * 1 <= w.length <= 10000
 * 1 <= w[i] <= 10^5
 * pickIndex will be called at most 10000 times.
 * Example 1:
 *
 * Input:
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * Output: [null,0]
 * Example 2:
 *
 * Input:
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * Output: [null,0,1,1,1,0]
 * Explanation of Input Syntax:
 *
 * The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the
 * array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.
 */
public class RandomPickWeight {

    int[] arr;
    int sum = 0;
    Random rand = new Random();

    /**
     * Idea is - write prefix array of weights where each prefix stored as per initial index. Summarize all prefixes.
     * Then generate random num up to that sum. Then do the binary search for that array of prefixes
     * @param w
     */
    public RandomPickWeight(int[] w) {
        sum = 0;
        arr = new int[w.length];
        for (int i = 0; i < w.length; i++) {
            sum += w[i];
            arr[i] = sum;
        }
    }

    public int pickIndex() {
        int num = rand.nextInt(sum);
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int m = (r + l) /2;
            if ( arr[m] > num )
                r = m;
            else
                l = m + 1;
        }
        return l;
    }
}
