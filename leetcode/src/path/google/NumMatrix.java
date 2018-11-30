package path.google;

class NumMatrix {
    int[][] m;
    int[][] colSum;
    int rows;
    int cols;

    public NumMatrix(int[][] matrix) {
        m = matrix;
        rows = matrix.length;
        cols = rows == 0 ? 0 : matrix[0].length;
        colSum = new int[rows][cols];
        for (int c = 0; c < cols; c++) {
            int s = 0;
            for (int r = rows - 1; r>=0; r--) {
                s+=m[r][c];
                colSum[r][c] = s;
            }
        }
    }

    public void update(int row, int col, int val) {
        int dif = val - m[row][col];
        m[row][col] = val;
        for (int r = 0; r <= row; r++) {
            colSum[r][col] += dif;
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int c = col1; c <= col2; c++) {
            sum += colSum[row1][c];
        }
        if (row2 <  rows - 1) {
            for (int c = col1; c <= col2; c++) {
                sum -= colSum[row2 + 1][c];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        NumMatrix obj = new NumMatrix(new int[][] {
                {3,0,1,4,2},
                {5,6,3,2,1},
                {1,2,0,1,5},
                {4,1,0,1,7},
                {1,0,3,0,5}
        });
        System.out.println(obj.sumRegion(2,1,4,3));
    }
}