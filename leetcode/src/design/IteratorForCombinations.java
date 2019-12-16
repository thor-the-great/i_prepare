package design;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1286. Iterator for Combination
 * Difficulty: Medium
 * Design an Iterator class, which has:
 *
 * A constructor that takes a string characters of sorted distinct lowercase English letters and a number
 * combinationLength as arguments.
 * A function next() that returns the next combination of length combinationLength in lexicographical order.
 * A function hasNext() that returns True if and only if there exists a next combination.
 *
 *
 * Example:
 *
 * CombinationIterator iterator = new CombinationIterator("abc", 2); // creates the iterator.
 *
 * iterator.next(); // returns "ab"
 * iterator.hasNext(); // returns true
 * iterator.next(); // returns "ac"
 * iterator.hasNext(); // returns true
 * iterator.next(); // returns "bc"
 * iterator.hasNext(); // returns false
 *
 *
 * Constraints:
 *
 * 1 <= combinationLength <= characters.length <= 15
 * There will be at most 10^4 function calls per test.
 * It's guaranteed that all calls of the function next are valid.
 */
class CombinationIterator {
    /**
     * Calculate all combinations upfront, put it to the Queue. The on each next poll from the queue
     * @param characters
     * @param combinationLength
     */
    Queue<String> q;
    String s;
    public CombinationIterator(String characters, int combinationLength) {
        q = new LinkedList();
        s = characters;
        helper("", 0, combinationLength);
    }

    void helper(String cur, int idx, int len) {
        if (len == 0) {
            q.add(cur);
        } else {
            for (int i = idx; i < s.length(); i++)
                helper(cur + s.charAt(i), i + 1, len - 1);
        }
    }

    public String next() {
        return q.poll();
    }

    public boolean hasNext() {
        return !q.isEmpty();
    }
}
