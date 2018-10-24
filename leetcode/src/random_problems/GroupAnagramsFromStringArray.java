package random_problems;

import java.util.*;

/**
 * Given an array of strings, group anagrams together.
 *
 * Example:
 *
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * Note:
 *
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 *
 */
public class GroupAnagramsFromStringArray {
    int[] primes = new int[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79,
            83, 89, 97, 101};

    /**
     * Idea is - create hash that will be identical for all strings are anagrams. This is easy because all letters are
     * the same. For that to guarantee uniqueness we'll use product of primes where each prime will be assigned to
     * the letter via array.
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Long, List<String>> m = new HashMap();
        List<List<String>> res = new ArrayList();
        for (String s : strs) {
            long hash = getHash(s);
            if (!m.containsKey(hash)) {
                List<String> l = new LinkedList();
                l.add(s);
                res.add(l);
                m.put(hash, l);
            }
            else {
                List<String> l = m.get(hash);
                l.add(s);
            }
        }
        return res;
    }

    long getHash(String s) {
        long hash = 1;
        for (int i =0; i < s.length(); i++) {
            hash *= primes[s.charAt(i) - 'a'];
        }
        return hash;
    }
}
