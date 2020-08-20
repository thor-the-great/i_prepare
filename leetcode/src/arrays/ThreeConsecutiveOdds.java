package arrays;

/**
 * 1550. Three Consecutive Odds
Easy

Given an integer array arr, return true if there are three consecutive odd numbers in the array. Otherwise, return false.
 

Example 1:

Input: arr = [2,6,4,1]
Output: false
Explanation: There are no three consecutive odds.
Example 2:

Input: arr = [1,2,34,3,4,5,7,23,12]
Output: true
Explanation: [5,7,23] are three consecutive odds.
 

Constraints:

1 <= arr.length <= 1000
1 <= arr[i] <= 1000

https://leetcode.com/problems/three-consecutive-odds/
 */
public class ThreeConsecutiveOdds {
    /**
     * Keep count of odd numbers, increment for each odd, reset to 0 for each even.
     * @param arr
     * @return
     */
    public boolean threeConsecutiveOdds(int[] arr) {
        int countOfOdds = 0;
        for (int n : arr) {
            if (n % 2 == 0) {
                countOfOdds = 0;
            } else {
                ++countOfOdds;
                if (countOfOdds >= 3) 
                    return true;
            }
        }
        return false;
    }
}