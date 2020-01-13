package arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TwoPluses {

    static class Plus {
        int r; int c; int rad;

        Plus(int rr, int cc, int rrad) {
            r = rr; c = cc; rad = rrad;
        }
    }

    // Complete the twoPluses function below.
    static int twoPluses(String[] grid)  {
        int rows = grid.length, cols = grid[0].length();
        List<Plus> l = new ArrayList();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r].charAt(c) == 'G') {
                    //start exploring
                    int ext = 0;
                    l.add(new Plus(r, c , 0));
                    while(true) {
                        ++ext;
                        if (((r - ext) >= 0 && grid[r - ext].charAt(c) == 'G')
                            && ((r + ext) < rows && grid[r + ext].charAt(c) == 'G')
                            && ((c - ext) >= 0 && grid[r].charAt(c - ext) == 'G')
                            && ((c + ext) < cols && grid[r].charAt(c + ext) == 'G')) {
                            l.add(new Plus(r, c , ext));
                        }
                        else
                            break;
                    }
                }
            }
        }
        if (l.isEmpty())
            return 0;
        Comparator<Plus> comp = (p1, p2) -> p2.rad - p1.rad;
        Collections.sort(l, comp);
        int res = 0;
        for (int i = 0; i < l.size(); i++) {
            Plus p1 = l.get(i);
            for (int j = i + 1; j < l.size(); j++) {
                Plus p2 = l.get(j);
                if (p1.r == p2.r && p1.c == p2.c)
                    continue;
                int d1 = Math.abs(p1.r - p2.r) - 1, d2 = Math.abs(p1.c - p2.c) - 1;
                if (d1 + d2 >= Math.max(p1.rad, p2.rad)
                    && (Math.min(d1, d2) >= Math.min(p1.rad, p2.rad)))
                {
                    res = Math.max(res,
                        ((4*p1.rad) + 1)*((4*p2.rad) + 1));
                }
            }
        }

        return res;
    }


    public static void main(String[] args) {
        TwoPluses obj = new TwoPluses();
        String[] grid;

        /*grid = new String[] {
            "GGGGGG",
            "GBBBGB",
            "GGGGGG",
            "GGBBGB",
            "GGGGGG"
        };

        System.out.println(TwoPluses.twoPluses(grid));
*/
        /*grid = new String[] {
            "BBGGBBGBBGBBG",
            "BBGGBBGBBGBBG",
            "GGGGGGGGGGGGG",
            "BBGGBBGBBGBBG",
            "GGGGGGGGGGGGG",
            "BBGGBBGBBGBBG",
            "BBGGBBGBBGBBG",
            "GGGGGGGGGGGGG",
            "BBGGBBGBBGBBG",
            "GGGGGGGGGGGGG",
            "BBGGBBGBBGBBG"
        };*/

        grid = new String[] {
            "GGGGGGGGGGGG",
            "GGGGGGGGGGGG",
            "BGBGGGBGBGBG",
            "BGBGGGBGBGBG",
            "GGGGGGGGGGGG",
            "GGGGGGGGGGGG",
            "GGGGGGGGGGGG",
            "GGGGGGGGGGGG",
            "BGBGGGBGBGBG",
            "BGBGGGBGBGBG",
            "BGBGGGBGBGBG",
            "BGBGGGBGBGBG",
            "GGGGGGGGGGGG",
            "GGGGGGGGGGGG"
        };

        System.out.println(TwoPluses.twoPluses(grid));
    }
}
