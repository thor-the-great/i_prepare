package map;

import java.util.HashSet;
import java.util.Set;

public class KDiffPairsInArray {

    public int findPairs(int[] nums, int k) {
        if (k < 0)
            return 0;
        int res = 0;
        Set<Integer> set = new HashSet();
        //if k == 0 we need to count only repeated nums
        //for that we need second set that indicats which num we have
        //count already
        if (k == 0 ) {
            Set<Integer> seen = new HashSet();
            for (int n : nums) {
                //if we met this num before - add it to the second set
                if (set.contains(n)) {
                    seen.add(n);
                } else
                    set.add(n);
            }
            //size of second set will be the resulting num
            res = seen.size();
        } else {
            //for k > 0 we check if n + k is in the set, this means we have a pair
            for (int n : nums) {
                set.add(n);
            }

            for (int n : set) {
                if (set.contains(n + k))
                    res++;
            }
        }
        return res;
    }
}
