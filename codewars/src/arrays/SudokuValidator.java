package arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Sudoku Background
 * Sudoku is a game played on a 9x9 grid. The goal of the game is to fill all cells of the grid with digits from 1 to 9,
 * so that each column, each row, and each of the nine 3x3 sub-grids (also known as blocks) contain all of the digits
 * from 1 to 9.
 * (More info at: http://en.wikipedia.org/wiki/Sudoku)
 *
 * Sudoku Solution Validator
 * Write a function validSolution/ValidateSolution/valid_solution() that accepts a 2D array representing a Sudoku
 * board, and returns true if it is a valid solution, or false otherwise. The cells of the sudoku board may also
 * contain 0's, which will represent empty cells. Boards containing one or more zeroes are considered to be invalid
 * solutions.
 *
 * The board is always 9 cells by 9 cells, and every cell only contains integers from 0 to 9.
 *
 * Examples
 * validSolution([
 *   [5, 3, 4, 6, 7, 8, 9, 1, 2],
 *   [6, 7, 2, 1, 9, 5, 3, 4, 8],
 *   [1, 9, 8, 3, 4, 2, 5, 6, 7],
 *   [8, 5, 9, 7, 6, 1, 4, 2, 3],
 *   [4, 2, 6, 8, 5, 3, 7, 9, 1],
 *   [7, 1, 3, 9, 2, 4, 8, 5, 6],
 *   [9, 6, 1, 5, 3, 7, 2, 8, 4],
 *   [2, 8, 7, 4, 1, 9, 6, 3, 5],
 *   [3, 4, 5, 2, 8, 6, 1, 7, 9]
 * ]); // => true
 * validSolution([
 *   [5, 3, 4, 6, 7, 8, 9, 1, 2],
 *   [6, 7, 2, 1, 9, 0, 3, 4, 8],
 *   [1, 0, 0, 3, 4, 2, 5, 6, 0],
 *   [8, 5, 9, 7, 6, 1, 0, 2, 0],
 *   [4, 2, 6, 8, 5, 3, 7, 9, 1],
 *   [7, 1, 3, 9, 2, 4, 8, 5, 6],
 *   [9, 0, 1, 5, 3, 7, 2, 1, 4],
 *   [2, 8, 7, 4, 1, 9, 6, 3, 5],
 *   [3, 0, 0, 4, 8, 1, 1, 7, 9]
 * ]); // => false
 */
public class SudokuValidator {

    public static boolean check(int[][] sudoku) {
        //do your magic
        //check rows
        for (int r = 0; r < 9; r++) {
            boolean[] digits = new boolean[9];
            for (int c = 0; c < 9; c++) {
                if (sudoku[r][c] == 0 || digits[sudoku[r][c] - 1])
                    return false;
                digits[sudoku[r][c] - 1] = true;
            }
        }

        //check cols
        for (int c = 0; c < 9; c++) {
            boolean[] digits = new boolean[9];
            for (int r = 0; r < 9; r++) {
                if (digits[sudoku[r][c] - 1])
                    return false;
                digits[sudoku[r][c] - 1] = true;
            }
        }

        for (int r = 0; r < 9; r+=3) {
            for (int c = 0; c < 9; c+=3) {
                if (!checkSq(sudoku, r, c))
                    return false;
            }
        }

        return true;
    }

    static boolean checkSq(int[][] sudoku, int row, int col) {
        boolean[] digits = new boolean[9];
        for (int c = col; c < col + 3; c++) {
            for (int r = row; r < row + 3; r++) {
                if (digits[sudoku[r][c] - 1])
                    return false;
                digits[sudoku[r][c] - 1] = true;
            }
        }
        return true;
    }
}

class SudokuValidatorTest {
    @Test
    public void exampleTest() {
        int[][] sudoku = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
        assertEquals(true, SudokuValidator.check(sudoku));

        sudoku[0][0]++;
        sudoku[1][1]++;
        sudoku[0][1]--;
        sudoku[1][0]--;

        assertEquals(false, SudokuValidator.check(sudoku));

        sudoku[0][0]--;
        sudoku[1][1]--;
        sudoku[0][1]++;
        sudoku[1][0]++;

        sudoku[4][4] = 0;

        assertEquals(false, SudokuValidator.check(sudoku));
    }
}