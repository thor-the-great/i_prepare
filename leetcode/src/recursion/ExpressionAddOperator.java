package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 282. Expression Add Operators
Hard

Given a string num that contains only digits and an integer target, return all possibilities to add the binary operators '+', '-', or '*' between the digits of num so that the resultant expression evaluates to the target value.

 

Example 1:

Input: num = "123", target = 6
Output: ["1*2*3","1+2+3"]

Example 2:

Input: num = "232", target = 8
Output: ["2*3+2","2+3*2"]

Example 3:

Input: num = "105", target = 5
Output: ["1*0+5","10-5"]

Example 4:

Input: num = "00", target = 0
Output: ["0*0","0+0","0-0"]

Example 5:

Input: num = "3456237490", target = 9191
Output: []

 

Constraints:

    1 <= num.length <= 10
    num consists of only digits.
    -231 <= target <= 231 - 1

https://leetcode.com/problems/expression-add-operators/
 */
public class ExpressionAddOperator {
    
    /**
     * Evaluate in recusrsive manner. For multiplication case it's tricky - need to substract from previous the 
     * multiplication part and add multiplier * current. 
     * few other tricks:
     * - handle leading '0'  - can't have multiple ones
     * - use long as we can get overflow with Integer
     * 
     */
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList();
        if (num == null || num.length() == 0)
            return res;
        
        helper(res, "", num, 0, target, 0, 0);
        return res;
    }
    
    void helper(List<String> res, String cur, String num, int idx, int target, long eval, long mult) {
        if (idx == num.length()) {
            if (target == eval) {
                res.add(cur);
            }
            return;
        }
        
        for (int i = idx; i < num.length(); i++) {
            if (i > idx && num.charAt(idx) == '0') {
                break;
            }
            long curNum = Long.parseLong(num.substring(idx, i + 1));
            
            if (idx == 0) {
                helper(res, cur + curNum, num, i + 1, target, curNum, curNum);
            } else {
                helper(res, cur + "+" + curNum, num, i + 1, target, eval + curNum, curNum);
                
                helper(res, cur + "-" + curNum, num, i + 1, target, eval - curNum, -curNum);
                
                helper(res, cur + "*" + curNum, num, i + 1, target, eval - mult + mult * curNum, mult * curNum);
            }
        }
    }
}