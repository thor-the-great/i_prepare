package path.linkedin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NestedListWeightSumII {
    Map<Integer, Integer> sums = new HashMap();

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
