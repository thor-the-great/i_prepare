package arrays;

import java.util.*;

/**
 * https://leetcode.com/explore/learn/card/array-and-string/
 */
public class Solution {

    /**
     * https://leetcode.com/explore/learn/card/array-and-string/201/introduction-to-array/1144/
     * @param nums
     * @return
     */
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
        //              index 1 - vertical, M
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

    private boolean isStepPossible(int currentN, int currentM, int M, int N, int dir0, int dir1, boolean[][] seen) {
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
                for (int j = 0; j < prevRow.size() - 1; j++) {
                    oneRow.add(prevRow.get(j) + prevRow.get(j + 1));
                }
                oneRow.add(1);
            }
            result.add(oneRow);
            prevRow = oneRow;
        }
        return result;
    }

    /**
     * https://leetcode.com/explore/learn/card/array-and-string/201/introduction-to-array/1147/
     * @param nums
     * @return
     */
    public int dominantIndex(int[] nums) {
        if (nums.length == 1)
            return 0;

        int largest = nums[0];
        //, nextLarge = Integer.MIN_VALUE;
        int largeIndex = 0;
        boolean isTwiceLarge = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > largest) {
                if (nums[i] >= largest*2)
                    isTwiceLarge = true;
                else
                    isTwiceLarge = false;
                largest = nums[i];
                largeIndex = i;
            } else {
                if (largest < nums[i]*2)
                    isTwiceLarge = false;
            }
        }
        if (!isTwiceLarge)
            return -1;
        else
            return largeIndex;
    }

    public String addBinary(String a, String b) {
        //make sure a is always longer
        if (b.length() > a.length())
            return addBinary(b, a);

        char[] aChar = a.toCharArray();
        char[] bChar = b.toCharArray();

        StringBuilder sb = new StringBuilder();
        boolean carryOn = false;
        int shift = aChar.length - bChar.length;
        for (int i = aChar.length - 1; i >=0; i--) {
            int nextChar = aChar[i] - 48;
            if (i - shift >= 0 ) {
                int nextCharB = bChar[i - shift] - 48;
                nextChar +=nextCharB;
            }
            if (carryOn)
                nextChar++;

            if (nextChar == 0) {
                sb.append('0');
                carryOn = false;
            } else if (nextChar == 1) {
                sb.append('1');
                carryOn = false;
            } else if (nextChar == 2) {
                sb.append('0');
                carryOn = true;
            } else if (nextChar == 3) {
                sb.append('1');
                carryOn = true;
            }
        }
        if (carryOn)
            sb.append('1');

        return sb.reverse().toString();
    }

    /**
     * https://leetcode.com/explore/learn/card/array-and-string/203/introduction-to-string/1161/
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int hL = haystack.length();
        int nL = needle.length();

        if (nL > hL)
            return -1;
        if (nL == 0)
            return 0;

        int[] kmpArray = getPartMatchTable(needle);

        int c = 0;
        while ( c <= hL - nL) {
            boolean found = true;
            for (int j = 0; j < nL; j++) {
                if (haystack.charAt(c) != needle.charAt(0)) {
                    found = false;
                    c++;
                    break; //from for loop
                }
                else if (haystack.charAt(j + c) != needle.charAt(j)) {
                    found = false;
                    c = c + j - kmpArray[j - 1];
                    break;
                }
            }
            if (found)
                return c;
        }
        return -1;
    }

    private int[] getPartMatchTable(String needle) {
        int[] result = new int[needle.length()];
        for (int i = 1; i < needle.length(); i++) {
            //fragment length is i
            int prevIndex = result[i - 1];
            while (prevIndex > 0 && needle.charAt(i) != needle.charAt(prevIndex)) {
                prevIndex = result[prevIndex - 1];
            }
            if (needle.charAt(i) == needle.charAt(prevIndex))
                result[i] = prevIndex + 1;
            else
                result[i] = 0;
        }
        return result;
    }

    public int[] getNext(String needle) {
        int[] next = new int[needle.length()];
        next[0] = 0;

        for (int i = 1; i < needle.length(); i++) {
            int index = next[i - 1];
            while (index > 0 && needle.charAt(index) != needle.charAt(i)) {
                index = next[index - 1];
            }

            if (needle.charAt(index) == needle.charAt(i)) {
                next[i] = next[i - 1] + 1;
            } else {
                next[i] = 0;
            }
        }

        return next;
    }

    public static void main(String[] args) {
        Solution obj = new Solution();

        obj.getNext("ababcaabc");

        //System.out.println(obj.strStr("hello", "ll"));

        System.out.println(obj.strStr("ababcaababcaabc", "ababcaabc"));

        //System.out.println(obj.addBinary("1", "111"));

        /*System.out.println(listToString(obj.spiralOrder(new int[][]{
                {1, 2,  3 },
                {4, 5,  6 },
                {7, 8,  9}
        })));

        System.out.println(listToString(obj.spiralOrder(new int[][]{
                {1, 2,  3,  4},
                {5, 6,  7,  8},
                {9, 10, 11, 12}
        })));*/

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

        System.out.println(obj.dominantIndex(new int[] {3, 4, 9, 1, 0}));

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

    static String listToString(List<Integer> a) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i =0;i < a.size(); i++) {
            sb.append(a.get(i));
            if (i < a.size() - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
