package cracking.recursion_dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Print possible combinations of queens that each queen isn't attacked by any other queen. Board size is
 * fixed = 8x8
 *
 */
public class QueensOnBoard {
    //idea - recursion with backtracking. Build recursion by following:
    // - base case - anaylse board that we have from previous step. If all queens are on board - position is possible,
    //   add it and return
    // - if still queens to place - sequentially add queen on next column in every possible row and do recursive call
    //   with this new board
    // - add backtracking part - after each add checking that position is valid and if not - prune this branch of
    //   decisions (don't check other queens) and go to next position of this one
    //  check for valid position - if col or row is the same of abs(col1 - col2) == abs(row1 - row2) - diagonal attack
    //  trick - board can be a 1-d array - col is index and val at index is row

    int _BOARD_SIZE = 8;

    List<List<Integer>> placeQueens() {
        List<List<Integer>> result = new ArrayList<>();
        place(result, new ArrayList<>());
        return result;
    }

    void place(List<List<Integer>> res, List<Integer> board) {
        if (board.size() == _BOARD_SIZE) {
            List<Integer> oneBoard = new ArrayList<>(board);
            res.add(oneBoard);
            return;
        }
        board.add(0);
        for (int i = 0; i < _BOARD_SIZE; i++) {
            board.set(board.size() - 1, i);
            if (isValid(board)) {
                place(res, board);
            }
        }
        board.remove(board.size() - 1);
    }

    boolean isValid(List<Integer> board) {
        int newCol = board.size() - 1;
        int newRow = board.get(newCol);
        for (int col = 0; col < newCol; col++) {
            int row = board.get(col);
            if (col == newCol
                    || newRow == row
                    || (Math.abs(col - newCol) == Math.abs(row - newRow))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        QueensOnBoard obj = new QueensOnBoard();
        List<List<Integer>> positions = obj.placeQueens();
        for(List<Integer> onePosition : positions) {
            System.out.print(" [ ");
            for (int place: onePosition ) {
                System.out.print(place + ", ");
            }
            System.out.println(" ]");
        }
    }
}
