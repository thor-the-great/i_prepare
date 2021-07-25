package bits;

/**
 * 600. Non-negative Integers without Consecutive Ones
Hard

Given a positive integer n, return the number of the integers in the range [0, n] whose binary representations 
do not contain consecutive ones. 

Example 1:

Input: n = 5
Output: 5
Explanation:
Here are the non-negative integers <= 5 with their corresponding binary representations:
0 : 0
1 : 1
2 : 10
3 : 11
4 : 100
5 : 101
Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule. 

Example 2:

Input: n = 1
Output: 2

Example 3:

Input: n = 2
Output: 3

 

Constraints:

    1 <= n <= 109

https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/
 */
public class NonNegativeIntegerWithoutConsecutiveOnes {
    
    /**
     * Idea - for each i max bits the approx number of combinations are 
     * num[i - 1] + num[i - 2]
     * The catch is that in some cases we need to exclude some bits
     */
    public int findIntegers(int n) {
        int[] bits = new int[32];
        bits[0] = 1;
        bits[1] = 2;
        
        for (int i = 2; i < 32; i++) {
            bits[i] = bits[i - 1] + bits[i - 2];
        }
        
        int i = 30, sum = 0; 
        boolean prev_bit = false;
        while (i >= 0) {
            if ((n & (1 << i)) != 0) {
                sum+=bits[i];
                if (prev_bit) {
                    --sum;
                    break;
                }
                prev_bit = true;
            } else {
                prev_bit = false;
            }
            --i;
        }
        
        return sum + 1;
    }
}
