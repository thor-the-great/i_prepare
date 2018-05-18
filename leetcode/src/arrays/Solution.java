package arrays;

import java.util.*;

public class Solution {

    public int pivotIndex(int[] nums) {
        if (nums.length == 0)
            return -1;

        int sum = 0;
        for (int i : nums)
            sum += i;

        int runSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int sumToDivide = sum - nums[i];
            if (sumToDivide - runSum == runSum)
                return i;
            else
                runSum += nums[i];
        }

        return -1;
    }

    /**
     * https://leetcode.com/problems/plus-one/description/
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        boolean isShift = false;
        for (int i = digits.length - 1; i >=0; i--) {
            if (i == digits.length - 1 || isShift) {
                int nextDigit = digits[i] + 1;
                if (nextDigit > 9) {
                    isShift = true;
                    digits[i] = 0;
                } else {
                    digits[i] = nextDigit;
                    isShift = false;
                }
            } else //exit on second digit if there is not shift
                break;
        }
        //in case the last shift was on first digit - need to extend array by one element
        if (isShift) {
            int[] newArray = new int[digits.length + 1];
            newArray[0] = 1;
            for (int i = 0; i < digits.length; i++) {
                newArray[i + 1] = digits[i];
            }
            digits = newArray;
        }
        return digits;
    }

    /**
     * https://leetcode.com/explore/learn/card/array-and-string/202/introduction-to-2d-array/1167/
     * @param matrix
     * @return
     */
    public int[] findDiagonalOrder(int[][] matrix) {
        int M = matrix.length, N = M == 0 ? 0 : matrix[0].length;
        int numOfElements = N * M;
        int[] result = new int[numOfElements];

        int countOfIterations = M + N - 1;
        boolean up = true;
        int minDim = Math.min(M,N);
        int countOfDim = 1;
        int countOfElementsInResult = 0;
        boolean pikePassed = false;
        boolean onPike = false;
        int numOfIterationOnPike = Math.max(M, N) - Math.min(M, N) - 1 > 0 ?  Math.max(M, N) - Math.min(M, N) - 1 : 0;

        int startM = 0, startN = 0;
        for (int iteration = 1; iteration <= countOfIterations; iteration++) {
            if (countOfDim <= minDim  && !onPike) {
                for (int i = 0; i < countOfDim; i++) {
                    if (up) {
                        result[countOfElementsInResult] = matrix[startM - i][startN + i];
                        if ( i == countOfDim - 1) {
                            startM -=i;
                            startN +=i;
                        }
                    }
                    else {
                        result[countOfElementsInResult] = matrix[startM + i][startN - i];
                        if ( i == countOfDim - 1) {
                            startM +=i;
                            startN -=i;
                        }
                    }
                    countOfElementsInResult++;
                }
                if ((up && !pikePassed) || (!up && pikePassed)) {
                    if (startN + 1 < N)
                        startN++;
                    else
                       startM++;
                }
                else {
                    if (startM + 1 < M)
                        startM++;
                    else
                        startN++;
                }
                if (countOfDim < minDim && !pikePassed) {
                    countOfDim++;
                }
                else if (pikePassed && countOfDim >= 0) {
                    countOfDim--;
                }
                else if (!pikePassed){
                    if (numOfIterationOnPike > 0)
                        onPike = true;
                    else {
                        pikePassed = true;
                        //special case - if M == N we remove one iteration, kind of merge last iteration before and after pike
                        if (N == M)
                            countOfDim = countOfDim > 0 ? countOfDim -1 : 0;
                    }
                }
            } else if (onPike) {
                for (int i = 0; i < countOfDim; i++) {
                    if (up) {
                        result[countOfElementsInResult] = matrix[startM - i][startN + i];
                        if ( i == countOfDim - 1) {
                            startM -=i;
                            startN +=i;
                        }
                    }
                    else {
                        result[countOfElementsInResult] = matrix[startM + i][startN - i];
                        if ( i == countOfDim - 1) {
                            startM +=i;
                            startN -=i;
                        }
                    }

                    countOfElementsInResult++;
                }
                if (N  > M) {
                    if (startN + 1 < N)
                        startN++;
                    else
                        startM++;
                } else {
                    if (startM  + 1 < M)
                        startM++;
                    else
                        startN++;
                }
                numOfIterationOnPike--;
                if(numOfIterationOnPike == 0) {
                    onPike = false;
                    pikePassed = true;
                }
            }
            up = !up;
        }
        return result;
    }

    /**
     * https://leetcode.com/explore/learn/card/array-and-string/202/introduction-to-2d-array/1168/
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int M = matrix.length, N = M == 0 ? 0 : matrix[0].length;
        int elementCount = N * M;
        List<Integer> result = new ArrayList<>();
        if (N == 0 || M == 0)
            return result;
        boolean[][] seen = new boolean[M][N];
        boolean allSeen = false;
        int currentM = 0;
        int currentN = -1;

        //initialize direction and state after first step
        //direction -   index 0 - horizontal, N
        //              inde x 1 - vertical, M
        int dir0 = 1;
        int dir1 = 0;

        while (!allSeen) {
            if (!isStepPossible(currentN, currentM, M, N, dir0, dir1, seen)) {
                //find next direction, doing cw turns
                //checking ->, next \l/
                if (dir0 == 1) {
                    if (isStepPossible(currentN, currentM, M, N, 0, 1, seen)) {
                        dir0 = 0;
                        dir1 = 1;
                    }
                } else if (dir1 == 1) {
                    //next change \l/ to <-
                    if (isStepPossible(currentN, currentM, M, N, -1, 0, seen)) {
                        dir0 = -1;
                        dir1 = 0;
                    }
                } else if (dir0 == -1) {
                    //next change <- to /l\
                    if (isStepPossible(currentN, currentM, M, N, 0, -1, seen)) {
                        dir0 = 0;
                        dir1 = -1;
                    }
                } else {
                    //next change /l\ to ->
                    if (isStepPossible(currentN, currentM, M, N, 1, 0, seen)) {
                        dir0 = 1;
                        dir1 = 0;
                    }
                }
            }
            currentN += dir0;
            currentM += dir1;
            result.add(matrix[currentM][currentN]);
            seen[currentM][currentN] = true;
            allSeen = result.size() >= elementCount;
        }
        return result;
    }

    boolean isStepPossible(int currentN, int currentM, int M, int N, int dir0, int dir1, boolean[][] seen) {
        //boolean result = false;
        int nextN = currentN + dir0;
        int nextM = currentM + dir1;

        if ((nextM >= 0 && nextM < M) &&
                (nextN >= 0 && nextN < N) &&
                !seen[nextM][nextN]) {
            return true;
        }
        else
            return false;
    }

    /**
     * https://leetcode.com/explore/learn/card/array-and-string/202/introduction-to-2d-array/1170/
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList();
        List<Integer> prevRow = null;
        for (int i = 0; i < numRows; i++) {
            List<Integer> oneRow = new ArrayList();
            oneRow.add(1);
            if (i > 0) {
                //List<Integer> prevRow = result.get(result.size() - 1);
                for (int j = 0; j < prevRow.size() - 1; j ++) {
                    oneRow.add(prevRow.get(j) + prevRow.get(j + 1));
                }
                oneRow.add(1);
            }
            result.add(oneRow);
            prevRow = oneRow;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();

        System.out.println(listToString(obj.spiralOrder(new int[][]{
                {1, 2,  3 },
                {4, 5,  6 },
                {7, 8,  9}
        })));

        System.out.println(listToString(obj.spiralOrder(new int[][]{
                {1, 2,  3,  4},
                {5, 6,  7,  8},
                {9, 10, 11, 12}
        })));

        /*System.out.println(intArrayToString(obj.findDiagonalOrder(new int[][]{
                {2,   3,  4 },
                {5,   6,  7 },
                {8,   9, 10},
                {11, 12, 13 },
                {14, 15, 16}
        })));

        System.out.println(intArrayToString(obj.findDiagonalOrder(new int[][]{
        })));

        System.out.println(intArrayToString(obj.findDiagonalOrder(new int[][]{
                {1, 2,  3 },
                {4, 5,  6 },
                {7, 8,  9}
        })));

        System.out.println(intArrayToString(obj.findDiagonalOrder(new int[][]{
                {1, 2,  3,  4},
                {5, 6,  7,  8},
                {9, 10, 11, 12}
        })));

        System.out.println(intArrayToString(obj.findDiagonalOrder(new int[][]{
                {1,  2,  3},
                {4,  5,  6},
                {7,  8,  9},
                {10,11, 12}
        })));

        System.out.println(intArrayToString(obj.findDiagonalOrder(new int[][]{
                {1,  2,  3, 4, 5}
        })));

        System.out.println(intArrayToString(obj.findDiagonalOrder(new int[][]{
                {1},
                {2},
                {3},
                {4}
        })));*/


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

    static String listToString (List l) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i =0;i < l.size(); i++) {
            sb.append(l.get(i));
            if (i < l.size() - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
