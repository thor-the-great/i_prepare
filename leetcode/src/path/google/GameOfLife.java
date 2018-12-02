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
     * State changed in-place - we put new state as next 10-th digit - newstate*10 + oldstate. Then after turn we
     * replace old state with new state - newstate = state / 10
     * @param board
     */
    public void gameOfLife(int[][] board) {
        rows = board.length;
        if (rows == 0) return;
        cols = board[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c =0; c < cols; c++) {
                int around = countAround(r, c, board);
                if (around < 2 && board[r][c]%10 == 1)
                    board[r][c] = board[r][c];
                else if (around == 3 && board[r][c]%10 == 0)
                    board[r][c] = 10 + board[r][c];
                else if (around > 3 && board[r][c] == 1)
                    board[r][c] = board[r][c];
                else
                    board[r][c] = 10*board[r][c] + board[r][c];
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c =0; c < cols; c++) {
                board[r][c] = board[r][c] / 10;
            }
        }
    }

    int countAround(int r, int c, int[][] board) {
        int res = 0;
        if (r > 0) {
            res += board[r-1][c]%10;
            if (c > 0) {
                res+= board[r - 1][c - 1]%10;
            }
            if (c < cols - 1) {
                res += board[r - 1][c + 1]%10;
            }
        }
        if (r < rows - 1) {
            res += board[r+1][c]%10;
            if (c > 0) {
                res+= board[r + 1][c - 1]%10;
            }
            if (c < cols - 1)
                res += board[r + 1][c + 1]%10;
        }
        if (c > 0) {
            res+=board[r][c - 1]%10;
        }
        if (c < cols - 1) {
            res += board[r][c+1]%10;
        }
        return res;
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
