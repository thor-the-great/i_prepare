package recursion;

import java.util.ArrayList;
import java.util.List;

public class TwentyFourGame {

    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList();
        for (int n : nums) {
            list.add((double) n);
        }

        return dfs(list);
    }

    boolean dfs(List<Double> list) {
        if (list.size() == 1) {
            return Math.abs(24.0 - list.get(0)) < 0.001;
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                List<Double> thisRound = getPerm(list.get(i), list.get(j));
                for (int k = 0; k < thisRound.size(); k++) {
                    List<Double> nextRound = new ArrayList();
                    nextRound.add(thisRound.get(k));
                    for (int kk = 0; kk < list.size(); kk++) {
                        if (kk != i && kk != j) {
                            nextRound.add(list.get(kk));
                        }
                    }

                    if (dfs(nextRound))
                        return true;
                }
            }
        }
        return false;
    }

    List<Double> getPerm(double a, double b) {
        List<Double> res = new ArrayList();
        res.add(a * b);
        res.add(a + b);
        res.add(a - b);
        res.add(b - a);
        res.add(a / b);
        res.add(b / a);
        return res;
    }
}
