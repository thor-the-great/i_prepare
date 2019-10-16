/**
 * Copyright (c) 2019 Ariba, Inc. All rights reserved. Patents pending.
 */
package random_problems;

import java.util.ArrayList;
import java.util.List;

public class QueensThatCanAttackTheKing {
  List<List<Integer>> res;
  boolean[][] grid;
  int[] king;

  public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
    res = new ArrayList();
    grid = new boolean[8][8];
    this.king = king;
    //put queens on the board
    for (int[] q : queens) {
      grid[q[0]][q[1]] = true;
    }
    //check row
    helper(new int[] {0, -1});
    helper(new int[] {0, 1});
    //check col
    helper(new int[] {-1, 0});
    helper(new int[] {1, 0});
    //check diag 1
    helper(new int[] {-1, -1});
    helper(new int[] {1, 1});
    //check diag 2
    helper(new int[] {-1, 1});
    helper(new int[] {1, -1});

    return res;
  }

  void helper(int[] dir) {
    //save initial king's position
    int r = king[0], c = king[1];
    while (r + dir[0] >= 0
        && r + dir[0] < 8
        && c + dir[1] >= 0
        && c + dir[1] < 8) {
      //move one cell as per provided direction
      r += dir[0];
      c += dir[1];
      //if there is a queen - save it's position and breaks out of loop
      if (grid[r][c]) {
        List<Integer> q = new ArrayList();
        q.add(r); q.add(c);
        res.add(q);
        break;
      }
    }
  }
}
