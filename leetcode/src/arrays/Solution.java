package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public int dominantIndex(int[] nums) {
        if (nums.length == 1)
            return 0;

        int largest = nums[0];
        //, nextLarge = Integer.MIN_VALUE;
        int largeIndex = 0;
        boolean isTwiceLarge = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > largest) {
                if (nums[i] >= largest*2)
                    isTwiceLarge = true;
                else
                    isTwiceLarge = false;
                largest = nums[i];
                largeIndex = i;
            } else {
                if (largest < nums[i]*2)
                    isTwiceLarge = false;
            }
        }
        if (!isTwiceLarge)
            return -1;
        else
            return largeIndex;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        System.out.println(obj.dominantIndex(new int[] {3, 4, 9, 1, 0}));

    }

    static String intArrayToString(int[] a) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i =0;i < a.length; i++) {
            sb.append(a[i]);
            if (i < a.length - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    static String intListToString(List<Integer> a) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i =0;i < a.size(); i++) {
            sb.append(a.get(i));
            if (i < a.size() - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

}
