package arrays;

/**
 * 84. Largest Rectangle in Histogram
Hard

Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.

 

Example 1:


Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.
Example 2:


Input: heights = [2,4]
Output: 4
 

Constraints:

1 <= heights.length <= 105
0 <= heights[i] <= 104

https://leetcode.com/problems/largest-rectangle-in-histogram/
 */
public class LargestRectangleInHistogram {
    /**
     * If we know number of cells (or index of last one) with the height >= to the current one to it's left and to it's right then the number of 
     * cells such rectangle takes is the (toRight - toLeft - 1). Area is number of cell in rect * height
     * 
     * To know this number we do scan all items to the current left and then to it's right. We can do it smartly by checking next cell right and left
     * 
     * Then at the end we iterate over all indexes, find the max rect by keeping rolling (length of rect * height)
     */
    public int largestRectangleArea(int[] heights) {
        int N = heights.length;
        
        int[] toLeft = new int[N], toRight = new int[N];
        
        toLeft[0] = -1;
        for (int i = 1; i < N; i++) {
            int idx = i - 1;
            while (idx >= 0 && heights[idx] >= heights[i]) {
                idx = toLeft[idx];
            }
            toLeft[i] = idx;
        }
        
        toRight[N - 1] = N;
        for (int i = N - 2; i >= 0; i--) {
            int idx = i + 1;
            while (idx < N && heights[idx] >= heights[i]) {
                idx = toRight[idx];
            }
            toRight[i] = idx;
        }
        
        int res = 0;
        for (int i = 0; i < N; i++) {
            int curLen = toRight[i] - toLeft[i] - 1;
            res = Math.max(res, curLen * heights[i]);
        }
        
        return res;
    }
}
