package grooking_coding_patterns.graphs.topological_sort;

import java.util.*;

/**
 * Alien Dictionary (hard)
 *
 * There is a dictionary containing words from an alien language for which we don’t know the ordering of the
 * characters. Write a method to find the correct order of characters in the alien language.
 *
 * Example 1:
 *
 * Input: Words: ["ba", "bc", "ac", "cab"]
 * Output: bac
 * Explanation: Given that the words are sorted lexicographically by the rules of the alien language, so
 * from the given words we can conclude the following ordering among its characters:
 *
 * 1. From "ba" and "bc", we can conclude that 'a' comes before 'c'.
 * 2. From "bc" and "ac", we can conclude that 'b' comes before 'a'
 *
 * From the above two points, we can conclude that the correct character order is: "bac"
 * Example 2:
 *
 * Input: Words: ["cab", "aaa", "aab"]
 * Output: cab
 * Explanation: From the given words we can conclude the following ordering among its characters:
 *
 * 1. From "cab" and "aaa", we can conclude that 'c' comes before 'a'.
 * 2. From "aaa" and "aab", we can conclude that 'a' comes before 'b'
 *
 * From the above two points, we can conclude that the correct character order is: "cab"
 * Example 3:
 *
 * Input: Words: ["ywx", "wz", "xww", "xz", "zyy", "zwz"]
 * Output: ywxz
 * Explanation: From the given words we can conclude the following ordering among its characters:
 *
 * 1. From "ywx" and "wz", we can conclude that 'y' comes before 'w'.
 * 2. From "wz" and "xww", we can conclude that 'w' comes before 'x'.
 * 3. From "xww" and "xz", we can conclude that 'w' comes before 'z'
 * 4. From "xz" and "zyy", we can conclude that 'x' comes before 'z'
 * 5. From "zyy" and "zwz", we can conclude that 'y' comes before 'w'
 *
 * From the above five points, we can conclude that the correct character order is: "ywxz"
 */
public class AlienDictionary {

    /**
     * Build graph (directed, first different letter in next word is after the letter in a prev word)
     * based on words array
     * Do the topological sort for the characters
     * O(V+N) time and space (where V is the total number of characters and N is number of rules in a language)
     * @param words
     * @return
     */
    public static String findOrder(String[] words) {

        Map<Character, List<Character>> g = new HashMap();
        Map<Character, Integer> indegree = new HashMap();

        for (int x = 0; x < words.length; x++) {
            for (char ch : words[x].toCharArray()) {
                indegree.put(ch, 0);
                g.put(ch, new ArrayList());
            }
        }

        for (int x = 0; x < words.length - 1; x++) {
            for (int i = 0; i < Math.min(words[x].length(), words[x + 1].length()); i++) {
                char chx = words[x].charAt(i), chy = words[x + 1].charAt(i);
                if (chx != chy) {
                    indegree.put(chy, indegree.getOrDefault(chy, 0) + 1);
                    g.get(chx).add(chy);
                    break;
                }
            }
        }

        Queue<Character> q = new LinkedList();
        for (char ch : indegree.keySet()) {
            if (indegree.get(ch) == 0)
                q.add(ch);
        }

        char[] resStr = new char[indegree.size()];
        int resP = 0;

        while(!q.isEmpty()) {
            char ch = q.poll();
            resStr[resP++] = ch;
            if (g.containsKey(ch)) {
                for (char next : g.get(ch)) {
                    indegree.put(next, indegree.get(next) - 1);
                    if (indegree.get(next) == 0) {
                        q.add(next);
                    }
                }
            }
        }

        return new String(resStr);
    }

    public static void main(String[] args) {
        String result = AlienDictionary.findOrder(new String[] { "ba", "bc", "ac", "cab" });
        System.out.println("Character order: " + result);

        result = AlienDictionary.findOrder(new String[] { "cab", "aaa", "aab" });
        System.out.println("Character order: " + result);

        result = AlienDictionary.findOrder(new String[] { "ywx", "wz", "xww", "xz", "zyy", "zwz" });
        System.out.println("Character order: " + result);
    }
}