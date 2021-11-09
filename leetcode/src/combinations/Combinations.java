package combinations;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList();
        helper(res, new int[k], 1, n, 0);
        return res;
    }
    
    void helper(List<List<Integer>> res, int[] cur, int curDigit, int n, int idx) {
        //we have filled our array, unload it to resulting list and return
        if (idx == cur.length) {
            List<Integer> list = new ArrayList();
            res.add(list);
            for (int num : cur) {
                list.add(num);
            }
            return;
        }
        //from current number interate up to the n inclusive, add every next number to a sequence
        for (int i = curDigit; i <= n; i++) {
            cur[idx] = i;
            helper(res, cur, i + 1, n, idx +1);
        }
    }
}
