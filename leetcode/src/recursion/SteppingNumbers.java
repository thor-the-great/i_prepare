package recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1215. Stepping Numbers
 * Medium
 *
 * A Stepping Number is an integer such that all of its adjacent digits have an absolute difference of exactly 1.
 * For example, 321 is a Stepping Number while 421 is not.
 *
 * Given two integers low and high, find and return a sorted list of all the Stepping Numbers in the range
 * [low, high] inclusive.
 *
 * Example 1:
 *
 * Input: low = 0, high = 21
 * Output: [0,1,2,3,4,5,6,7,8,9,10,12,21]
 *
 * Constraints:
 *
 * 0 <= low <= high <= 2 * 10^9
 */
public class SteppingNumbers {

    List<Integer> res;

    /**
     * Backtracking
     * @param low
     * @param high
     * @return
     */
    public List<Integer> countSteppingNumbers(int low, int high) {
        res = new ArrayList();
        //starting from 0-9 interval
        for (int i = 0; i <= 9; i++) {
            helper(i, low, high);
        }
        //sort the result collection
        Collections.sort(res);
        return res;
    }

    void helper(long num, int low, int high) {
        //check if our number in bounds, add to result if it is
        if (num >= low && num <= high) {
            res.add((int) num);
        }
        //we reach 0 or out of high - we done with recursion, base case
        if (num == 0 || num > high)
            return;
        //get last digit of the current number
        long lastDigit = num % 10;
        //build increment and decrement variations
        long inc = num * 10 + lastDigit + 1, dec = inc - 2;
        //if last digit was 9 - we can build increment
        if (lastDigit == 9) {
            helper(dec, low, high);
            //same if it's 0 - we can't do decrement
        } else if (lastDigit == 0) {
            helper(inc, low, high);
        } else {
            //all other cases ok to both incerement and decrement
            helper(inc, low, high);
            helper(dec, low, high);
        }
    }
}
