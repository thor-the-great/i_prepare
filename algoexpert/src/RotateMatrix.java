import utils.StringUtils;

public class RotateMatrix {

    public static void transposeMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return;

        int N = matrix.length;
        int layers = N / 2;

        for (int l = 0; l < layers; l++) {
            int first = l;
            int last = N - l - 1;

            for (int i = first; i < last; i++) {
                int off = i - first;
                //top saved
                int tmp = matrix[first][i];
                //left to top
                matrix[first][i] = matrix[last - off][first];
                //bottom to left
                matrix[last - off][first] = matrix[last - off][last - off];
                //right to bottom
                matrix[last - off][last - off] = matrix[i][last];
                //saved top - to right
                matrix[i][last] = tmp;
            }
        }

    }

    static void rotate(int[][] matrix, int tr, int lc, int br, int rc) {
        int tmp = matrix[tr][lc];
        //top row left col
        matrix[tr][lc] = matrix[br][lc];
        //bot row left col
        matrix[br][lc] = matrix[br][rc];

        matrix[br][rc] = matrix[tr][rc];

        matrix[tr][rc] = tmp;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        transposeMatrix(matrix);

        System.out.println(StringUtils.int2DArrayToString(matrix));
    }
}
