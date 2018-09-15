package cracking.moderate;

public class CheckTicTac {

    boolean checkBoard(char[][] board) {
        for (int r = 0; r < board.length; r++) {
            char ch = board[r][0];
            if (ch == ' ') continue;
            int count = 1;
            for (int c = 1; c < board[0].length; c++) {
                if (ch != board[r][c]) break;
                else count++;
            }
            if (count == board.length)
                return true;
        }

        for (int c = 0; c < board[0].length; c++) {
            char ch = board[0][c];
            if (ch == ' ') continue;
            int count = 1;
            for (int r = 1; r < board.length; r++) {
                if (ch != board[r][c]) break;
                else count++;
            }
            if (count == board.length)
                return true;
        }

        char ch = board[0][0];
        int count = 1;
        for (int i = 1; i < board[0].length; i++) {
            if (ch != board[i][i]) break;
            else count++;
        }
        if (count == board.length)
            return true;

        int n = board.length - 1;
        ch = board[n][0];
        count = 1;
        for (int i = 1; i < board[0].length; i++) {
            if (ch != board[n - i][i]) break;
            else count++;
        }
        if (count == board.length)
            return true;

        return false;
    }

    public static void main(String[] args) {
        char[][] board1 = null;
        CheckTicTac obj = new CheckTicTac();
        board1 = new char[][] {
                {'X', ' ', 'O'},
                {'X', 'X', 'O'},
                {'O', 'O', 'X'}
        };
        System.out.println(obj.checkBoard(board1));

        board1 = new char[][] {
                {'X', ' ', 'O'},
                {'X', 'O', 'O'},
                {'X', 'O', 'X'}
        };
        System.out.println(obj.checkBoard(board1));

        board1 = new char[][] {
                {'X', ' ', 'O'},
                {' ', 'O', 'O'},
                {'X', 'O', 'X'}
        };
        System.out.println(obj.checkBoard(board1));
    }
}
