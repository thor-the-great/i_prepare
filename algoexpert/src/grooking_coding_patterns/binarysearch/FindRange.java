package grooking_coding_patterns.binarysearch;

/**
 * Number Range (medium)
 *
 * Given an array of numbers sorted in ascending order, find the range of a given number ‘key’. The range of the
 * ‘key’ will be the first and last position of the ‘key’ in the array.
 *
 * Write a function to return the range of the ‘key’. If the ‘key’ is not present return [-1, -1].
 *
 * Example 1:
 *
 * Input: [4, 6, 6, 6, 9], key = 6
 * Output: [1, 3]
 * Example 2:
 *
 * Input: [1, 3, 8, 10, 15], key = 10
 * Output: [3, 3]
 * Example 3:
 *
 * Input: [1, 3, 8, 10, 15], key = 12
 * Output: [-1, -1]
 */
public class FindRange {

    public static int[] findRange(int[] arr, int key) {
        int[] result = new int[] { -1, -1 };
        result[0] = search(arr, key, true);
        if (result[0] > -1)
            result[1] = search(arr, key, false);
        return result;
    }

    static int search(int[] arr, int key, boolean isMin) {
        int idx = -1;
        int l = 0, r = arr.length -1 ;

        while (l <= r) {
            int m = (l + r)/2;
            if (arr[m] < key)
                l = m + 1;
            else if (arr[m] > key)
                r = m - 1;
            else {
                idx = m;
                if (isMin)
                    r = m - 1;
                else
                    l = m + 1;
            }
        }

        return idx;
    }

    public static void main(String[] args) {
        int[] result = FindRange.findRange(new int[] { 4, 6, 6, 6, 9 }, 6);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
        result = FindRange.findRange(new int[] { 1, 3, 8, 10, 15 }, 10);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
        result = FindRange.findRange(new int[] { 1, 3, 8, 10, 15 }, 12);
        System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
    }
}
