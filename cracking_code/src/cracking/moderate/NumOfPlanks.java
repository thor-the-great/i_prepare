package cracking.moderate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumOfPlanks {

    List<Integer> planks(int k, int longer, int shorter) {
        Set<Integer> set = new HashSet<>();
        calc(set, k, longer, shorter, 0, new HashSet<>());
        return new ArrayList<>(set);
    }

    void calc(Set<Integer> set, int k, int longer, int shorter, int total, Set<String> memo) {
        String key = k + " " + total;
        if (k == 0) {
            set.add(total);
            return;
        }
        if (memo.contains(key))
            return;
        calc(set, k -1, longer, shorter, total + longer, memo);
        calc(set, k -1, longer, shorter, total + shorter, memo);
        memo.add(key);
    }

    public static void main(String[] args) {
        NumOfPlanks obj = new NumOfPlanks();
        List<Integer> planks = obj.planks(200, 2, 7);
        for (int i : planks)
            System.out.print(i + ", ");
    }
}
