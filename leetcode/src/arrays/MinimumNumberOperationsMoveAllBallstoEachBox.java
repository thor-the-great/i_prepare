package arrays;

/**
 * 
 * 1769. Minimum Number of Operations to Move All Balls to Each Box
Medium

You have n boxes. You are given a binary string boxes of length n, where boxes[i] is '0' if the ith box is empty, and '1' if it contains one ball.

In one operation, you can move one ball from a box to an adjacent box. Box i is adjacent to box j if abs(i - j) == 1. Note that after doing so, there may be more than one ball in some boxes.

Return an array answer of size n, where answer[i] is the minimum number of operations needed to move all the balls to the ith box.

Each answer[i] is calculated considering the initial state of the boxes.

 

Example 1:

Input: boxes = "110"
Output: [1,1,3]
Explanation: The answer for each box is as follows:
1) First box: you will have to move one ball from the second box to the first box in one operation.
2) Second box: you will have to move one ball from the first box to the second box in one operation.
3) Third box: you will have to move one ball from the first box to the third box in two operations, and move one ball from the second box to the third box in one operation.
Example 2:

Input: boxes = "001011"
Output: [11,8,5,4,3,4]
 

Constraints:

n == boxes.length
1 <= n <= 2000
boxes[i] is either '0' or '1'.

https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/
 */
public class MinimumNumberOperationsMoveAllBallstoEachBox {
    
    /**
     * We count operations exactly for 0th box. Also we keep left and right counters for number of 
     * boxes for the right and to the left of current box. 
     * On each next box to the right we:
     * - take operations from prev cell
     * - add for boxes to the left, it eaquals to number of boxes to the left (including previous cell)
     * - minus boxes to the right. we need to decrement the counter, but after
     */
    public int[] minOperations(String boxes) {
        int N = boxes.length();
        int leftCount = 0, rightCount = 0;
        
        int[] res = new int[N];
        int idx = 0;
        //fill res for index 0
        for (int i = 1; i < N; i++) {
            if (boxes.charAt(i) == '1') {
                rightCount++;
                res[idx] += i;
            }
        }
        //now moving from left to right
        while (++idx < N) {
            res[idx] = res[idx - 1];
            leftCount += (boxes.charAt(idx - 1) == '1' ? 1 : 0);
            res[idx] += (leftCount - rightCount);
            rightCount -= (boxes.charAt(idx) == '1' ? 1 : 0);
        }
        
        return res;
    }
}
