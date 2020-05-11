package random_problems;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 733. Flood Fill
 * Easy
 * An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0
 * to 65535).
 *
 * Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value
 * newColor, "flood fill" the image.
 *
 * To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting
 * pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also
 * with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with
 * the newColor.
 *
 * At the end, return the modified image.
 *
 * Example 1:
 * Input:
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation:
 * From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
 * by a path of the same color as the starting pixel are colored with the new color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally connected
 * to the starting pixel.
 * Note:
 *
 * The length of image and image[0] will be in the range [1, 50].
 * The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
 * The value of each color in image[i][j] and newColor will be an integer in [0, 65535].
 */
public class FloodFillColorReplace {

    int[][] image;
    boolean[][] visited;
    int newColor;
    int replaceColor;

    /**
     * Just do the dfs with visited array.
     * @param image
     * @param sr
     * @param sc
     * @param newColor
     * @return
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        this.image = image;
        visited = new boolean[image.length][image[0].length];
        this.newColor = newColor;
        this.replaceColor = image[sr][sc];
        if (newColor != replaceColor)
            dfs(sr, sc);
        return this.image;
    }

    void dfs(int r, int c) {
        if (!visited[r][c] && image[r][c] == replaceColor) {
            visited[r][c] = true;
            image[r][c] = newColor;
            if (r > 0)
                dfs(r - 1, c);
            if (c > 0)
                dfs(r, c - 1);
            if (r < image.length - 1)
                dfs(r + 1, c);
            if (c < image[0].length - 1)
                dfs(r, c + 1);
        }
    }

    public int[][] floodFillBFSIterative(int[][] image, int sr, int sc, int newColor) {
        int rows = image.length;
        if (rows == 0)
            return image;
        int cols = image[0].length;
        int oldColor = image[sr][sc];
        if (oldColor == newColor)
            return image;


        Queue<Integer> q = new LinkedList();
        q.add(sr*100 + sc);

        while(!q.isEmpty()) {
            int point = q.poll();
            int pc = point % 100, pr = point / 100;
            if (image[pr][pc] != oldColor)
                continue;
            image[pr][pc] = newColor;

            if (pr - 1 >= 0 && image[pr - 1][pc] == oldColor) {
                q.add((pr - 1)*100 + pc);
            }
            if (pc - 1 >= 0 && image[pr][pc - 1] == oldColor) {
                q.add(pr*100 + pc - 1);
            }
            if (pr + 1 < rows && image[pr + 1][pc] == oldColor) {
                q.add((pr + 1)*100 + pc);
            }
            if (pc + 1 < cols && image[pr][pc + 1] == oldColor) {
                q.add(pr*100 + pc + 1);
            }
        }

        return image;
    }
}
