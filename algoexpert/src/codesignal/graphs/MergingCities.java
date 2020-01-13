package codesignal.graphs;

/**
 * Medium
 *
 * Codewriting
 *
 * 300
 *
 * Once upon a time, in a kingdom far, far away, there lived a King Byteasar VIII. The king went
 * down in history as a great warrior, since he managed to defeat a longtime enemy that had been
 * capturing the kingdom's cities for more than a century. When the war was over, most of the
 * cities were almost completely destroyed, so Byteasar decided to create new cities by merging
 * them.
 *
 * The king decided to merge each pair of cities cityi, cityi+1 for i = 0, 2, 4, ... if they were
 * connected by one of the two-way roads running through the kingdom.
 *
 * Initially, all information about the roads was stored in the roadRegister. Your task is to
 * return the roadRegister after the merging is performed, assuming that after merging the cities
 * re-indexation is done in such way that any city formed from cities a and b (where a < b)
 * receives index a, and all the cities with numbers i (where i > b) get numbers i - 1.
 *
 * Example
 *
 * For
 *
 * roadRegister = [
 *   [false, true,  true,  false, false, false, true ],
 *   [true,  false, true,  false, true,  false, false],
 *   [true,  true,  false, true,  false, false, true ],
 *   [false, false, true,  false, false, true,  true ],
 *   [false, true,  false, false, false, false, false],
 *   [false, false, false, true,  false, false, false],
 *   [true,  false, true,  true,  false, false, false]
 * ]
 * the output should be
 *
 * mergingCities(roadRegister) = [
 *   [false, true,  true,  false, true ],
 *   [true,  false, false, true,  true ],
 *   [true,  false, false, false, false],
 *   [false, true,  false, false, false],
 *   [true,  true,  false, false, false]
 * ]
 * Here's how the cities were merged:
 *
 *
 * Input/Output
 *
 * [execution time limit] 3 seconds (java)
 *
 * [input] array.array.boolean roadRegister
 *
 * Since cartography was not properly developed in the kingdom, the registers were used instead.
 * A register is stored as a square matrix, with its size equal to the number of cities in the
 * kingdom. If roadRegister[i][j] = true, then there is a road between the ith and the jth
 * cities; the road doesn't exist otherwise.
 *
 * It is guaranteed that there are no looping roads, i.e. roads that lead back to the same city
 * it originated from.
 *
 * Guaranteed constraints:
 * 1 ≤ roadRegister.length ≤ 100,
 * roadRegister[0].length = roadRegister.length,
 * roadRegister[i][j] = roadRegister[j][i], i ≠ j,
 * roadRegister[i][i] = false.
 *
 * [output] array.array.boolean
 *
 * The roadRegister after all the cities are merged.
 */
public class MergingCities {
    boolean[][] mergingCities(boolean[][] roadRegister) {
        boolean[] toMerge = new boolean[roadRegister.length];

        int count = 0;
        for (int i = 0; i < roadRegister.length; i+=2) {
            if (i + 1 < roadRegister.length
                && roadRegister[i][i + 1]) {
                toMerge[i] = true;
                ++count;
            }
        }

        if (count > 0) {
            //first connect all cities to the one we gonna merge into. After this will have
            //correct values in rows and cols, but old cities (need to be merged) will still
            //be in a grid
            for (int i = 0; i < roadRegister.length; i++) {
                if (toMerge[i]) {
                    for (int j = 0; j < roadRegister.length; j++) {
                        if (roadRegister[i + 1][j] && i != j) {
                            roadRegister[i][j] = true;
                        }
                        if (roadRegister[j][i + 1] && i != j) {
                            roadRegister[j][i] = true;
                        }
                    }
                }
            }

            //then remove unused rows and cols
            boolean[][] newR = new boolean[roadRegister.length - count][roadRegister.length - count];
            int rr = 0, cc;
            for (int r = 0; r < roadRegister.length; r++) {
                if (r == 0 || !toMerge[r - 1]) {
                    cc = 0;
                    for (int c = 0; c < roadRegister.length; c++) {
                        if (c == 0 || !toMerge[c - 1]) {
                            newR[rr][cc++] = roadRegister[r][c];
                        }
                    }
                    ++rr;
                }
            }
            roadRegister = newR;
        }
        return roadRegister;
    }
}
