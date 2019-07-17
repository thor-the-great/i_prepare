/**
 * Merge two sorted arrays into one sorted array
 */
public class MergeTwoSortedArrays {

  /**
   * Idea - create result array as empty one, then iterate over two arrays merging into result by
   * one element. When at least one is exhausted. Then put the rest of elements without processing
   * from one array that is still have elements
   * @param arrLeft
   * @param arrRight
   * @return
   */
  public static int[] merge(int[] arrLeft, int[] arrRight){
    int N1 = arrLeft.length;
    int N2 = arrRight.length;
    int[] res = new int[N1 + N2];

    int p1 = 0, p2 = 0, p = 0;

    while (p1 < N1 && p2 < N2) {
      int el1 = arrLeft[p1];
      int el2 = arrRight[p2];

      if (el1 < el2) {
        res[p++] = el1;
        p1++;
      } else {
        res[p++] = el2;
        p2++;
      }
    }

    while(p1 < N1) {
      res[p++] = arrLeft[p1++];
    }
    while(p2 < N2) {
      res[p++] = arrRight[p2++];
    }

    return res;
  }
}
