package utils;

import java.util.List;

public class StringUtils {
    public static String intArrayToString(int[] a) {
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

    public static String int2DArrayToString(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for(int row =0;row < arr.length; row++) {
            sb.append("{ ");
            for (int col = 0; col < arr[0].length; col++) {
                sb.append(arr[row][col]);
                if (col < arr[0].length - 1)
                    sb.append(", ");
            }
            sb.append(" }, \n");
        }
        sb.append("]");
        return sb.toString();
    }


    public static String listToString(List<Integer> a) {
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
