package path.google;

import java.util.HashMap;
import java.util.Map;

/**
 * 904. Fruit Into Baskets
 * Medium
 *
 *
 * In a row of trees, the i-th tree produces fruit with type tree[i].
 *
 * You start at any tree of your choice, then repeatedly perform the following steps:
 *
 * Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
 * Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
 * Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step
 * 2, then back to step 1, then step 2, and so on until you stop.
 *
 * You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one
 * type of fruit each.
 *
 * What is the total amount of fruit you can collect with this procedure?
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,1]
 * Output: 3
 * Explanation: We can collect [1,2,1].
 * Example 2:
 *
 * Input: [0,1,2,2]
 * Output: 3
 * Explanation: We can collect [1,2,2].
 * If we started at the first tree, we would only collect [0, 1].
 * Example 3:
 *
 * Input: [1,2,3,2,2]
 * Output: 4
 * Explanation: We can collect [2,3,2,2].
 * If we started at the first tree, we would only collect [1, 2].
 * Example 4:
 *
 * Input: [3,3,3,1,2,1,1,2,3,3,4]
 * Output: 5
 * Explanation: We can collect [1,2,1,1,2].
 * If we started at the first tree or the eighth tree, we would only collect 4 fruits.
 *
 *
 * Note:
 *
 * 1 <= tree.length <= 40000
 * 0 <= tree[i] < tree.length
 *
 */
public class TotalFruits {
    /**
     * idea is to use 2 pointers and kind of a sliding window. In Map we keep type and count of fruits of this type.
     * when size of map became > 2 - means we need to backtrack. For this case we have second pointer pointing to the
     * begging of the window. We start incrementing this second pointer unless size of map became 1. On every such step
     * we decrease counter or remove key at all if counter became 0.
     *
     * @param tree
     * @return
     */
    public int totalFruit(int[] tree) {
        Map<Integer, Integer> m = new HashMap();
        int N = tree.length;
        int max = 0;
        int p = 0;
        int res = 0;
        for(int i= 0; i < N; i++) {
            int type = tree[i];
            if (m.containsKey(type)) {
                m.put(type, m.get(type) + 1);
            } else {
                if (m.size() == 2) {
                    res = Math.max(res, max);
                    while(m.size() == 2) {
                        int t = tree[p];
                        int c =  m.get(t);
                        c--;
                        max--;
                        if (c == 0)
                            m.remove(t);
                        else
                            m.put(t, c);
                        p++;
                    }
                }
                m.put(type, 1);
            }
            max++;
        }
        res = Math.max(res, max);
        return res;
    }

    public static void main(String[] args) {
        TotalFruits obj = new TotalFruits();
        System.out.println(obj.totalFruit(new int[] {1,0,1,4,1,4,1,2,3}));//5
    }
}
