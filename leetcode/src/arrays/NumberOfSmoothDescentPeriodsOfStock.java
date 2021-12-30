package arrays;

/**
 * 2110. Number of Smooth Descent Periods of a Stock
Difficulty:Medium
You are given an integer array prices representing the daily price history of a stock, where prices[i] is the stock price on the ith day.

A smooth descent period of a stock consists of one or more contiguous days such that the price on each day is lower than the price on the preceding day by exactly 1. The first day of the period is exempted from this rule.

Return the number of smooth descent periods.

 

Example 1:

Input: prices = [3,2,1,4]
Output: 7
Explanation: There are 7 smooth descent periods:
[3], [2], [1], [4], [3,2], [2,1], and [3,2,1]
Note that a period with one day is a smooth descent period by the definition.
Example 2:

Input: prices = [8,6,7,7]
Output: 4
Explanation: There are 4 smooth descent periods: [8], [6], [7], and [7]
Note that [8,6] is not a smooth descent period as 8 - 6 ≠ 1.
Example 3:

Input: prices = [1]
Output: 1
Explanation: There is 1 smooth descent period: [1]
 

Constraints:

1 <= prices.length <= 105
1 <= prices[i] <= 105

https://leetcode.com/contest/weekly-contest-272/problems/number-of-smooth-descent-periods-of-a-stock/
 */
public class NumberOfSmoothDescentPeriodsOfStock {

    /**
     * for each smooth seq of length n the number of subseq is n*(n+1)/2
     * when we scan array and meet next number that is part of the seq for the overall result
     * then means we need to 
     * 1. add new updated value for n+1
     * 2. subtract the prev value for n
     * 
     * the different between seq of length n + 1 and n is 
     * (n+1)*(n+2)/2 - n*(n+1)/2 = n + 1
     * 
     * if case the number is not part of the seq we just do +1 and reset length of seq to 1
     */
    public long getDescentPeriods(int[] prices) {
        long res = 1;
        long cur = 1;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] == prices[i - 1] - 1) {
                cur++;
                res+=cur;
            } else {
                ++res;
                cur = 1;
            }
        }
        return res;
    }
}
