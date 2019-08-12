/**
 * Find the Maximum Contiguous Subsequence in an Array New
 *     Recursion Arrays Numbers
 * Given an array of integers consisting of both positive and negative
 * numbers, find the contiguous subsequence that has the maximum sum among all subsequences in the array (click the
 * red text to learn more about subsequences). Write a method that takes in
 * an array of integers arr and returns an array res containing 3 integers in the following format:
 *
 * res[0] = max sum
 * res[1] = starting index of the subsequence
 * res[2] = ending index of the subsequence
 *
 * Examples:
 *
 * maxContSequence({-1,-2,3,4,5}) ==> {12,2,4}
 *
 * maxContSequence({1,2,3,-2,5}) ==> {6,0,2}
 */
public class MaxContiguesSubarray {

    /**
     * Kadeins algorithm - go with current sum, check on every step :
     * - if we are greater than max - make it new max
     * - if it's lower than previous current - start over from this element
     * @param arr
     * @return
     */
    public static int[] maxContSequence(int[] arr) {

        int[] res = new int[] {0, 0, -1};

        int max = Integer.MIN_VALUE, cur = 0, start = 0, end = -1;

        for (int i = 0; i < arr.length; i++) {
            //possible next cur sum
            int t = cur + arr[i];
            //if with this element we are lower than before
            if (t < arr[i]) {
                //set current as current element, i as start and end indexes
                cur = arr[i];
                start = i;
                end = i;
                //else update current sum and end index
            } else {
                cur = t;
                end = i;
            }

            //if we are greater than max sum - this is out new max
            if (cur > max) {
                max = cur;
                res[0] = max;
                res[1] = start;
                res[2] = end;
            }
        }

        return res;
    }
}
