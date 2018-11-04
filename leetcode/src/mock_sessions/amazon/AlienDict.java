package mock_sessions.amazon;

import java.util.*;

/**
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
 * You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of
 * this new language. Derive the order of letters in this language.
 *
 * Example 1:
 *
 * Input:
 * [
 *   "wrt",
 *   "wrf",
 *   "er",
 *   "ett",
 *   "rftt"
 * ]
 *
 * Output: "wertf"
 * Example 2:
 *
 * Input:
 * [
 *   "z",
 *   "x"
 * ]
 *
 * Output: "zx"
 * Example 3:
 *
 * Input:
 * [
 *   "z",
 *   "x",
 *   "z"
 * ]
 *
 * Output: ""
 *
 * Explanation: The order is invalid, so return "".
 * Note:
 *
 * You may assume all letters are in lowercase.
 * You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is fine.
 */
public class AlienDict {
    /**
     * Idea is - build graph of letters, if letter if after another letter - create directed edge from master to slave
     * Then do topological sort, keep track of vertex with 0 in-degree. Start for every such vertex the topo sort
     * Result of topo sort will be out abc
     *
     * @param words
     * @return
     */
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> m = new HashMap();
        LinkedHashMap<Character, Integer> indegreeMap = new LinkedHashMap();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++){
                indegreeMap.putIfAbsent(word.charAt(i), 0);
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            int length = Math.min(words[i].length(), words[i + 1].length());
            for (int j = 0; j < length; j++) {
                char v1 = words[i].charAt(j);
                char v2 = words[i + 1].charAt(j);
                if (v1 != v2) {
                    m.putIfAbsent(v1, new HashSet<>());
                    Set<Character> adj = m.get(v1);
                    if (!adj.contains(v2)) {
                        adj.add(v2);
                        indegreeMap.put(v2, indegreeMap.get(v2) + 1);
                    }
                    break;
                }
            }
        }
        Queue<Character> q = new LinkedList<>();
        for (char ch : indegreeMap.keySet()) {
            if (indegreeMap.get(ch) == 0) {
                q.add(ch);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            char nextStartChar = q.poll();
            sb.append(nextStartChar);
            if (m.containsKey(nextStartChar)) {
                Set<Character> adj = m.get(nextStartChar);
                for (char adjChar : adj) {
                    int newIndegree = indegreeMap.get(adjChar) - 1;
                    indegreeMap.put(adjChar, newIndegree);
                    if (newIndegree == 0)
                        q.add(adjChar);
                }
            }
        }
        if (sb.length() != indegreeMap.size())
            return "";
        return sb.toString();
    }

    public static void main(String[] args) {
        AlienDict obj = new AlienDict();
        System.out.println(obj.alienOrder(new String[]{"wrt", "wrf", "er"}));

        System.out.println(obj.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}));

        System.out.println(obj.alienOrder(new String[]{"z", "x"}));

        System.out.println(obj.alienOrder(new String[]{"z", "x", "z"}));

        System.out.println(obj.alienOrder(new String[]{"za", "zb", "ca", "cb"}));

    }
}
