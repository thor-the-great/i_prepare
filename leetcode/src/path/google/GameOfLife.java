package path.google;

import java.util.Arrays;

/**
 * Game of Life
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised
 * by the British mathematician John Horton Conway in 1970."
 *
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its
 * eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia
 * article):
 *
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state. The next state
 * is created by applying the above rules simultaneously to every cell in the current state, where births and deaths
 * occur simultaneously.
 *
 * Example:
 *
 * Input:
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * Output:
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 * Follow up:
 *
 * Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some
 * cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause
 * problems when the active area encroaches the border of the array. How would you address these problems?
 */
public class GameOfLife {

    int rows;
    int cols;

    /**
     * State changed in-place - we can save the new state of the cell as bits added to the original value.
     * Shift by 1 bit in enough - we have 0 or 1 only. Catch - to read the value from the original grid
     * we need now to get only lower bit - this can be achieved by %2 operation
     * @param board
     */
    static int[][] dir = new int[][] {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

    public void gameOfLife(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int l = 0, d = 0;
                for (int i = 0; i < dir.length; i++) {
                    int newR = r + dir[i][0];
                    int newC  = c + dir[i][1];
                    if (newR >= 0 && newR < rows && newC >= 0 && newC < cols) {
                        if (board[newR][newC] % 2 == 0)
                            ++d;
                        else
                            ++l;
                    }
                }
                int newState;
                if (board[r][c] == 1) {
                    newState = (l >= 2 && l <= 3) ? 1 : 0;
                } else {
                    newState = (l == 3) ? 1 : 0;
                }
                board[r][c] = board[r][c] + (newState<<1);
            }
        }
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                board[r][c] = (board[r][c]>>1);
            }
        }
    }

    public static void main(String[] args) {
        GameOfLife obj = new GameOfLife();
        int[][] board = new int[][] {
                {0,1,0},
                {0,0,1},
                {1,1,1},
                {0,0,0}
        };
        obj.gameOfLife(board);
        for(int[] row : board) {
            System.out.print("[");
            Arrays.stream(row).forEach(n-> System.out.print(n + " "));
            System.out.print("]\n");
        }
    }
}
