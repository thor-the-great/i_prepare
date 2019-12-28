package path.linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NestedListWeightSumII {
    Map<Integer, Integer> sums = new HashMap();

    /**
     * Idea - sum numbers at each level as unweighted and keep adding them to the list as weighted. Then continue
     * iterating, so the numbers at the lowest level summed once and other summed as many times as there are
     * levels
     * @param nestedList
     * @return
     */
    public int depthSumInverseIterative(List<NestedInteger> nestedList) {
        int w = 0, unw = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> list = new ArrayList();
            for (NestedInteger i : nestedList) {
                if (i.isInteger()) {
                    unw += i.getInteger();
                } else {
                    list.addAll(i.getList());
                }
            }
            w += unw;
            nestedList = list;
        }
        return w;
    }

    public int depthSumInverse(List<NestedInteger> nestedList) {
        helper(nestedList, 1);
        int maxDepth = sums.size();
        int adjDepth = maxDepth;
        int res = 0;
        for (int d = 1; d <= maxDepth; d++ ) {
            res += adjDepth * sums.getOrDefault(d, 0);
            adjDepth--;
        }
        return res;
    }

    void helper(List<NestedInteger> list, int depth) {
        int sum = 0;
        for (NestedInteger ni : list) {
            if (ni.isInteger())
                sum+=ni.getInteger();
            else
                helper(ni.getList(), depth + 1);
        }
        sums.put(depth, sums.getOrDefault(depth, 0) + sum );
    }
}
