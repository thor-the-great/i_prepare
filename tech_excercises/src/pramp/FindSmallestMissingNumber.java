package pramp;

import java.util.Arrays;

public class FindSmallestMissingNumber {

    static int getDifferentNumber(int[] arr) {
        // your code goes here
        int N = arr.length;
        if (N == 0)
            return 0;
        int zeroIdx = -1;
        for (int i = 0; i < N; i++) {
            int idx = Math.abs(arr[i]);
            if (idx >= N)
                continue;

            if (idx == 0)
                zeroIdx = i;
            if (arr[idx] > 0)
                arr[idx] = -arr[idx];
        }

        System.out.println(Arrays.toString(arr));
        int res = N;
        for (int i = 0; i < N; i++) {
            int num = arr[i];
            if (num == 0 && zeroIdx == -1) {
                res = i;
                break;
            }
            if (num > 0) {
                res = i;
                break;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(getDifferentNumber(new int[] {1,3,0,2}));

        System.out.println(getDifferentNumber(new int[] {1,6,7,3,2,0}));
    }
}
