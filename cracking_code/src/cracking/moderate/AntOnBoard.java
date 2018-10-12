package cracking.moderate;

import java.util.HashSet;

public class AntOnBoard {

    public static void main(String[] args) {
        AntOnBoard board = new AntOnBoard();
        int k = 12000;
        for (int i = 0; i < k; i++)
            board.move();
        System.out.print(board.toString());
    }

    Ant ant = new Ant();
    HashSet<Position> whites = new HashSet();
    Position leftTop = new Position(0, 0);
    Position rightBot = new Position(0, 0);

    void move() {
        ant.turn(isWhite(ant.pos));
        flip(ant.pos);
        ant.move();
        ensureFit(ant.pos);
    }

    boolean isWhite(Position p) {
        return whites.contains(p);
    }

    boolean isWhite(int row, int col) {
        return whites.contains(new Position(row, col));
    }

    void ensureFit(Position p) {
        int r = p.row;
        int c = p.col;

        leftTop.row = Math.min(leftTop.row, r);
        leftTop.col = Math.min(leftTop.col, c);
        rightBot.row = Math.max(rightBot.row, r);
        rightBot.col = Math.max(rightBot.col, c);
    }

    void flip(Position p) {
        if (whites.contains(p))
            whites.remove(p);
        else {
            whites.add(p.clone());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r = leftTop.row; r <= rightBot.row; r++) {
            for (int c = leftTop.col; c <= rightBot.col; c++) {
                if (r == ant.pos.row && c == ant.pos.col)
                    sb.append(ant.orient);
                else if (isWhite(r, c)) {
                    sb.append("X");
                } else {
                    sb.append("_");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

class Ant {
    Position pos = new Position(0, 0);
    Orientation orient = Orientation.right;

    void turn(boolean clockwise) {
        orient = orient.getTurn(clockwise);
    }

    void move() {
        if (orient == Orientation.right) pos.col++;
        else if (orient == Orientation.down) pos.row++;
        else if (orient == Orientation.left) pos.col--;
        else pos.row--;
    }
}

class Position {
    int row ,col;

    Position(int r, int c) {
        row = r;
        col = c;
    }

    @Override
    public boolean equals(Object obj) {
        Position p = (Position) obj;
        return p.col == this.col && p.row == this.row;
    }

    @Override
    public int hashCode() {
        return (this.row*31)^this.col;
    }

    @Override
    protected Position clone() {
        return new Position(this.row, this.col);
    }
}

enum Orientation {
    right, left, up, down;

    public Orientation getTurn(boolean clockwise) {
        if (this == right) return clockwise ? down : up;
        else if (this == left) return clockwise ? up : down;
        else if (this == up) return clockwise ? right : left;
        else /*down*/ return clockwise ? left : right;
    }

    @Override
    public String toString() {
        if (this == left) return "<";
        else if (this == right) return ">";
        else if (this == up) return "^";
        else return "v";
    }
}