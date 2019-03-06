package random_problems;

import java.util.*;

/**
 * 710. Random Pick with Blacklist
 * Hard
 *
 * Given a blacklist B containing unique integers from [0, N), write a function to return a uniform random integer
 * from [0, N) which is NOT in B.
 *
 * Optimize it such that it minimizes the call to system’s Math.random().
 *
 * Note:
 *
 * 1 <= N <= 1000000000
 * 0 <= B.length < min(100000, N)
 * [0, N) does NOT include N. See interval notation.
 * Example 1:
 *
 * Input:
 * ["Solution","pick","pick","pick"]
 * [[1,[]],[],[],[]]
 * Output: [null,0,0,0]
 * Example 2:
 *
 * Input:
 * ["Solution","pick","pick","pick"]
 * [[2,[]],[],[],[]]
 * Output: [null,1,1,1]
 * Example 3:
 *
 * Input:
 * ["Solution","pick","pick","pick"]
 * [[3,[1]],[],[],[]]
 * Output: [null,0,0,2]
 * Example 4:
 *
 * Input:
 * ["Solution","pick","pick","pick"]
 * [[4,[2]],[],[],[]]
 * Output: [null,1,3,1]
 * Explanation of Input Syntax:
 *
 * The input is two lists: the subroutines called and their arguments. Solution's constructor has two arguments,
 * N and the blacklist B. pick has no arguments. Arguments are always wrapped with a list, even if there aren't any.
 */
public class RandomPickWithBlackList {

    Random rand;
    int whiteLen;
    Map<Integer, Integer> m;

    /**
     * Idea - use whitelist with a mapping between blacklisted and whitelisted.
     * @param N
     * @param blacklist
     */
    public RandomPickWithBlackList(int N, int[] blacklist) {
        rand = new Random();
        whiteLen = N - blacklist.length;
        Set<Integer> set = new HashSet();
        for (int i = whiteLen; i < N; i ++)
            set.add(i);
        for (int i  = 0; i < blacklist.length; i++) {
            if (blacklist[i] >= whiteLen)
                set.remove(blacklist[i]);
        }
        Iterator<Integer> mappedNumsIt = set.iterator();
        m = new HashMap();
        for (int b : blacklist) {
            if (b < whiteLen) {
                m.put(b, mappedNumsIt.next());
            }
        }
    }

    public int pick() {
        int nextRand = rand.nextInt(whiteLen);
        if (m.containsKey(nextRand))
            return m.get(nextRand);
        else
            return nextRand;
    }
}
