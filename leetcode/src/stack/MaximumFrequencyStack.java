package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 895. Maximum Frequency Stack
 * Hard
 *
 * Implement FreqStack, a class which simulates the operation of a stack-like data structure.
 *
 * FreqStack has two functions:
 *
 *     push(int x), which pushes an integer x onto the stack.
 *     pop(), which removes and returns the most frequent element in the stack.
 *         If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.
 *
 *
 *
 * Example 1:
 *
 * Input:
 * ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
 * [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
 * Output: [null,null,null,null,null,null,null,5,7,5,4]
 * Explanation:
 * After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:
 *
 * pop() -> returns 5, as 5 is the most frequent.
 * The stack becomes [5,7,5,7,4].
 *
 * pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
 * The stack becomes [5,7,5,4].
 *
 * pop() -> returns 5.
 * The stack becomes [5,7,4].
 *
 * pop() -> returns 4.
 * The stack becomes [5,7].
 *
 *
 *
 * Note:
 *
 *     Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
 *     It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
 *     The total number of FreqStack.push calls will not exceed 10000 in a single test case.
 *     The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
 *     The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.
 *
 * https://leetcode.com/problems/maximum-frequency-stack/
 */

/**
 * Solution : keep 2 maps:
 * map1  number -> frequency of the number
 * map2  frequency -> stack of numbers with that frequency in order of occurrences (LIFO)
 * and the max freq that is calculated as max between previous ma value and the new freq of the recently added number
 *
 * when add number - get the number freq, increment it. For updated freq - get the stack of numbers with that freq and
 * push this number. update the max freq
 * on pop - get the stack by maxFreq from map2. pop the number from that stack, update that number freq, update the max
 * freq if needed
 *
 * O(1) time - all operations are O(1)
 * O(N) space as overall the numbers of elements in each map is N in worst case
 */
public class MaximumFrequencyStack {

    Map<Integer, Integer> freq = new HashMap();
    Map<Integer, Stack<Integer>> stackOfSameFreq = new HashMap();
    int maxFreq = 0;

    public MaximumFrequencyStack() {
    }

    public void push(int x) {
        freq.put(x, freq.getOrDefault(x, 0) + 1);
        int newFreq = freq.get(x);
        Stack stack;
        if (!stackOfSameFreq.containsKey(newFreq)) {
            stack = new Stack();
            stackOfSameFreq.put(newFreq, stack);
        } else {
            stack = stackOfSameFreq.get(newFreq);
        }
        stack.push(x);
        maxFreq = Math.max(maxFreq, newFreq);
    }

    public int pop() {
        Stack<Integer> stack = stackOfSameFreq.get(maxFreq);
        int res = stack.pop();
        if (stack.isEmpty()) {
            stackOfSameFreq.remove(maxFreq);
            freq.put(res, --maxFreq);
        } else {
            freq.put(res, maxFreq - 1);
        }

        return res;
    }
}
