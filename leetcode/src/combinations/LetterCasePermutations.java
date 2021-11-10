package combinations;

import java.util.ArrayList;
import java.util.List;

/**
 * 784. Letter Case Permutation
Medium

Given a string s, we can transform every letter individually to be lowercase or uppercase to create another string.

Return a list of all possible strings we could create. You can return the output in any order.

 

Example 1:

Input: s = "a1b2"
Output: ["a1b2","a1B2","A1b2","A1B2"]
Example 2:

Input: s = "3z4"
Output: ["3z4","3Z4"]
Example 3:

Input: s = "12345"
Output: ["12345"]
Example 4:

Input: s = "0"
Output: ["0"]
 

Constraints:

s will be a string with length between 1 and 12.
s will consist only of letters or digits.

 * https://leetcode.com/problems/letter-case-permutation/
 */
public class LetterCasePermutations {

    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList();
        helper(res, s.toCharArray(), 0);
        return res;
    }
    
    void helper(List<String> res, char[] cur, int idx) {
        //base case
        if (idx == cur.length) {
            res.add(new String(cur));
            return;
        }
        //handle char at idx position - leave it as it is for non-letter and flip to 
        //upper and lower cases for letter one
        if (!Character.isLetter(cur[idx])) {
            helper(res, cur, idx + 1);
        } else {
            cur[idx] = Character.toLowerCase(cur[idx]);
            helper(res, cur, idx + 1);
            cur[idx] = Character.toUpperCase(cur[idx]);
            helper(res, cur, idx + 1);
        }
    }
}
