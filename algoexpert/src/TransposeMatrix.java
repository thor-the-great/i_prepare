import java.util.Arrays;

/**
 * Transpose matrix
 */
public class TransposeMatrix {

  static void transpose(int[][] matrix) {
    int N = matrix.length;
    for (int i = 0; i < N; i++) {
      for (int j = i; j < N; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
      }
    }
  }


  public static void main(String[] args) {
    int[][] matrix;
    matrix = new int[][]{
        {1, 2, 3},
        {4, 5, 6,},
        {7, 8, 9}
    };

    transpose(matrix);

    for(int[] row : matrix) {
      System.out.println(Arrays.toString(row));
    }
  }
}
