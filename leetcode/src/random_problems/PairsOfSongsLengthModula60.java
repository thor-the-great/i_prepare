package random_problems;

import java.util.HashMap;
import java.util.Map;

/**
 * 1013. Pairs of Songs With Total Durations Divisible by 60
 * Easy
 *
 * 6
 *
 * 0
 *
 * Favorite
 *
 * Share
 * In a list of songs, the i-th song has a duration of time[i] seconds.
 *
 * Return the number of pairs of songs for which their total duration in seconds is divisible by 60.  Formally,
 * we want the number of indices i < j with (time[i] + time[j]) % 60 == 0.
 *
 *
 *
 * Example 1:
 *
 * Input: [30,20,150,100,40]
 * Output: 3
 * Explanation: Three pairs have a total duration divisible by 60:
 * (time[0] = 30, time[2] = 150): total duration 180
 * (time[1] = 20, time[3] = 100): total duration 120
 * (time[1] = 20, time[4] = 40): total duration 60
 * Example 2:
 *
 * Input: [60,60,60]
 * Output: 3
 * Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 *
 *
 * Note:
 *
 * 1 <= time.length <= 60000
 * 1 <= time[i] <= 500
 */
public class PairsOfSongsLengthModula60 {

    /**
     * Idea:
     * pair will be divisible by 60 if sum is divisible => modulo of sum is 0.
     * convert original array to array of modulo 60.
     * iterate over that array and check for the current element. If found - arr number to res.
     * in the end add compliment of the number to the map as key, number of times met - as value
     * @param time
     * @return
     */
    public int numPairsDivisibleBy60(int[] time) {
        int N = time.length;
        int res = 0;

        for (int i = 0; i < N; i++) {
            time[i] %= 60;
        }

        Map<Integer, Integer> m = new HashMap();
        for (int i = 0; i <N; i++) {
            int el = time[i];
            int compl = 60 - el;
            if (compl == 60)
                compl = 0;
            if (m.containsKey(el)) {
                res += m.get(el);
            }
            if (!m.containsKey(compl))
                m.put(compl, 1);
            else
                m.put(compl, m.get(compl) + 1);
        }

        return res;
    }
}
