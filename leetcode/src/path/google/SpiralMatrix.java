package path.google;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        int rows = matrix.length;
        if (rows == 0)
            return new ArrayList();
        int cols = matrix[0].length;
        List<Integer> res= new ArrayList(rows*cols);

        int r1 = 0, r2 = rows - 1;
        int c1 = 0, c2 = cols - 1;

        while (r1<=r2 && c1<=c2) {
            for (int c = c1; c <= c2; c++)
                res.add(matrix[r1][c]);
            for (int r = r1 + 1; r <= r2; r++)
                res.add(matrix[r][c2]);
            if (c2 > c1 && r2 > r1) {
                for (int c = c2 - 1; c > c1; c--)
                    res.add(matrix[r2][c]);
                for (int r = r2; r > r1; r--)
                    res.add(matrix[r][c1]);
            }
            c1++;
            c2--;
            r1++;
            r2--;
        }
        return res;
    }

    public static void main(String[] args) {
        SpiralMatrix obj = new SpiralMatrix();
        List<Integer> res = obj.spiralOrder(new int[][] {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 9 }
        });
        res.forEach(i->System.out.print(i +" "));
    }
}
