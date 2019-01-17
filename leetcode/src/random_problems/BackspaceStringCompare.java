package random_problems;

import java.util.Stack;

/**
 * 844. Backspace String Compare
 * Easy
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace
 * character.
 *
 * Example 1:
 *
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 * Example 2:
 *
 * Input: S = "ab##", T = "c#d#"
 * Output: true
 * Explanation: Both S and T become "".
 * Example 3:
 *
 * Input: S = "a##c", T = "#a#c"
 * Output: true
 * Explanation: Both S and T become "c".
 * Example 4:
 *
 * Input: S = "a#c", T = "b"
 * Output: false
 * Explanation: S becomes "c" while T becomes "b".
 * Note:
 *
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S and T only contain lowercase letters and '#' characters.
 * Follow up:
 *
 * Can you solve it in O(N) time and O(1) space?
 */
public class BackspaceStringCompare {
    /**
     * Idea - keep tracking of number of backspaces
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare(String S, String T) {
        int sI = S.length() - 1;
        int tI = T.length() - 1;

        while(true) {
            int backspcCount = 0;
            while (sI >= 0 && (backspcCount > 0 || S.charAt(sI) == '#')) {
                backspcCount += S.charAt(sI) == '#' ? 1 : -1;
                sI--;
            }
            backspcCount = 0;
            while (tI >= 0 && (backspcCount > 0 || T.charAt(tI) == '#')) {
                backspcCount += T.charAt(tI) == '#' ? 1 : -1;
                tI--;
            }
            if (sI >= 0 && tI >= 0 && S.charAt(sI) == T.charAt(tI)) {
                sI--;
                tI--;
            } else {
                return sI == -1 && tI == -1;
            }
        }
    }

    public boolean backspaceCompareTwoStacks(String S, String T) {
        Stack<Character> s1 = new Stack();
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (ch == '#') {
                if (!s1.isEmpty())
                    s1.pop();
            }
            else
                s1.push(ch);
        }

        Stack<Character> s2 = new Stack();
        for (int i = 0; i < T.length(); i++) {
            char ch = T.charAt(i);
            if (ch == '#') {
                if (!s2.isEmpty())
                    s2.pop();
            }
            else
                s2.push(ch);
        }

        if (s1.size() != s2.size())
            return false;

        while(!s1.isEmpty()) {
            if (s1.pop() != s2.pop())
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        BackspaceStringCompare obj = new BackspaceStringCompare();
        System.out.println(obj.backspaceCompare("abg##c","ad#f#c"));
    }
}
