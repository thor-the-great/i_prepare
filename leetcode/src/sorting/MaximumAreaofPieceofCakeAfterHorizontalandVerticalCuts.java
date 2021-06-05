/**
 * 1465. Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts
Medium


Share
Given a rectangular cake with height h and width w, and two arrays of integers horizontalCuts and verticalCuts where horizontalCuts[i]
is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, verticalCuts[j] is the distance from the 
left of the rectangular cake to the jth vertical cut.

Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts 
and verticalCuts. Since the answer can be a huge number, return this modulo 10^9 + 7.

 

Example 1:



Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
Output: 4 
Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the 
cake, the green piece of cake has the maximum area.
Example 2:



Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
Output: 6
Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the 
cake, the green and yellow pieces of cake have the maximum area.
Example 3:

Input: h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
Output: 9
 

Constraints:

2 <= h, w <= 10^9
1 <= horizontalCuts.length < min(h, 10^5)
1 <= verticalCuts.length < min(w, 10^5)
1 <= horizontalCuts[i] < h
1 <= verticalCuts[i] < w
It is guaranteed that all elements in horizontalCuts are distinct.
It is guaranteed that all elements in verticalCuts are distinct.

https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/
 */
public class MaximumAreaofPieceofCakeAfterHorizontalandVerticalCuts {
    
    /**
     * Max cut will always be between max length parts from horizontal and vertical.
     * We need to find max for each of two cuts, this including 0 to el_0 and el_last - end.  
     */
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        
        int maxH = Math.max(horizontalCuts[0], h - horizontalCuts[horizontalCuts.length - 1]);
        int maxV = Math.max(verticalCuts[0], w - verticalCuts[verticalCuts.length - 1]);
        
        for (int i = 0; i < horizontalCuts.length - 1; i++) {
            maxH = Math.max(maxH, horizontalCuts[i + 1] - horizontalCuts[i]);
        }
        
        for (int i = 0; i < verticalCuts.length - 1; i++) {
            maxV = Math.max(maxV, verticalCuts[i + 1] - verticalCuts[i]);
        }
        int mod = 1_000_000_007;
        return (int)(((long) maxH * maxV) % mod);
    }
}
