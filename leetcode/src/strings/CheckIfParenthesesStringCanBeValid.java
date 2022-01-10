package strings;

/**
 * 2116. Check if a Parentheses String Can Be Valid
Medium

A parentheses string is a non-empty string consisting only of '(' and ')'. It is valid if any of the following conditions is true:

It is ().
It can be written as AB (A concatenated with B), where A and B are valid parentheses strings.
It can be written as (A), where A is a valid parentheses string.
You are given a parentheses string s and a string locked, both of length n. locked is a binary string consisting only of '0's and '1's. For each index i of locked,

If locked[i] is '1', you cannot change s[i].
But if locked[i] is '0', you can change s[i] to either '(' or ')'.
Return true if you can make s a valid parentheses string. Otherwise, return false.

 

Example 1:


Input: s = "))()))", locked = "010100"
Output: true
Explanation: locked[1] == '1' and locked[3] == '1', so we cannot change s[1] or s[3].
We change s[0] and s[4] to '(' while leaving s[2] and s[5] unchanged to make s valid.
Example 2:

Input: s = "()()", locked = "0000"
Output: true
Explanation: We do not need to make any changes because s is already valid.
Example 3:

Input: s = ")", locked = "0"
Output: false
Explanation: locked permits us to change s[0]. 
Changing s[0] to either '(' or ')' will not make s valid.
 

Constraints:

n == s.length == locked.length
1 <= n <= 105
s[i] is either '(' or ')'.
locked[i] is either '0' or '1'.

https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/
 */
public class CheckIfParenthesesStringCanBeValid {
    /**
     * Ideas:
     * - number of chars in string must be even
     * - scan string from one edge to another, keep balance as count of sequential "(" and ")", and number of unlocked chars
     * - do 2 scans - left to right and right to left. On lToR start from looking at "(", on rToL look at ")"
     * - if anytime balance + unlocked goes below 0 - it's invalid situation, return false
     * - at the end if balance is positive and is below number of unlocked - string can be balanced
     * 
     * O(n) time
     * O(1) space
     */
    public boolean canBeValid(String s, String locked) {
        int N = s.length();
        if (N % 2 == 1) {
            return false;
        }
        
        int balanceLeftToRight = 0, unlockedLeftToRight = 0;
        int balanceRightToLeft = 0, unlockedRightToLeft = 0;
        //checking left to right, "(" +1, ")" -1
        for (int i = 0; i < N && balanceLeftToRight + unlockedLeftToRight >= 0 && balanceRightToLeft + unlockedRightToLeft >= 0; i++) {
            if (locked.charAt(i) == '0') {
                unlockedLeftToRight++;
            } else {
                if (s.charAt(i) == '(') {
                    balanceLeftToRight++;
                } else {
                    balanceLeftToRight--;
                }
            }
            int rightToLeftIdx = N - i - 1;
            if (locked.charAt(rightToLeftIdx) == '0') {
                unlockedRightToLeft++;
            } else {
                if (s.charAt(rightToLeftIdx) == ')') {
                    balanceRightToLeft++;
                } else {
                    balanceRightToLeft--;
                }
            }
        }
        if (Math.abs(balanceLeftToRight) > unlockedLeftToRight || Math.abs(balanceRightToLeft) > unlockedRightToLeft) {
            return false;
        }
        return true;
    }
}
