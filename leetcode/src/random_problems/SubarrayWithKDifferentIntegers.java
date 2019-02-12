package random_problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SubarrayWithKDifferentIntegers {

    public int subarraysWithKDistinct(int[] A, int K) {
        int N = A.length;
        if (N == 0)
            return 0;
        Set<Integer> set = new HashSet<>();
        int start = 0, end = 0;
        int res = 0;

        while (end < N) {
            int endEl = A[end];
            if (!set.contains(endEl))
                set.add(endEl);
            //else
             //   map.put(endEl, map.get(endEl) + 1);

            int size = set.size();
            if (size > K) {
                set.clear();
                start++;
                end = start;
            } else if (size == K) {
                res++;
                end++;
                if (end == N) {
                    set.clear();
                    start++;
                    end = start;
                }
            } else
                end++;
        }

        return res;
    }

    public static void main(String[] args) {
        SubarrayWithKDifferentIntegers obj = new SubarrayWithKDifferentIntegers();
        System.out.println(obj.subarraysWithKDistinct(new int[] {2, 1, 2, 1, 2}, 2));
    }
}
