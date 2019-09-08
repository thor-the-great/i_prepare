package heap;

import java.util.*;

public class TopKFrequent {

    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList();
        //comparator for our object - first based on quantity then
        //if the same frequency - based on string (lexicographical order)
        Comparator<Obj> comp = new Comparator<Obj>() {
            public int compare(Obj o1, Obj o2) {
                if (o1.n != o2.n)
                    return o1.n - o2.n;
                return o2.w.compareTo(o1.w);
            }
        };

        PriorityQueue<Obj> pq = new PriorityQueue(comp);
        Map<String, Obj> m = new HashMap();
        //count how many times we met each word
        for (String w : words) {
            if (m.containsKey(w)) {
                m.get(w).n++;
            } else {
                m.put(w, new Obj(1, w));
            }
        }
        //iterate over unique words
        Set<String> keys = m.keySet();
        for (String key : keys) {
            pq.add(m.get(key));
            //if we have more than k - poll the min one as per our comparator
            if (pq.size() > k) {
                pq.poll();
            }
        }
        //for the result list, heap gives us the reverse order, so insert every next element to the 0 index, move
        //the rest to the right
        while(!pq.isEmpty()) {
            if (res.isEmpty())
                res.add(pq.poll().w);
            else
                res.add(0, pq.poll().w);
        }
        return res;
    }

    class Obj {
        //num of times we met this word
        int n;
        //word
        String w;
        Obj(int n, String w) {
            this.n = n;
            this.w = w;
        }
    }
}
