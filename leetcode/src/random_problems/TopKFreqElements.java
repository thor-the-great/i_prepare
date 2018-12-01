package random_problems;

import java.util.*;

public class TopKFreqElements {

    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            int c = map.getOrDefault(num, 0);
            map.put(num, c + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue(map.size(), (Comparator<int[]>) (o1, o2) -> Integer.compare(o1[1], o2[1]));
        Set<Integer> keys = map.keySet();
        for (int key : keys) {
            pq.add(new int[]{key, map.get(key)});
        }
        while(pq.size() > k) {
            pq.poll();
        }
        List<Integer> res = new ArrayList<>();
        while(!pq.isEmpty()) {
            int[] arr = pq.poll();
            res.add(arr[0]);
        }
        return res;
    }

    public static void main(String[] args) {
        TopKFreqElements obj = new TopKFreqElements();
        System.out.println(obj.topKFrequent(new int[] {1,1,1,2,2,3}, 2));

        System.out.println(obj.topKFrequent(new int[] {1,1,1,2,2,3, 5, 3, 2, 1, 2, 3}, 3));
    }
}
