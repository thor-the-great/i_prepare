package arrays;

/**
 * 531. Lonely Pixel I
 * Medium
 *
 * Given a picture consisting of black and white pixels, find the number of black lonely pixels.
 *
 * The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and
 * white pixels respectively.
 *
 * A black lonely pixel is character 'B' that located at a specific position where the same row
 * and same column don't have any other black pixels.
 *
 * Example:
 * Input:
 * [['W', 'W', 'B'],
 *  ['W', 'B', 'W'],
 *  ['B', 'W', 'W']]
 *
 * Output: 3
 * Explanation: All the three 'B's are black lonely pixels.
 * Note:
 * The range of width and height of the input 2D array is [1,500].
 */
public class LonelyPixel {

  /**
   * We keep counts for every row and col. Then iterate one more time and check cells for which
   * count[r] == count[c] == 1 and cell == 'B'
   * @param picture
   * @return
   */
  public int findLonelyPixel(char[][] picture) {
    int rows = picture.length, cols = picture[0].length;
    int[] rowCount = new int[rows]; int[] colCount = new int[cols];
    int res = 0;
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        if (picture[r][c] == 'B') {
          rowCount[r]++;
          colCount[c]++;
        }
      }
    }

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        if (picture[r][c] == 'B'
            && rowCount[r] == 1
            && colCount[c] == 1) {
          res++;
        }
      }
    }
    return res;
  }
}
