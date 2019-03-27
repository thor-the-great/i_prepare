package random_problems;

/**
 * 1020. Partition Array Into Three Parts With Equal Sum
 * Easy
 *
 * Given an array A of integers, return true if and only if we can partition the array into three non-empty
 * parts with equal sums.
 *
 * Formally, we can partition the array if we can find indexes i+1 < j with (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2]
 * + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1])
 *
 *
 *
 * Example 1:
 *
 * Input: [0,2,1,-6,6,-7,9,1,2,0,1]
 * Output: true
 * Explanation: 0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
 * Example 2:
 *
 * Input: [0,2,1,-6,6,7,9,-1,2,0,1]
 * Output: false
 * Example 3:
 *
 * Input: [3,3,6,5,-2,2,5,1,-9,4]
 * Output: true
 * Explanation: 3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
 *
 *
 * Note:
 *
 * 3 <= A.length <= 50000
 * -10000 <= A[i] <= 10000
 *
 */
public class PartitionArrayIntoEqualSumParts {

    /**
     * Idea: exact sum of the segment is the overall sum / 3. Get that segment sum and start scanning array elements
     * one by one. Once we reach segment sum - increment number of segments, reset current to 0.
     *
     * @param A
     * @return
     */
    public boolean canThreePartsEqualSum(int[] A) {
        int sum = 0;
        //calculate overall sum
        for (int n : A)
            sum += n;
        //check if it's divisible by 3
        if (sum % 3 != 0)
            return false;
        //exact sum of each segment
        sum /= 3;

        int curSum = 0;
        int numOfSegments = 0;
        for (int i = 0; i < A.length; i++) {
            curSum += A[i];
            //check if we can form a segment
            if (curSum == sum && numOfSegments <= 1) {
                numOfSegments++;
                curSum = 0;
            }
        }
        //if we have 2 segments formed greedily and sum of leftover is also 1/3 of overall sum
        return (numOfSegments == 2 && curSum == sum);
    }
}
