package cracking.recursion_dp;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Board of different colors cells. Start from one point, change color of all points of the same original
 * color to a new passed color
 *
 */
public class FillPaint {

    void fillArea(int[][] board, int[] startPoint, int newColor) {
        int[] rowSteps = new int[] { -1, 0, 1, 0};
        int[] colSteps = new int[] {  0, 1, 0, -1};

        Set<Integer> visited = new HashSet();
        int rowMax = board.length;
        int colMax = board[0].length;
        Stack<Integer> stack = new Stack();
        int oldColor = board[startPoint[0]][startPoint[1]];
        stack.push(point2DToFlat(startPoint[0], startPoint[1], colMax));
        while (!stack.isEmpty()) {
            int flat = stack.pop();
            if (!visited.contains(flat)) {
                int[] point = pointFlatTo2D(flat, colMax);
                if (oldColor == board[point[0]][point[1]]){
                    board[point[0]][point[1]] = newColor;
                    visited.add(flat);
                    for (int i = 0; i < 4; i++) {
                        int[] newPoint = new int[]{point[0] + rowSteps[i], point[1] + colSteps[i]};
                        if (isValid(newPoint[0], newPoint[1], rowMax, colMax)) {
                            int newFlat = point2DToFlat(newPoint[0], newPoint[1], colMax);
                            if (!visited.contains(newFlat)) {
                                stack.push(newFlat);
                            }
                        }
                    }
                }
            }
        }
    }

    int point2DToFlat(int row, int col, int colMax) {
        return (row * colMax) + col;
    }

    int[] pointFlatTo2D(int flat, int colMax) {
        return new int[] {(flat/ colMax), (flat % colMax)};
    }

    boolean isValid(int row, int col, int rowMax, int colMax) {
        if (row < 0 || row >= rowMax || col < 0 || col >= colMax)
            return false;
        return true;
    }

    void printBoard(int[][] board){
        for(int row = 0; row < board.length; row++) {
            System.out.print("{ ");
            for (int col = 0; col < board[0].length; col++) {
                System.out.print(board[row][col] + ", ");
            }
            System.out.println(" }");
        }
    }

    public static void main(String[] args) {
        FillPaint obj = new FillPaint();
        int[][] board = new int[][] {
                {1, 1, 1, 1,},
                {1, 2, 2, 1},
                {1, 1, 2, 1}
        };

        /*int[][] board = new int[][] {
                {1, 1, 1},
                {1, 2, 1}
        };*/

        System.out.println("--- initial board ----");

        obj.printBoard(board);

        int[] point = new int[] {0, 0};
        int newColor = 3;
        obj.fillArea(board, point, newColor);

        System.out.println("--- re-painted board ----");
        obj.printBoard(board);
    }
}
