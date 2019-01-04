package random_problems;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 341. Flatten Nested List Iterator
 * Medium
 *
 * Given a nested list of integers, implement an iterator to flatten it.
 *
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Example 1:
 *
 * Input: [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false,
 *              the order of elements returned by next should be: [1,1,2,1,1].
 * Example 2:
 *
 * Input: [1,[4,[6]]]
 * Output: [1,4,6]
 * Explanation: By calling next repeatedly until hasNext returns false,
 *              the order of elements returned by next should be: [1,4,6].
 */
public class NestedIterator implements Iterator<Integer> {

    Queue<Integer> q;

    public NestedIterator(List<NestedInteger> nestedList) {
        q = new LinkedList();
        if (nestedList != null) {
            for (NestedInteger r : nestedList) {
                helper(r);
            }
        }
    }

    void helper(NestedInteger n) {
        if (n.isInteger()) {
            q.add(n.getInteger());
            return;
        }
        //if it's a list
        List<NestedInteger> l = n.getList();
        if (!l.isEmpty()) {
            for (NestedInteger nested : l) {
                helper(nested);
            }
        }
    }

    @Override
    public Integer next() {
        return q.poll();
    }

    @Override
    public boolean hasNext() {
        return !q.isEmpty();
    }
}

