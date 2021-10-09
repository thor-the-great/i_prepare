package bfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 1992. Find All Groups of Farmland
Medium

You are given a 0-indexed m x n binary matrix land where a 0 represents a hectare of forested land and a 1 represents a 
hectare of farmland.

To keep the land organized, there are designated rectangular areas of hectares that consist entirely of farmland. 
These rectangular areas are called groups. No two groups are adjacent, meaning farmland in one group is not 
four-directionally adjacent to another farmland in a different group.

land can be represented by a coordinate system where the top left corner of land is (0, 0) and the bottom right 
corner of land is (m-1, n-1). Find the coordinates of the top left and bottom right corner of each group of farmland. 
A group of farmland with a top left corner at (r1, c1) and a bottom right corner at (r2, c2) is represented by the 
4-length array [r1, c1, r2, c2].

Return a 2D array containing the 4-length arrays described above for each group of farmland in land. If there are 
no groups of farmland, return an empty array. You may return the answer in any order.

 

Example 1:

Input: land = [[1,0,0],[0,1,1],[0,1,1]]
Output: [[0,0,0,0],[1,1,2,2]]
Explanation:
The first group has a top left corner at land[0][0] and a bottom right corner at land[0][0].
The second group has a top left corner at land[1][1] and a bottom right corner at land[2][2].

Example 2:

Input: land = [[1,1],[1,1]]
Output: [[0,0,1,1]]
Explanation:
The first group has a top left corner at land[0][0] and a bottom right corner at land[1][1].

Example 3:

Input: land = [[0]]
Output: []
Explanation:
There are no groups of farmland.

 

Constraints:

    m == land.length
    n == land[i].length
    1 <= m, n <= 300
    land consists of only 0's and 1's.
    Groups of farmland are rectangular in shape.

    https://leetcode.com/problems/find-all-groups-of-farmland/

 */
public class FindAllGroupsofFarmland {
    /**
     * Do matrix traversal, when found the top left corner (beggining) of the area - draw vertical and horizontal lines to
     * get max row and max column. How to get corner - check if previous row and previous column is 0, or this is very left
     * top of the grid.
     * 
     * O(n*m) time, O(1) space
     * 
     * @param land
     * @return
     */
    public int[][] findFarmland(int[][] land) {
        List<int[]> res = new ArrayList();
        int rows = land.length, cols = land[0].length;        
       for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (land[r][c] == 1) {
                    //found land, checking if that is the top left corner
                    if ((r == 0 || land[r - 1][c] == 0) && (c == 0 || land[r][c - 1] == 0)) {
                        int rr = r, cc = c;
                        //goint to down left
                        while(rr + 1 < rows && land[rr + 1][c] == 1) {
                            rr++;
                        }
                        //going to left top
                        while(cc + 1 < cols && land[r][cc + 1] == 1) {
                            cc++;
                        }
                        res.add(new int[] {r, c , rr, cc});
                    }
                }
            }
        }
        return res.toArray(new int[0][]);
    }    
}
