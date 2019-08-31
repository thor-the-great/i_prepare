import java.util.*;

public class SubarraysSumToZero {

    List<int[]> zeroSumSegments(int[] arr) {
        List<int[]> res = new ArrayList<>();
        if (arr == null || arr.length == 0)
            return res;

        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, 0);
        int cur = 0;

        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            if (m.containsKey(cur)) {
                res.add(new int[] {m.get(cur), i});
            } else {
                m.put(cur, i + 1);
            }
        }

        return  res;
    }

    public static void main(String[] args) {
        SubarraysSumToZero obj = new SubarraysSumToZero();
        List<int[]> res;

        res = obj.zeroSumSegments(new int[] { 1, 3, -3, 4, 2, -1, -1});
        for (int[] pairs: res) {
            System.out.println(Arrays.toString(pairs));
        }

        res = obj.zeroSumSegments(new int[] { 3, 4, 2, -3, -3, 1, 4, -5, 6});
        for (int[] pairs: res) {
            System.out.println(Arrays.toString(pairs));
        }
    }
}
