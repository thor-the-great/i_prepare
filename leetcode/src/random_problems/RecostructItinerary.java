package random_problems;

import java.util.*;

/**
 * 332. Reconstruct Itinerary
 * Medium
 *
 * 615
 *
 * 410
 *
 * Favorite
 *
 * Share
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct
 * the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin
 * with JFK.
 *
 * Note:
 *
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when
 * read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * Example 1:
 *
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 * Example 2:
 *
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 *              But it is larger in lexical order.
 */
public class RecostructItinerary {

    LinkedList<String> res = new LinkedList();
    Map<String, PriorityQueue<String>> m = new HashMap();

    /**
     * Idea: group elements by from city, for every key the value is the minheap of the 'to' cities. This gives us
     * lexicographical order. Then start visiting cities in "reverse" order, but add visited cities to the list
     * from the head - this gives the correct final order
     * @param tickets
     * @return
     */
    public List<String> findItinerary(String[][] tickets) {

        for (String[] t : tickets) {
            String from = t[0];
            if (!m.containsKey(from)) {
                PriorityQueue<String> set = new PriorityQueue();
                set.add(t[1]);
                m.put(from, set);
            } else {
                m.get(from).add(t[1]);
            }
        }

        visit("JFK");
        return res;
    }

    void visit(String from) {

        while (m.containsKey(from) && !m.get(from).isEmpty()) {
            String to = m.get(from).poll();
            visit(to);
        }
        res.addFirst(from);
    }
}
