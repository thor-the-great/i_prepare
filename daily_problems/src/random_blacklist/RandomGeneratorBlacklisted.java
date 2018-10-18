package random_blacklist;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * This question was asked by Google.
 *
 * Given an integer n and a list of integers l, write a function that randomly generates a number from 0 to n-1
 * that isn't in l (uniform).
 *
 * Idea - to reuse continuous random generator we need to re-map numbers from blacklist that are in the range 0..n to
 * some other numbers and then call rand(new_limit). Each time we bumped into blacklisted number - read actual number
 * from the map.
 *
 * How we re-map - we iterate over blacklist two times. First time we mark every number to map as -1. Second time
 * we do actual re-map, including special case indexes - like {1, 2, 19}, 20. 19 will be re-mapped first time 2-19, then
 * 19 ->? To avoid 2->19 (invalid) we need to map 2->next available num 18. This is done in nested while.
 *
 */
public class RandomGeneratorBlacklisted {
    Map<Integer, Integer> map = new HashMap();
    Random rand;
    int newLimit;

   RandomGeneratorBlacklisted (int[] blacklist, int n) {
        map = new HashMap();
        for (int numEl : blacklist) {
            if (numEl >=0 && numEl <= n) {
                if (!map.containsKey(numEl)) {
                    map.put(numEl, -1);
                }
            }
        }
        newLimit = n - map.size();
        int tmp = n;
        for (int numEl : blacklist) {
            if (numEl >= 0 && numEl <= n) {
                if (numEl < newLimit) {
                    //avoid double-mapping cases. Those numbers will be mapped to next available number
                    while (map.containsKey(tmp))
                        tmp--;
                    map.put(numEl, tmp);
                    tmp--;
                }
            }
        }
        rand = new Random();
    }

    public int nextRandom() {
        int nextRandom = rand.nextInt(newLimit);
        if (map.containsKey(nextRandom))
            return map.get(nextRandom);
        return nextRandom;
    }

    public static void main(String[] args) {
        RandomGeneratorBlacklisted obj = new RandomGeneratorBlacklisted(new int[]{4, 5, -10, -1, 50, 3, 15, 19, 5, 7, 6, 12}, 10);
        Map<Integer, Integer> counts = new HashMap<>();
        int numOfTries = 100000;
        IntStream.range(0, numOfTries).
                forEach(n->
                {   int r = obj.nextRandom();
                    int c = counts.getOrDefault(r, 0);
                    counts.put(r, c + 1);
                });
        counts.forEach((k, v) -> System.out.println(k + " = " + (100*v)/numOfTries));
    }
}
