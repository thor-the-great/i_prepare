package arrays;

import java.util.*;

/**
 * https://leetcode.com/explore/learn/card/array-and-string/
 */
public class Sol2 {

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


    /**
     * https://leetcode.com/explore/learn/card/array-and-string/203/introduction-to-string/1162/
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        StringBuilder resultSB = new StringBuilder();
        if (strs.length == 0)
            return "";
        int maxPreffixLength = Integer.MAX_VALUE;
        for(String str : strs) {
            if (str.length() < maxPreffixLength)
                maxPreffixLength = str.length();
        }

        int cursorPosition = 0;
        boolean isSearchDone = false;

        while (cursorPosition < maxPreffixLength && !isSearchDone) {
            char nextChar = strs[0].charAt(cursorPosition);
            for(int i = 1; i < strs.length; i++) {
                if (nextChar != strs[i].charAt(cursorPosition)) {
                    isSearchDone = true;
                    break;
                }
            }
            if (!isSearchDone) {
                resultSB.append(nextChar);
                cursorPosition++;
            }
        }

        return resultSB.toString();
    }

    public void rotate(int[] nums, int k) {
        int N = nums.length;
        int realSteps = k % N;
        int[] tmpArray = new int[realSteps];
        for (int i = N - realSteps; i < N; i ++) {
            tmpArray[i - (N - realSteps)] = nums[i];
        }
        for(int i = N - realSteps - 1; i >= 0; i--) {
            nums[i + realSteps] = nums[i];
        }
        for(int i = 0; i < tmpArray.length; i++) {
            nums[i] = tmpArray[i];
        }
    }

    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0)
            return Arrays.asList(new Integer[]{1});
        int[] oneRow = new int[rowIndex];
        oneRow[0] = 1;
        int[] secondRow = new int[rowIndex + 1];
        secondRow[0] = 1;
        secondRow[1] = 1;
        for (int i = 2; i <= rowIndex; i++) {
            for(int j = 0; j < i; j++) {
                oneRow[j] = secondRow[j];
            }
            secondRow[0] = 1;
            for (int j = 1; j < i; j++) {
                secondRow[j] = oneRow[j-1] + oneRow[j];
            }
            secondRow[i] = 1;
        }
        List<Integer> result = new ArrayList<>();
        for (int arrayElement: secondRow) {
            result.add(arrayElement);
        }
        return result;
    }

    /**
     * https://leetcode.com/explore/learn/card/array-and-string/204/conclusion/1164/
     * @param s
     * @return
     */
    public String reverseWords1(String s) {
        /*List<String> wordList = new ArrayList();
        boolean isNewWordStarted = false;
        //char[] sCharArray = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        StringBuilder resSb = new StringBuilder();
        for (int i=0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                sb.append(s.charAt(i));
                if (sb.length() == 1)
                    isNewWordStarted = true;
            } else {
                if (isNewWordStarted) {
                    wordList.add(sb.toString());
                    isNewWordStarted = false;
                    sb.setLength(0);
                }
            }
        }
        if (sb.length() > 0 && wordList.size() > 0)
            sb.append(" ");
        for(int i = wordList.size() - 1; i >= 0; i--) {
            sb.append(wordList.get(i));
            if (i > 0 )
                sb.append(" ");
        }
        return sb.toString();*/

        //working solution 2
        /*boolean isNewWordStarted = false;
        StringBuilder resSb = new StringBuilder();
        int wordL = 0;
        int wordC = 0;
        int insertLocation = 0;
        for (int i = s.length() - 1; i >= 0 ; i--) {
            if (s.charAt(i) != ' ') {
                wordL++;
                if (wordL == 1) {
                    if (wordC > 0)
                        resSb.append(" ");
                    resSb.append(s.charAt(i));
                    isNewWordStarted = true;
                    insertLocation = resSb.length() - 1;
                } else {
                    resSb.insert(insertLocation, s.charAt(i));
                }
            } else {
                if (isNewWordStarted) {
                    wordC++;
                    isNewWordStarted = false;
                    wordL = 0;
                }
            }
        }
        return resSb.toString();*/

        //fastest solution - 92%
        int countSymbolsInWord = 0;
        int pointerWordStart = 0;
        StringBuilder resSb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0 ; i--) {
            if (s.charAt(i) != ' ') {
                countSymbolsInWord++;
                if (countSymbolsInWord == 1)
                    pointerWordStart = i;
            } else {
                if (countSymbolsInWord >= 1) {
                    getTheWord(s, countSymbolsInWord, pointerWordStart, resSb);
                    resSb.append(" ");
                    countSymbolsInWord = 0;
                }
            }
        }

