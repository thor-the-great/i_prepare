package path.google;

/**
 * 540. Single Element in a Sorted Array
 * Medium
 *
 *
 * Given a sorted array consisting of only integers where every element appears twice except for one element which
 * appears once. Find this single element that appears only once.
 *
 * Example 1:
 * Input: [1,1,2,3,3,4,4,8,8]
 * Output: 2
 * Example 2:
 * Input: [3,3,7,7,10,11,11]
 * Output: 10
 *
 * Note: Your solution should run in O(log n) time and O(1) space.
 */
public class SingleElementSortedArray {

    public int singleNonDuplicate(int[] nums) {
        //return singleNonDuplicateBinarySearch(nums);
        return singleNonDuplicateBits(nums);
    }

    public int singleNonDuplicateBits(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }

    /**
     * Idea is following:
     * There are odd number of elements in array
     * if we start dividing array on 2 parts
     * in those 2 parts one will be of odd size and another - of even. If we check 1-st and 2-nd elements of odd
     * part and if they are equals we automatically eliminate the other part from the search because it's not possible
     * that one elements is sitting there - in this case elements are shirted and will not be equals.
     * If the are not equals - it means balance has shifted from the first part by our single so it's sitting there.
     * We adjust l and r pointers and repeat procedure
     * 1,1,2,3,3,4,4        l=0, r=6, m = 3 shifted = 2,
     * 1,1,|2,3,3,4,4       2 != 3 => single is in first part, l=0, r = 2
     *
     * |1,1,2                l=0, r=2, m=1 => 0; 1 == 1 => l = 2, r = 2
     * exit, return nums[2] = 2
     *
     * @param nums
     * @return
     */
    public int singleNonDuplicateBinarySearch(int[] nums) {
        int N = nums.length;
        int l =0, r = N - 1;
        while (l < r) {
            int m = l + ((r -l) / 2);
            //make l to m even
            if (m % 2 == 1)
                m -= 1;

            if (nums[m] == nums[m + 1]) {
                l = m + 2;
            } else {
                r = m;
            }
        }
        return nums[r];
    }

    public static void main(String[] args) {
        SingleElementSortedArray obj = new SingleElementSortedArray();
        System.out.println(obj.singleNonDuplicate(new int[] {1, 1, 2, 3, 3, 4, 4, 7, 7, 8, 8, 9, 9}));

        System.out.println(obj.singleNonDuplicate(new int[] {1, 1, 2, 2, 3, 3, 4, 4, 7, 7, 8, 9, 9}));
    }
}
