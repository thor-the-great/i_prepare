package queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 1047. Remove All Adjacent Duplicates In String
 * Easy
 *
 * Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.
 *
 * We repeatedly make duplicate removals on S until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.
 *
 * Example 1:
 *
 * Input: "abbaca"
 * Output: "ca"
 * Explanation:
 * For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only
 * possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final
 * string is "ca".
 *
 * 1 <= S.length <= 20000
 * S consists only of English lowercase letters.
 */
public class RemoveAdjusentDuplicates {

    public String removeDuplicates(String S) {
        Deque<Character> deq = new LinkedList();
        //iterate over the chars in string
        for (char ch : S.toCharArray()) {
            //check if the last char we checked is equal to the current one
            //if so - don't add current and remove that previosuly added
            //else add char to the stack
            if (!deq.isEmpty() && deq.peekLast() == ch) {
                deq.pollLast();
            } else {
                deq.addLast(ch);
            }
        }
        //form the result string by polling from the stack in reversed order
        char[] strArr = new char[deq.size()];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = deq.pollFirst();
        }
        return new String(strArr);
    }
}