        if (countSymbolsInWord >= 1) {
            getTheWord(s, countSymbolsInWord, pointerWordStart, resSb);
        }

        return resSb.toString().trim();
    }

    private void getTheWord(String s, int countSymbolsInWord, int pointerWordStart, StringBuilder resSb) {
        for (int j = pointerWordStart - countSymbolsInWord + 1; j <= pointerWordStart; j++) {
            resSb.append(s.charAt(j));
        }
    }

    /**
     * https://leetcode.com/explore/learn/card/array-and-string/204/conclusion/1165/
     * @param s
     * @return
     */
    public String reverseWords2(String s) {
        int countSymbolsInWord = 0;
        int pointerWordStart = 0;
        StringBuilder resSb = new StringBuilder();
        for (int i = 0; i < s.length() ; i++) {
            if (s.charAt(i) != ' ') {
                countSymbolsInWord++;
                if (countSymbolsInWord == 1)
                    pointerWordStart = i;
            } else {
                if (countSymbolsInWord >= 1) {
                    getTheWord2(s, countSymbolsInWord, pointerWordStart, resSb);
                    resSb.append(" ");
                    countSymbolsInWord = 0;
                }
            }
        }

        if (countSymbolsInWord >= 1) {
            getTheWord2(s, countSymbolsInWord, pointerWordStart, resSb);
        }

        return resSb.toString();
    }

    private void getTheWord2(String s, int countSymbolsInWord, int pointerWordStart, StringBuilder resSb) {
        for (int j = pointerWordStart + countSymbolsInWord - 1; j >= pointerWordStart; j--) {
            resSb.append(s.charAt(j));
        }
    }

    /**
     * https://leetcode.com/explore/learn/card/array-and-string/204/conclusion/1173/
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int newPointer = 0, oldPointer = 0;
        while (oldPointer < nums.length) {
            int number = nums[oldPointer];
            while(oldPointer < nums.length && nums[oldPointer] == number) {
                oldPointer++;
            }
            nums[newPointer] = number;
            newPointer++;
        }
        return newPointer;
    }

    /**
     * https://leetcode.com/explore/learn/card/array-and-string/204/conclusion/1174/
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int leftZeroPointer = -1;
        int leftNonZeroPointer = 0;
        while (leftNonZeroPointer < nums.length) {
            if (nums[leftNonZeroPointer] == 0) {
                int nextPointer = leftNonZeroPointer + 1;
                int firstZeroIndex = leftZeroPointer == -1 ? nextPointer - 1 : leftZeroPointer;
                while (nextPointer < nums.length && nums[nextPointer] != 0 ) {
                    if (nums[nextPointer] != 0) {
                        nums[firstZeroIndex] = nums[nextPointer];
                        nums[nextPointer] = 0;
                        nextPointer++;
                        firstZeroIndex++;
                    }
                }
                leftZeroPointer = firstZeroIndex;
                leftNonZeroPointer = nextPointer;
            }
            else {
                leftNonZeroPointer++;
            }
        }
    }

    public boolean isSumPairInArray(int[] arr, int sum) {
        HashSet<Integer> compliments = new HashSet();
        for (int i=0; i<arr.length; i++) {
            if (compliments.contains(arr[i]))
                return true;
            int nextCompliment = sum - arr[i];
            compliments.add(nextCompliment);
        }
        return false;
    }

    /**
     * Print all subsets of set - FB interview question
     * @param array
     */
    public void getAllSubsetsOfSet(int[] array) {
        int[] subsets = new int[array.length];
        helperSubset(array, subsets, 0);
    }

    void helperSubset(int[] originalArray, int[] subsets, int i) {
        if (i>= originalArray.length) {
            printSubset(subsets);
            return;
        }
        //decide on elements
        subsets[i] = Integer.MIN_VALUE;
        helperSubset(originalArray, subsets, i+1);
        subsets[i] = originalArray[i];
        helperSubset(originalArray, subsets, i+1);
    }

    void printSubset(int[] subset) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0; i<subset.length; i++) {
            if (subset[i] != Integer.MIN_VALUE)
                sb.append(subset[i]);
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    /**
     * Product of Array Except Self
     * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the
     * product of all the elements of nums except nums[i].
     *
     * Example:
     *
     * Input:  [1,2,3,4]
     * Output: [24,12,8,6]
     * Note: Please solve it without division and in O(n).
     *
     * Follow up:
     * Could you solve it with constant space complexity? (The output array does not count as extra space for the
     * purpose of space complexity analysis.)
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        //idea is to do two scans - one from left to right and after that - opposie way.
        //on first iteration - collection products of elements till (i-1) to result
        //on second iteration - collection products of elements to the right up to i + 1.
        //this way in i-th element will be product p(0..i-1) * p(i+1...N-1)
        int N = nums.length;
        int[] res = new int[N];
        Arrays.fill(res, 1);
        //do the first pass - fill array from left to right
        for (int i = 1; i < N; i++) {
            res[i] = res[i - 1]*nums[i - 1];
        }
        //now fill compliment numbers
        int tmp = 1;
        for(int i = N - 2; i >=0 ;i--) {
            tmp *= nums[i + 1];
            res[i] = res[i] * tmp;
        }
        return res;
    }

    /**
     * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at
     * least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
     *
     * Example 1:
     *
     * Input: [1,3,4,2,2]
     * Output: 2
     * Example 2:
     *
     * Input: [3,1,3,4,2]
     * Output: 3
     * Note:
     *
     * You must not modify the array (assume the array is read only).
     * You must use only constant, O(1) extra space.
     * Your runtime complexity should be less than O(n2).
     * There is only one duplicate number in the array, but it could be repeated more than once.
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        //idea is to use two pointers technique similar to cycle search in linked list. Iterate with slow and fast pointers
        //slow goes + 1, fast goes 2xslow. They will meet eventually (meaning that there is a cycle = there is a duplicate)
        //after that start fresh pointer from 0 and another one from point of met. They will meet at the point when
        //cycle started = duplicate
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        int rep = 0;
        while (rep != slow) {
            rep = nums[rep];
            slow = nums[slow];
        }
        return rep;
    }

    public static void main(String[] args) {
        Sol2 obj = new Sol2();

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

        //System.out.println(obj.dominantIndex(new int[] {3, 4, 9, 1, 0}));

        //String[] data = new String[]{"flower","flow","flight"};
        //System.out.println(obj.longestCommonPrefix(data));

        /*int[] array = new int[]{1,2,3,4,5,6,7};
        obj.rotate(array, 3);
        System.out.println(intArrayToString(array));*/

        //System.out.println(obj.getRow(0));

        //System.out.println(obj.reverseWords1("   a   "));

        //System.out.println(obj.reverseWords1(" 1"));

        //System.out.println(obj.reverseWords1("the sky is blue"));

        /*int[] nums = new int[]{0, 1, 2, 0, 3, 0, 4, 5};
        obj.moveZeroes(nums);
        System.out.println(intArrayToString(nums));

        nums = new int[]{0, 1, 0, 0, 2, 3, 4, 0, 0, 5, 6, 7};
        obj.moveZeroes(nums);
        System.out.println(intArrayToString(nums));

        nums = new int[]{0, 0, 0, 0, 0};
        obj.moveZeroes(nums);
        System.out.println(intArrayToString(nums));*/

        //System.out.println(obj.isSumPairInArray(new int[] {1, 3, 9, 0, 4}, 3));

        obj.getAllSubsetsOfSet(new int[] {1,3,2});
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
