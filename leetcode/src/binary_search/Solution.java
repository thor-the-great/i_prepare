package binary_search;

public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int l=0, r = nums.length - 1;
        int x = -1;
        while(l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                x = m;
                break;
            }
            if (nums[m] < target) {
                l = m + 1;
            }
            else {
                r = m - 1;
            }
        }

        if (x == -1)
            return new int[] {-1, -1};

        do {
            if (nums[l] == target)
                break;
            l++;
        } while (l < x);

        do {
            if (nums[r] == target)
                break;
            r--;
        } while (r > x);
        return new int[] {l, r};

        /*int lPoint = findPoint(nums, l, x - 1, target);
        //int rPoint = findPoint(nums, x + 1, r, target);
        return new int[] {lPoint == -1 ? x : lPoint, rPoint == -1 ? x : rPoint};*/
    }

    int findPoint(int[] nums, int x, int y, int target) {
        int l = x, r = y;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                return m;
            }
            if (nums[m] < target) {
                l = m + 1;
            }
            else {
                r = m - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();
        //System.out.println(intArrayToString(obj.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 7)));
        //System.out.println(intArrayToString(obj.searchRange(new int[]{1}, 0)));
        System.out.println(intArrayToString(obj.searchRange(new int[]{0,0,0,0,1,2,3,3,4,5,6,6,7,8,8,8,9,9,10,10,11,11}, 0)));
    }

    static String intArrayToString(int[] a) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i =0;i < a.length; i++) {
            sb.append(a[i]);
            if (i < a.length - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
