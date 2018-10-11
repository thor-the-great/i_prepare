package cracking.moderate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairsOfSum {

    List<int[]> pairsOfSum(int[] nums, int k) {
        Map<Integer, Integer> compl = new HashMap();
        List<int[]> res = new ArrayList();
        for (int n : nums) {
            int compliment = k - n;
            if (compl.containsKey(compliment)) {
                int[] pair = new int[] {n, compliment};
                res.add(pair);
                if (compl.get(compliment) == 1)
                    compl.remove(compliment);
                else
                    compl.put(compliment, compl.get(compliment) - 1);
            } else {
                compl.put(n, 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[] {1, 5, 3, 8, 4, 3, 6, 2, 4};
        PairsOfSum obj = new PairsOfSum();
        List<int[]> res = obj.pairsOfSum(nums1, 7);
        for (int[] pair: res ) {
            System.out.print("[" + pair[0] +", " + pair[1] + "] ");
        }
        System.out.print("\n");

        res = obj.pairsOfSum(nums1, 9);
        for (int[] pair: res ) {
            System.out.print("[" + pair[0] +", " + pair[1] + "] ");
        }
        System.out.print("\n");
    }
}
