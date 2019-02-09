package path.adobe;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap();
            int[] res = new int[]{-1, -1};
            for (int i = 0; i < nums.length; i++) {
                int comp = target - nums[i];
                if (map.containsKey(comp)) {
                    res[0] = map.get(comp);
                    res[1] = i;
                    return res;
                }
                else {
                    map.put(nums[i], i);
                }
            }
            return res;
        }
}
