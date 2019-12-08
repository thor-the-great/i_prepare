package random_problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1282. Group the People Given the Group Size They Belong To
 * Medium
 *
 * There are n people whose IDs go from 0 to n - 1 and each person belongs exactly to one group. Given the array
 * groupSizes of length n telling the group size each person belongs to, return the groups there are and the people's
 * IDs each group includes.
 *
 * You can return any solution in any order and the same applies for IDs. Also, it is guaranteed that there exists at
 * least one solution.
 *
 *
 *
 * Example 1:
 *
 * Input: groupSizes = [3,3,3,3,3,1,3]
 * Output: [[5],[0,1,2],[3,4,6]]
 * Explanation:
 * Other possible solutions are [[2,1,6],[5],[0,4,3]] and [[5],[0,6,2],[4,3,1]].
 * Example 2:
 *
 * Input: groupSizes = [2,1,3,3,3,2]
 * Output: [[1],[0,5],[2,3,4]]
 *
 *
 * Constraints:
 *
 * groupSizes.length == n
 * 1 <= n <= 500
 * 1 <= groupSizes[i] <= n
 */
public class GroupPeopleGivenGroupSize {

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        //group ids by the group size
        Map<Integer, List<Integer>> m = new HashMap();
        for (int i = 0; i < groupSizes.length; i++) {
            int group = groupSizes[i];
            if (!m.containsKey(group))
                m.put(group, new ArrayList());
            m.get(group).add(i);
        }

        List<List<Integer>> res = new ArrayList();
        //for every group size we may form one or more groups
        for (int group : m.keySet()) {
            //get ids with this group size
            List<Integer> ids = m.get(group);
            for (int i = 0; i < ids.size(); i+=group) {
                //every next portion of ids will be from i to i + group
                List<Integer> oneGroup = new ArrayList();
                for (int j = i; j < i + group; j++) {
                    oneGroup.add(ids.get(j));
                }
                res.add(oneGroup);
            }
        }
        return res;
    }
}
