package heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFreqNumber {
    
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap();
        for (int n : nums) {
            if (countMap.containsKey(n)) {
                countMap.put(n, countMap.get(n) + 1);
            } else {
                countMap.put(n, 1);
            }
        }
        Comparator<int[]> comp = (a1, a2) -> a2[1] - a1[1];
        PriorityQueue<int[]> pq = new PriorityQueue<>();
        
        for (int num : countMap.keySet()) {
            pq.add(new int[] {num, countMap.get(num)});
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int[] res = new int[pq.size()];
        
        for (int i = 0; i < res.length; i++) {
            res[i] = pq.poll()[0];    
        }
        return res;
    }
}
