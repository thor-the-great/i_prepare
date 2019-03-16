package minesweeper;

import java.util.*;

public class MSGame {

    static final int MAX_SIZE = 10;

    void startGame() {
        Scanner s = new Scanner(System.in);
        int size = 0;
        while (size <= 0 || size > MAX_SIZE) {
            System.out.println("Enter board size : ");
            size = s.nextInt();
        }

        //Board board = new Board(size, size - 1);
        Board board = new Board(size, 1);
        board.show();

        while (!board.done && !board.hit) {
            int moveR = -1, moveC = -1;
            char moveType = ' ';
            System.out.println("Enter next move (row, column, - for try or x for marking it as bomb) : ");
            moveR = s.nextInt();
            moveC = s.nextInt();
            moveType = s.next().charAt(0);
            board.makeMove(moveR, moveC, moveType);
            board.show();
        }

        if (board.hit) {
            System.out.println("Boom, you've been killed!!!");
        }
        if (board.done)
            System.out.println("Well done, you won!");
    }

    public static void main(String[] args) {
        MSGame game = new MSGame();

        game.startGame();
    }

    class Board {
        int N;
        int numOfBombs;
        Cell[][] board;
        Random rand = new Random();
        boolean hit;
        boolean done;
        Set<Integer> bomsCells;

        Board(int N, int numOfBombs) {
            this.N = N;
            board = new Cell[N][N];
            this.numOfBombs = numOfBombs;
            init();
        }

        void makeMove(int moveR, int moveC, char type) {
            Cell nextCell = board[moveR][moveC];
            if (nextCell.played)
                System.out.println("Cell already played");

            if (type == 'x') {
                board[moveR][moveC].markedAsBomb = true;
                board[moveR][moveC].played = true;
                int idx = moveR * N + moveC;
                if (bomsCells.contains(idx)) {
                    bomsCells.remove(idx);
                    if (bomsCells.size() == 0)
                        done = true;
                }
                return;
            }

            if (type == '-') {
                if (nextCell.isMine) {
                    hit = true;
                    return;
                }
                else {
                    if (board[moveR][moveC].around == 0) {
                        //do BFS for empty cells
                        Queue<int[]> q = new LinkedList<>();
                        q.add(new int[]{moveR, moveC});
                        while (!q.isEmpty()) {
                            int[] cell = q.poll();
                            int r = cell[0];
                            int c = cell[1];
                            if (board[r][c].played)
                                continue;

                            board[r][c].played = true;
                            if (board[r][c].around != 0)
                                continue;

                            if (r > 0) {
                                q.add(new int[]{r -1, c});
                            }
                            if (r < N - 1) {
                                q.add(new int[]{r + 1, c});
                            }
                            if (c > 0) {
                                q.add(new int[]{r, c - 1});
                            }
                            if (c < N - 1) {
                                q.add(new int[]{r, c + 1});
                            }
                        }
                    } else {
                        board[moveR][moveC].played = true;
                    }
                }
            }


        }

        void init() {
            bomsCells = new HashSet();
            while(bomsCells.size() < numOfBombs) {
                int next = rand.nextInt(N*N);
                if (!bomsCells.contains(next))
                    bomsCells.add(next);
            }

            for (int r = 0; r <N; r++) {
                for (int c = 0; c < N; c++) {
                    int idx = r * N + c;
                    board[r][c] = new Cell(bomsCells.contains(idx));
                }
            }
            //fill the number of neighbours
            for (int r = 0; r <N; r++) {
                for (int c = 0; c < N; c++) {
                    Cell cell = board[r][c];
                    if (cell.isMine) {
                        for (int rr = r - 1; rr <= r + 1; rr++) {
                            for (int cc = c - 1; cc <= c + 1; cc++) {
                                if (rr >= 0 && rr < N && cc >= 0 && cc < N) {
                                    if (!board[rr][cc].isMine) {
                                        board[rr][cc].around++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        void show() {
            System.out.print("\n");
            for (int r = 0; r <N; r++) {
                for (int c = 0; c < N; c++) {
                    Cell cell = board[r][c];
                    if (!cell.played)
                        System.out.print(" . ");
                    else {
                        if (cell.markedAsBomb) {
                            System.out.print(" X ");
                        }
                        else {
                            if (cell.around == 0)
                                System.out.print("   ");
                            else
                                System.out.print(" " + cell.around + " ");
                        }
                    }
                }
                System.out.print("\n");
            }
            System.out.println("Bombs left : " + bomsCells.size());
        }
    }

    class Cell {
        boolean isMine;
        int around;
        boolean played;
        boolean markedAsBomb;

        Cell(boolean isMine) {
            this.isMine = isMine;
        }
    }
}
