package random_problems;

import java.util.Arrays;

public class MinIncrementUniqueArray {
    public int minIncrementForUnique(int[] A) {
        int N = A.length;
        if (N == 0) return 0;
        Arrays.sort(A);
        int res = 0;
        int prev = A[0];

        for (int i = 1; i < N; i++) {
            //this is when elements are identical (or less) - violation of uniqueness. prev will
            //need to increment by 1, res is new prev - current array element
            if (A[i] <= prev) {
                prev = prev + 1;
                res += (prev - A[i]);
            }
            else {
                prev = A[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MinIncrementUniqueArray obj = new MinIncrementUniqueArray();

        System.out.println(obj.minIncrementForUnique(new int[] {2, 1, 3, 2, 1, 7}));//6

        System.out.println(obj.minIncrementForUnique(new int[] {1, 2, 2}));//1

        System.out.println(obj.minIncrementForUnique(new int[] {1, 2, 4}));//0

        System.out.println(obj.minIncrementForUnique(new int[] {1, 1, 2}));//2

        System.out.println(obj.minIncrementForUnique(new int[] {1,1,2,0}));//2

        System.out.println(obj.minIncrementForUnique(new int[] {1, 2, 1, 1}));//5

        System.out.println(obj.minIncrementForUnique(new int[] {2, 2, 1, 2}));//5
    }
}
