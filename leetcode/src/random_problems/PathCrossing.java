package random_problems;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/path-crossing/
 *
 * 1496. Path Crossing
 *
 * Difficulty:Easy
 * Given a string path, where path[i] = 'N', 'S', 'E' or 'W', each representing moving one unit north, south, east, or west, respectively. You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.
 *
 * Return True if the path crosses itself at any point, that is, if at any time you are on a location you've previously visited. Return False otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: path = "NES"
 * Output: false
 * Explanation: Notice that the path doesn't cross any point more than once.
 * Example 2:
 *
 *
 *
 * Input: path = "NESWW"
 * Output: true
 * Explanation: Notice that the path visits the origin twice.
 *
 *
 * Constraints:
 *
 * 1 <= path.length <= 10^4
 * path will only consist of characters in {'N', 'S', 'E', 'W}
 */
public class PathCrossing {

  /**
   * Save visited coordinates to the set, check every time when visit next one if we hve been there
   * before
   * @param path
   * @return
   */
  public boolean isPathCrossing(String path) {
    Set visited = new HashSet();
    int x = 10_000, y = 10_000;
    visited.add(coordinate(x, y));

    for (char ch : path.toCharArray()) {
      if (ch == 'N')
        ++y;
      else if (ch == 'S')
        --y;
      else if (ch == 'E')
        ++x;
      else
        --x;

      int nextCoordinate = coordinate(x, y);
      if (visited.contains(nextCoordinate))
        return true;
      else
        visited.add(nextCoordinate);
    }

    return false;
  }

  int coordinate(int x, int y) {
    return (x<<16) + y;
  }
}
