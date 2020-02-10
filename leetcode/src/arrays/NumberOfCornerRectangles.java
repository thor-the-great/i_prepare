package arrays;

public class NumberOfCornerRectangles {

    /**
     * for every row the number of rectangles depends on a number of the rectangles in a same columns but in a
     * higher rows:
     * 0, 1 ,0 ,1, 0
     * 0, 1, 1, 1, 0
     * 0, 1, 1, 1, 0
     *
     * 0, 1, 0, 1, 0
     * 0, 1, 1, 2, 0 = 1
     * 0, 1, 2, 3, 0 = 1 + (1 + 2) = 4
     *
     * the algo is - we keep number of matches for [col1,col2], if we meet match in a next row - we do res+count and
     * then ++count. Count is stored in array
     * @param grid
     * @return
     */
    public int countCornerRectangles(int[][] grid) {
        //init array of column pairs, as per problem statement cols <= 200
        int[][] colPairs = new int[200][200];
        int cols = grid[0].length, res = 0;
        //for every row
        for (int[] row : grid) {
            //start loop on columns - need to check every pair of columns in the row
            for (int c1= 0; c1 < cols - 1; c1++) {
                if (row[c1] == 1) {
                    for (int c2 = c1+1; c2 < cols; c2++) {
                        //if both row[c1] and row[c2] == 1 - this is potentially a rectangle
                        if (row[c2] == 1) {
                            //get the previous value before incrementing - number of rectangles will be - 1 number of
                            //rows cause we need two rows to form first rectangle
                            res+=colPairs[c1][c2];
                            ++colPairs[c1][c2];
                        }
                    }
                }
            }
        }
        return res;
    }
}
