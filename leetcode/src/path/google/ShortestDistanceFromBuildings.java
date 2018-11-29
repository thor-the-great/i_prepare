package path.google;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 317. Shortest Distance from All Buildings
 *
 *
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
 * You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 *
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * Example:
 *
 * Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 *
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 *
 * Output: 7
 *
 * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
 *              the point (1,2) is an ideal empty land to build a house, as the total
 *              travel distance of 3+3+1=7 is minimal. So return 7.
 * Note:
 * There will be at least one building. If it is not possible to build such house according to the above rules, return
 * -1.
 */
public class ShortestDistanceFromBuildings {

    public int shortestDistance(int[][] grid) {
        int rows = grid.length;
        if (rows == 0) return 0;
        int cols = grid[0].length;
        if (cols == 0) return 0;
        int houseCount = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1)
                    houseCount++;
            }
        }

        int res = Integer.MAX_VALUE;

        int minHouseCount = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) {
                    int min = 0;
                    minHouseCount = 0;
                    boolean[][] visited = new boolean[rows][cols];
                    Cell start = new Cell(r, c, 0);
                    Queue<Cell> q = new LinkedList();
                    q.add(start);
                    while (!q.isEmpty()) {
                        Cell cell = q.poll();
                        if (!visited[cell.r][cell.c]) {
                            visited[cell.r][cell.c] = true;

                            if (grid[cell.r][cell.c] == 1) {
                                min += cell.d;
                                if (min > res)
                                    break;
                                minHouseCount++;
                                if (minHouseCount == houseCount)
                                    break;
                                continue;
                            }

                            //add cells to visit
                            if (cell.r > 0 && grid[cell.r - 1][cell.c] != 2)
                                q.add(new Cell(cell.r - 1, cell.c, cell.d + 1));

                            if (cell.r < rows - 1 && grid[cell.r + 1][cell.c] != 2)
                                q.add(new Cell(cell.r + 1, cell.c, cell.d + 1));

                            if (cell.c > 0 && grid[cell.r][cell.c - 1] != 2)
                                q.add(new Cell(cell.r, cell.c - 1, cell.d + 1));

                            if (cell.c < cols - 1 && grid[cell.r][cell.c + 1] != 2)
                                q.add(new Cell(cell.r, cell.c + 1, cell.d + 1));

                        }
                    }
                    if (minHouseCount == houseCount)
                        res = Math.min(res, min);
                }
            }
        }
        if (res == Integer.MAX_VALUE) return -1;
        else return res;
    }

    class Cell {
        int r, c;
        int d;

        Cell(int row, int col, int dist) {
            r = row;
            c = col;
            d = dist;
        }
    }

    public static void main(String[] args) {
        ShortestDistanceFromBuildings obj = new ShortestDistanceFromBuildings();
        System.out.println(obj.shortestDistance(new int[][] {
                {1,1,1,1,1,0},
                {0,0,0,0,0,1},
                {0,1,1,0,0,1},
                {1,0,0,1,0,1},
                {1,0,1,0,0,1},
                {1,0,0,0,0,1},
                {0,1,1,1,1,0}
        }));


        System.out.println(obj.shortestDistance(new int[][] {
                {1, 1},
                {0, 1}
        }));
        System.out.println(obj.shortestDistance(new int[][] {
                {1, 0, 1}
        }));
    }
}
