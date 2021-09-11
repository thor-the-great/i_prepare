package strings;

/**
 * 848. Shifting Letters
Medium

You are given a string s of lowercase English letters and an integer array shifts of the same length.

Call the shift() of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a').

    For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.

Now for each shifts[i] = x, we want to shift the first i + 1 letters of s, x times.

Return the final string after all such shifts to s are applied.

 

Example 1:

Input: s = "abc", shifts = [3,5,9]
Output: "rpl"
Explanation: We start with "abc".
After shifting the first 1 letters of s by 3, we have "dbc".
After shifting the first 2 letters of s by 5, we have "igc".
After shifting the first 3 letters of s by 9, we have "rpl", the answer.

Example 2:

Input: s = "aaa", shifts = [1,2,3]
Output: "gfd"

 

Constraints:

    1 <= s.length <= 105
    s consists of lowercase English letters.
    shifts.length == s.length
    0 <= shifts[i] <= 109

https://leetcode.com/problems/shifting-letters/
 */
public class ShiftingLetters {
    /**
     * If we count shifts from end to beginning of the string we can do
     * shifts[i] = shifts[i] + shifts[i + 1]
     * 
     * then each shift need to mod 26
     * 
     * O(n) time
     * O(n) space
     */
    public String shiftingLetters(String s, int[] shifts) {
        int N = shifts.length;
        char[] newStr = new char[N];
        for (int i = N - 1; i >= 0; i--) {
            if (i < N - 1) {
                shifts[i] = (shifts[i] + shifts[i + 1])%26;
            }
            newStr[i] = shiftOneChar(s.charAt(i), shifts[i]);
        }
        return new String(newStr);
    }
    
    char shiftOneChar(char ch, int s) {
        int idx = (ch - 'a' + s)%26;
        return (char)('a' + idx);
    }
}
