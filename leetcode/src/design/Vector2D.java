package design;

/**
 * 251. Flatten 2D Vector
 * Medium
 *
 * Design and implement an iterator to flatten a 2d vector. It should support the following
 * operations: next and hasNext.
 *
 *
 *
 * Example:
 *
 * Vector2D iterator = new Vector2D([[1,2],[3],[4]]);
 *
 * iterator.next(); // return 1
 * iterator.next(); // return 2
 * iterator.next(); // return 3
 * iterator.hasNext(); // return true
 * iterator.hasNext(); // return true
 * iterator.next(); // return 4
 * iterator.hasNext(); // return false
 *
 *
 * Notes:
 *
 * Please remember to RESET your class variables declared in Vector2D, as static/class variables
 * are persisted across multiple test cases. Please see here for more details.
 * You may assume that next() call will always be valid, that is, there will be at least a next
 * element in the 2d vector when next() is called.
 *
 *
 * Follow up:
 *
 * As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 *
 */
public class Vector2D {

  /**
   * Essentially we need only 2 variables to keep the state - one points to current row and second
   * to the column.
   * I've made the code a bit cleaner but slower (
   */

  int[][] v;
  int row, col;
  int N;

  public Vector2D(int[][] v) {
    this.v = v;
    row = 0;
    col = 0;
    N = v.length;
  }

  public int next() {
    int ret;
    if (col < v[row].length) {
      ret = v[row][col];
    } else {
      col = 0;
      row = nextValidRow(row);
      ret = v[row][col];
    }

    if (col + 1 < v[row].length)
      col++;
    else {
      col = 0;
      row = nextValidRow(row);
    }
    return ret;
  }

  public boolean hasNext() {
    if (row >= N) return false;
    if (col < v[row].length)
      return true;

    return (nextValidRow(row) < N);
  }

  private int nextValidRow(int r) {
    r++;
    while(r < N) {
      if (v[r].length > 0)
        return r;
      r++;
    }
    return r;
  }
}