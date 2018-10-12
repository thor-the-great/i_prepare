package amazon;

import java.util.*;

// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    int removeObstacle(int numRows, int numColumns, List<List<Integer>> lot)
    {
        return bfsBased(numRows, numColumns, lot);
        //return submitted(numRows, numColumns, lot);
    }
    // METHOD SIGNATURE ENDS

    int bfsBased(int numRows, int numColumns, List<List<Integer>> lot) {
        boolean[][] visited = new boolean[numRows][numColumns];
        Queue<Cell> q = new LinkedList();
        q.add(new Cell(0, 0, 0));
        while(!q.isEmpty()) {
            Cell c = q.poll();
            if (lot.get(c.row).get(c.col) == 9)
                return c.dist;
            visited[c.row][c.col] = true;
            //check that it's valid
            for (int i = 0; i < dir.length; i++) {
                int[] currentStep = dir[i];
                int nextRow = c.row + currentStep[0];
                int nextCol = c.col + currentStep[1];
                if (nextRow >= 0 && nextRow < numRows && nextCol >= 0 && nextCol < numColumns
                        && !visited[nextRow][nextCol]
                        && lot.get(nextRow).get(nextCol) != 0) {
                    Cell nextCell = new Cell(nextRow, nextCol, c.dist + 1);
                    q.add(nextCell);
                }
            }
        }
        return -1;
    }

    class Cell {
        int row, col, dist;

        Cell (int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    int submitted(int numRows, int numColumns, List<List<Integer>> lot) {
        // WRITE YOUR CODE HERE
        boolean[][] visited = new boolean[numRows][numColumns];
        visited[0][0] = true;
        int numOfSteps = traverseHelper(lot, 0, 0, 0, numRows, numColumns, visited);
        return numOfSteps;
    }

    int traverseHelper(List<List<Integer>> lot, int row, int col, int numOfSteps,
                       int numRows, int numColumns, boolean[][] visited) {
        if (lot.get(row).get(col) == 9) {
            return numOfSteps + 1;
        }
        int minSteps = -1;
        for (int i = 0; i < dir.length; i ++) {
            int[] currentStep = dir[i];
            int nextRow = row + currentStep[0];
            int nextCol = col + currentStep[1];
            if (isValid(lot, nextRow, nextCol, numRows, numColumns, visited)) {
                visited[nextRow][nextCol] = true;
                int steps = traverseHelper(lot, nextRow, nextCol, numOfSteps + 1, numRows, numColumns, visited);
                if (steps == -1)
                    continue;
                if (minSteps == -1 || steps < minSteps)
                    minSteps = steps;
                visited[nextRow][nextCol] = false;
            }
        }
        return minSteps;
    }

    boolean isValid(List<List<Integer>> lot, int row, int col, int numRows, int numColumns, boolean[][] visited) {
        if (row >= 0 && row < numRows && col >= 0 && col < numColumns
                && !visited[row][col]
                && lot.get(row).get(col) != 0)
            return true;
        return false;
    }

    int[][] dir = new int[][] {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    public static void main(String[] args) {
        Solution obj = new Solution();
        List<Integer> row1 = Arrays.asList(new Integer[] {1, 0, 1});
        List<Integer> row2 = Arrays.asList(new Integer[] {1, 0, 1});
        List<Integer> row3 = Arrays.asList(new Integer[] {1, 9, 1});
        List<List<Integer>> grid = new ArrayList<>();
        grid.add(row1);
        grid.add(row2);
        grid.add(row3);
        System.out.println(obj.removeObstacle(3, 3, grid));

        row1 = Arrays.asList(new Integer[] {1, 0, 0, 1});
        row2 = Arrays.asList(new Integer[] {1, 0, 1, 9});
        row3 = Arrays.asList(new Integer[] {1, 1, 1, 1});
        grid = new ArrayList<>();
        grid.add(row1);
        grid.add(row2);
        grid.add(row3);
        System.out.println(obj.removeObstacle(3, 4, grid));
    }
}