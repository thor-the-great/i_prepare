package random_problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddToArrayFormInteger {

    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> res = new ArrayList();
        int aL = A.length - 1;
        while (K > 0 || aL >= 0) {
            if (aL >= 0)
                K += A[aL--];

            int next = K % 10;
            res.add(next);

            K = K / 10;
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        AddToArrayFormInteger obj = new AddToArrayFormInteger();
        List<Integer> res = obj.addToArrayForm(new int[] {2, 7, 5}, 49);
        res.forEach(d->System.out.print(d + ", "));
        System.out.print("\n");

        res = obj.addToArrayForm(new int[] {1, 2, 0, 0}, 34);
        res.forEach(d->System.out.print(d + ", "));
        System.out.print("\n");

        res = obj.addToArrayForm(new int[] {2, 1, 5}, 806);
        res.forEach(d->System.out.print(d + ", "));
        System.out.print("\n");

        res = obj.addToArrayForm(new int[] {9, 9, 9, 9}, 1);
        res.forEach(d->System.out.print(d + ", "));
        System.out.print("\n");
    }
}
