import java.util.ArrayList;

public class MatrixSpiralTraversal {

  /**
   * going from top-left to top-right, then top-right bot-right, bot-right bot-left, bot-left top-left
   * @param arr
   * @return
   */
  public static ArrayList<Integer> findSpiral(int[][] arr) {
    ArrayList<Integer> res = new ArrayList<>();

    if (arr == null)
      return res;

    int r1 = 0, r2 = arr.length - 1;
    int c1 = 0, c2 = arr[0].length - 1;

    while (r1 <= r2 && c1 <= c2) {
      //top row
      for (int i = c1; i <= c2; i++) {
        res.add(arr[r1][i]);
      }
      //right col
      for (int i = r1 + 1; i <= r2; i++) {
        res.add(arr[i][c2]);
      }

      //this is special case to handle o
      if (r1 < r2 && c1 < c2) {
        //bot col
        for (int i = c2 - 1; i > c1; i--) {
          res.add(arr[r2][i]);
        }
        //left col
        for (int i = r2; i > r1; i--) {
          res.add(arr[i][c1]);
        }
      }
      r1++; r2--;
      c1++; c2--;
    }

    return res;
  }
}
