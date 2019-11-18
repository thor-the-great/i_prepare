package graphs;

import java.util.*;

/**
 * 1257. Smallest Common Region
 * Medium
 *
 * You are given some lists of regions where the first region of each list includes all other regions in that list.
 *
 * Naturally, if a region X contains another region Y then X is bigger than Y.
 *
 * Given two regions region1, region2, find out the smallest region that contains both of them.
 *
 * If you are given regions r1, r2 and r3 such that r1 includes r3, it is guaranteed there is no r2 such that r2 includes r3.
 *
 * It's guaranteed the smallest region exists.
 *
 *
 *
 * Example 1:
 *
 * Input:
 * regions = [["Earth","North America","South America"],
 * ["North America","United States","Canada"],
 * ["United States","New York","Boston"],
 * ["Canada","Ontario","Quebec"],
 * ["South America","Brazil"]],
 * region1 = "Quebec",
 * region2 = "New York"
 * Output: "North America"
 *
 *
 * Constraints:
 *
 * 2 <= regions.length <= 10^4
 * region1 != region2
 * All strings consist of English letters and spaces with at most 20 letters.
 */
public class SmallestCommonRegion {

    /**
     * This is smallest common ancestor problem, can be solved via Maps. Create map key is child and parent is
     * value. Then get the set of all parents of the first region1. Then start collecting parents of the second
     * region2, when we met parent that is in the set of region1 parents - this is our answer
     * @param regions
     * @param region1
     * @param region2
     * @return
     */
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        Map<String, String> m = new HashMap();
        //create map <child,parent> for all regions
        for (List<String> regs : regions) {
            String mainReg = regs.get(0);
            for (int i = 1; i < regs.size(); i++) {
                m.put(regs.get(i), mainReg);
            }
        }
        //get set of region1 parents back to the root
        Set<String> reg1Parents = new HashSet();
        while (region1 != null) {
            reg1Parents.add(region1);
            region1 = m.get(region1);
        }

        String res = null;
        //now start collection region2 parents, checking every new parent with the region1 parents set
        while (region2 != null) {
            if (reg1Parents.contains(region2)) {
                res = region2;
                break;
            }
            region2 = m.get(region2);
        }
        return res;
    }
}
