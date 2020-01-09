package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * 1042. Flower Planting With No Adjacent
 * Easy
 *
 * You have N gardens, labelled 1 to N.  In each garden, you want to plant one of 4 types of flowers.
 *
 * paths[i] = [x, y] describes the existence of a bidirectional path from garden x to garden y.
 *
 * Also, there is no garden that has more than 3 paths coming into or leaving it.
 *
 * Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have
 * different types of flowers.
 *
 * Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)-th garden.
 * The flower types are denoted 1, 2, 3, or 4.  It is guaranteed an answer exists.
 *
 *
 *
 * Example 1:
 *
 * Input: N = 3, paths = [[1,2],[2,3],[3,1]]
 * Output: [1,2,3]
 * Example 2:
 *
 * Input: N = 4, paths = [[1,2],[3,4]]
 * Output: [1,2,1,2]
 * Example 3:
 *
 * Input: N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
 * Output: [1,2,3,4]
 *
 *
 * Note:
 *
 * 1 <= N <= 10000
 * 0 <= paths.size <= 20000
 * No garden has 4 or more paths coming into or leaving it.
 * It is guaranteed an answer exists.
 */
public class FlowerPlantingWithNoAdjacent {

    public int[] gardenNoAdj(int N, int[][] paths) {
        //create graph as array of adjacent nodes
        List<Integer>[] g = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            g[i] = new ArrayList();
        }
        for (int[] p : paths) {
            g[p[0] - 1].add(p[1] - 1);
            g[p[1] - 1].add(p[0] - 1);
        }
        //now start picking color in a greedy manner - taken first that is not taken
        //by any of the adjacent nodes
        int[] colors = new int[N];
        for (int i = 0; i < N; i++) {
            //this node has a color
            if (colors[i] > 0)
                continue;
            //check all connected garden colors, 0 means it doesn't have one
            boolean[] takenColors = new boolean[5];
            for (int adj : g[i]) {
                takenColors[colors[adj]] = true;
            }
            //now all taken colors are marked so pick first un-taken for the current garden
            for (int c = 1; c <= 4; c++) {
                if (!takenColors[c]) {
                    colors[i] = c;
                    break;
                }
            }
        }
        return colors;
    }
}
