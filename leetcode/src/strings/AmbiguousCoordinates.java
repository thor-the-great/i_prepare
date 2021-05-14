/**
 * 816. Ambiguous Coordinates
Medium

We had some 2-dimensional coordinates, like "(1, 3)" or "(2, 0.5)".  Then, we removed all commas, decimal points, and spaces, and ended up with the string s.  Return a list of strings representing all possibilities for what our original coordinates could have been.

Our original representation never had extraneous zeroes, so we never started with numbers like "00", "0.0", "0.00", "1.0", "001", "00.01", or any other number that can be represented with less digits.  Also, a decimal point within a number never occurs without at least one digit occuring before it, so we never started with numbers like ".1".

The final answer list can be returned in any order.  Also note that all coordinates in the final answer have exactly one space between them (occurring after the comma.)

Example 1:
Input: s = "(123)"
Output: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
Example 2:
Input: s = "(00011)"
Output:  ["(0.001, 1)", "(0, 0.011)"]
Explanation: 
0.0, 00, 0001 or 00.01 are not allowed.
Example 3:
Input: s = "(0123)"
Output: ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]
Example 4:
Input: s = "(100)"
Output: [(10, 0)]
Explanation: 
1.0 is not allowed.
 

Note:

4 <= s.length <= 12.
s[0] = "(", s[s.length - 1] = ")", and the other elements in s are digits.
 * 
 * https://leetcode.com/problems/ambiguous-coordinates/
 */
public class AmbiguousCoordinates {
    /**
     * Idea - first we do all possible splis for two numbers based on initial string.
     * This gives us potentialy list of possible numbers from placing a dot.
     * After getting lists from two parts of string we do cartezian product of all elements of east list
     * 
     * Second part - how to split each one string for decimal dot. For that simple rules:
     * - cannot do anything if the first one is 0. Exception - if it's the only 0
     * - cannot do if number ends with 0.
     * 
     * O(n^4) time
     * O(n^2) for recursive calls + O(n^4) for storing answer
     */
    public List<String> ambiguousCoordinates(String s) {
        int N = s.length();
        List<String> res = new ArrayList();
        
        for (int i = 2; i < N - 1; i++) {
             for (String left : make(s, 1, i)) {
                 for (String right : make(s, i, N - 1)) {
                     res.add("(" + left + ", " + right + ")");
                 }
             }
        }
        return res;
    }
    
    public List<String> make(String S, int i, int j) {
        // Make on S.substring(i, j)
        List<String> ans = new ArrayList();
        for (int d = 1; d <= j-i; ++d) {
            String left = S.substring(i, i+d);
            String right = S.substring(i+d, j);
            if ((!left.startsWith("0") || left.equals("0"))
                    && !right.endsWith("0"))
                ans.add(left + (d < j-i ? "." : "") + right);
        }
        return ans;
    }
}
