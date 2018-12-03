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

    public static void main(String[] args) {
        LargestTimeForDigits obj = new LargestTimeForDigits();
        System.out.println(obj.largestTimeFromDigits(new int[] {1, 2, 3, 4}));
    }
}
