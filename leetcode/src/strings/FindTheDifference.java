package strings;

/**
 * 389. Find the Difference
Easy

Given two strings s and t which consist of only lowercase letters.

String t is generated by random shuffling string s and then add one more letter at a random position.

Find the letter that was added in t.

Example:

Input:
s = "abcd"
t = "abcde"

Output:e

Explanation:'e' is the letter that was added.
 * 
 * https://leetcode.com/problems/find-the-difference/
 */
public class FindTheDifference {

    /**
     * Scan string s and save counts to array. Scan string t and decrement counts. At some point the count 
     * will became -1 - this will be our answer
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        int[] counts = new int[26];
        for (char ch : s.toCharArray()) {
            ++counts[ch - 'a'];
        }
        for (char ch : t.toCharArray()) {
            --counts[ch - 'a'];
            if (counts[ch - 'a'] < 0)
                return ch;
        }
        return ' ';
    }
}