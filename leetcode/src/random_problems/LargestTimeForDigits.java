package random_problems;

import java.util.Arrays;

public class LargestTimeForDigits {

    public String largestTimeFromDigits(int[] A) {
        int mins_in_day = 60*24;
        int[] digits = new int[10];
        for (int i = 0; i < A.length; i++) {
            digits[A[i]] += 1;
        }
        for (int i = mins_in_day - 1; i >= 0; i--) {
            int[] dCopy = Arrays.copyOf(digits, digits.length);
            int time0 = (i / 60) / 10;
            if (!check(dCopy, time0)) continue;
            int time1 = (i / 60) % 10;
            if (!check(dCopy, time1)) continue;
            int time2 = (i % 60) / 10;
            if (!check(dCopy, time2)) continue;
            int time3 = (i % 60) % 10;
            if (!check(dCopy, time3)) continue;
            StringBuilder sb = new StringBuilder();
            sb.append(time0).append(time1).append(':').append(time2).append(time3);
            return sb.toString();
        }
        return "";
    }

    boolean check(int[] dCopy, int time0) {
        if (dCopy[time0] != 0) {
            dCopy[time0]-=1;
            return true;
        }
        return false;
    }
    
    /**
     * Just check every combination, it's O(n^4), but because n == 4 it's small 
     * @param A
     * @return
     */
    public String largestTimeFromDigitsSimpler(int[] A) {
        int max = -1;
        String res = "";
        for (int i = 0; i < 4; i++) {
            if (A[i] > 2)
                continue;
            for (int j = 0; j < 4; j++) {
                if (j == i || (A[i]*10 + A[j]) > 23) 
                    continue;
                for (int k = 0; k < 4; k++) {
                    if (k == i || k == j || A[k] > 5) 
                        continue;
                    for (int l = 0; l < 4; l++) {
                        if (l == i || l == j || l == k)
                            continue;
                        int minutes =  A[i]*1000 + A[j]*100 + A[k]*10 + A[l];
                        if (minutes > max) {
                            max = minutes;
                            StringBuilder sb = new StringBuilder();
                            sb.append(A[i]).append(A[j]).append(':').append(A[k]).append(A[l]);
                            res = sb.toString();
                        }
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LargestTimeForDigits obj = new LargestTimeForDigits();
        System.out.println(obj.largestTimeFromDigits(new int[] {1, 2, 3, 4}));
    }
}
