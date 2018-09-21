import trees.BSTNodeChar;
import utils.StringUtils;

import java.util.*;

public class SolutionDailyCodingSeptember2018 {
    Random rand = new Random();
    /**
     * This problem was asked by Google.
     *
     * We can determine how "out of order" an array A is by counting the number of inversions it has. Two elements A[i]
     * and A[j] form an inversion if A[i] > A[j] but i < j. That is, a smaller element appears after a larger element.
     *
     * Given an array, count the number of inversions it has. Do this faster than O(N^2) time.
     *
     * You may assume each element in the array is distinct.
     *
     * For example, a sorted list has zero inversions. The array [2, 4, 1, 3, 5] has three inversions: (2, 1), (4, 1),
     * and (4, 3). The array [5, 4, 3, 2, 1] has ten inversions: every distinct pair forms an inversion.
     *
     * @param arr
     * @return
     */
    public int countInversions(int[] arr) {
        //recursive divide and conquer approach, runs in O(nlog(n))
        Result result = countHelper(arr);
        return result.invCount;
    }

    Result countHelper(int[] arr) {
        //idea is to divide and conquer.
        //divide array in halves, then calculate inversions in each half and after - number of inversions between
        //halves. in each half we can calculate in O(n), plus log(n) for all array. Trick is to calculate differences
        //between halves in linear time O(n).
        //For that we do the trick - calculate using sorting property (and do actual sorting). If element in left half
        //is greater than element in right half that means there is an inversions, and number of inversions is equal to
        //number of elements left in left size.

        //this is base case of recursion - if there is one element in array return it, count of inversions is 0
        if (arr.length <= 1) {
            Result  ret = new Result();
            if (arr.length > 0)
                for (int element : arr ) {
                    ret.sorted.add(element);
                }
            return ret;
        }
        //divide array into 2, fill each half with values from original array
        int m = arr.length/2;
        int[] left = fillPartOfArray(arr, 0, m);
        int[] right = fillPartOfArray(arr, m, arr.length);
        //do the divide
        Result leftRes = countHelper(left);
        Result rightRes = countHelper(right);
        //calculate results for between halves
        Result betweenResult = countAndSort(leftRes.sorted, rightRes.sorted);
        //form final result, count is a sum of left, right and in between
        Result retResult = new Result();
        retResult.sorted.addAll(betweenResult.sorted);
        retResult.invCount = leftRes.invCount + betweenResult.invCount + rightRes.invCount;
        return retResult;
    }

    private int[] fillPartOfArray(int[] arr, int start, int end) {
        int[] left = new int[end - start];
        for (int i = start; i < end; i++) {
            left[i - start] = arr[i];
        }
        return left;
    }

    Result countAndSort(List<Integer> left, List<Integer> right) {
        int count = 0;
        List<Integer> sorted = new ArrayList();
        int i = 0;
        int j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i) < right.get(j)) {
                sorted.add(left.get(i));
                i++;
            } else if (left.get(i) > right.get(j)) {
                sorted.add(right.get(j));
                count += left.size() - i;
                j++;
            }
        }

        for (int k = i; k < left.size(); k++)
            sorted.add(left.get(k));
        for (int k = j; k < right.size(); k++)
            sorted.add(right.get(k));

        Result retResult = new Result();
        retResult.sorted.addAll(sorted);
        retResult.invCount = count;
        return retResult;
    }
    //utility class to return two variable from method
    class Result {
        List<Integer> sorted = new ArrayList();
        int invCount = 0;
    }

    /**
     * This problem was asked by Two Sigma.
     *
     * Using a function rand5() that returns an integer from 1 to 5 (inclusive) with uniform probability, implement a
     * function rand7() that returns an integer from 1 to 7 (inclusive).
     *
     * @return
     */
    public int random_7() {
        //idea is to generate more uniformly dits numbers, select those suitable for 7 and re-generate everything for
        //other numbers
        //we generate two random_5 numbers, means 5*5=25 uniformly distributed numbers. Then reasoning is following:
        //
        //1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
        //1 2 3 4 5 1 2 3 4 5  1  2  3  4  5  1  2  3  4  5  1  2  3  4  5  - oneR5
        //1         2          3              4              5              - twoR5
        //1 2 3 4 5 6 7 X X X  1  2  3  4  5  6  7  X  X  X  X  X  X  X  X  - rand_7
        //
        //- where the X is - those need to re-enroll.
        //- where twoR5 is 5 - re-generate
        //- where twoR5 = 1 or 3 - rand7 = oneR5
        //- where twoR5 = 2 or 4 need to check oneR5, those that are 1 or 2 - add it, else - re-generate
        int oneR5 = random_5(), twoR5 = random_5();
        int result;
        while (true) {
            //System.out.println( oneR5 + ", " + twoR5);
            if (twoR5 != 5) {
                if (twoR5 == 1 || twoR5 == 3) {
                    result = oneR5;
                    break;
                } else if (oneR5 <= 2) {
                    result = oneR5 + 5;
                    break;
                }
            }
            oneR5 = random_5();
            twoR5 = random_5();
        }
        return result;
    }

    private int random_5() {
        return (1 + rand.nextInt(5));
    }

    /**
     * This problem was asked by Amazon.
     *
     * Given a string, find the longest palindromic contiguous substring. If there are more than one with the maximum
     * length, return any one.
     *
     * For example, the longest palindromic substring of "aabcdcb" is "bcdcb". The longest palindromic substring of
     * "bananas" is "anana".
     *
     * @param s
     * @return
     */
    String getLongestPalindrome(String s) {
        //idea is to check for palindrome from the center. Check helper function uses two iterators technique.
        //there are 2n - 1 possible centers for the palindrome, odd (when there is a letter in the center) and even
        //(when two letters in center are the same).
        //we just scan all string (O(n) time) and on each iteration checking for 2 possible palindromes (O(2*n) = O(n))
        //then we compare and choose the longest string. For our task we keep running longest palindrome.
        //Overall it runs in O(n^2) time and O(1) space
        if (s.isEmpty() || s.length() == 0)
            return s;

        String result = "";
        for (int i = 0; i < s.length(); i++) {
            //check for 2 centers, palindrome can be with odd or even number of letters
            String s1 = checkFromCenter(s, i, i);
            //for this one we need to be extra careful, i+1 can go over the string length
            String s2 = i + 1 >= s.length() ? "" : checkFromCenter(s, i, i  + 1);
            String s12 = s1.length() > s2.length() ? s1 : s2;
            if (s12.length() > result.length())
                result = s12;
        }
        return result;
    }

    String checkFromCenter(String s, int start, int end) {
        //checking for the palindrome, going from center. In case of odd case it can be the same position for start and end.
        //In case of even - different letter that are next to each other
        int L = start, R = start;
        while (start >= 0 && end < s.length()) {
            if (s.charAt(start) == s.charAt(end)) {
                L = start;
                R = end;
                start--;
                end++;
            }
            else
                break;
        }
        return s.substring(L, R + 1);
    }

    /**
     * This problem was asked by Facebook.
     *
     * Given a array of numbers representing the stock prices of a company in chronological order, write a function that
     * calculates the maximum profit you could have made from buying and selling that stock once. You must buy before
     * you can sell it.
     *
     * For example, given [9, 11, 8, 5, 7, 10], you should return 5, since you could buy the stock at 5 dollars and
     * sell it at 10 dollars.
     *
     * @param prices
     * @return
     */
    int maxProfit(int[] prices) {
        //running max of profit - need to keep track of best profit and min element so far
        int min = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit = Math.max(profit, prices[i] - min);
            min = Math.min(prices[i], min);
        }
        return profit;
    }

    /**
     * This problem was asked by Amazon.
     *
     * Given an array of numbers, find the maximum sum of any contiguous subarray of the array.
     *
     * For example, given the array [34, -50, 42, 14, -5, 86], the maximum sum would be 137, since we would take
     * elements 42, 14, -5, and 86.
     *
     * Given the array [-5, -1, -8, -9], the maximum sum would be 0, since we would not take any elements.
     *
     * Do this in O(N) time.
     *
     * @param arr
     * @return
     */
    int maxContiguousSubArraySum(int[] arr) {
        if (arr.length == 0)
            return 0;
        //idea is following on every next step if we know max sum of previous sub-arrays the next max sum can be
        //either max + a[i] or a[i]. So keep tracking of running sum and global max sum
        int maxRunning = arr[0] > 0 ? arr[0] : 0;
        int maxGlobal = maxRunning;
        for (int i = 1; i < arr.length; i++) {
            maxRunning = Math.max(arr[i], maxRunning + arr[i]);
            if (maxRunning > maxGlobal)
                maxGlobal = maxRunning;
        }
        return maxGlobal;
    }

    /**
     * This problem was asked by Microsoft.
     *
     * Suppose an arithmetic expression is given as a binary tree. Each leaf is an integer and each internal node
     * is one of '+', '−', '∗', or '/'.
     *
     * Given the root to such a tree, write a function to evaluate it.
     *
     * For example, given the following tree:
     *
     *     *
     *    / \
     *   +    +
     *  / \  / \
     * 3  2  4  5
     * You should return 45, as it is (3 + 2) * (4 + 5).
     *
     * @param n
     * @return
     */
    public float compute(BSTNodeChar n) {
        if (n.right == null && n.left == null) {
            return n.val - '0';
        }
        float left = compute(n.left);
        float right = compute(n.right);
        switch (n.val) {
            case '+': return left + right;
            case '-': return left - right;
            case '*': return left * right;
            case '/': return left / right;
            default: return .0f;
        }
    }

    /**
     * This problem was asked by Facebook.
     *
     * Given a function that generates perfectly random numbers between 1 and k (inclusive), where k is an input,
     * write a function that shuffles a deck of cards represented as an array using only swaps.
     *
     * It should run in O(N) time.
     *
     * Hint: Make sure each one of the 52! permutations of the deck is equally likely.
     *
     * @param deck
     * @return
     */
    public int[] shuffle(int[] deck) {
        if (deck.length < 2)
            return deck;
        for (int i = 1; i < deck.length; i++) {
            int randIndex = getRandom(i) - 1;
            int tmp = deck[randIndex];
            deck[randIndex] = deck[i];
            deck[i] = tmp;
        }
        return deck;
    }

    int getRandom(int k) {
        Random rand = new Random();
        return 1 + rand.nextInt(k);
    }

    /**
     * This problem was asked by Dropbox.
     *
     * Sudoku is a puzzle where you're given a partially-filled 9 by 9 grid with digits. The objective is to fill the
     * grid with the constraint that every row, column, and box (3 by 3 subgrid) must contain all of the digits from 1 to 9.
     *
     * Implement an efficient sudoku solver.
     *
     * @param board
     * @return
     */
    public boolean solveSudoku(int[][] board) {
        //- check if board is filled - it's solved
        //- find unfilled cell (count 0 as unfilled)
        //- try numbers from 1 to 9, check if num is allowed
        //- fill it with next num
        //- call solve recursively
        //- if not valid - erase and backtrack
        //Time complexity is O(n^m), where n is possible options for nums (1..9) and m is number of unfilled cells
        boolean hasUnfilled = false;
        for (int row = 0; row < board.length; row++ ) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == 0)
                    hasUnfilled = true;
            }
        }
        if (!hasUnfilled)
            return true;

        for (int row = 0; row < board.length; row++ ){
            for (int col = 0; col < board[0].length; col++ ){
                //if cell is unfilled - start our magic
                if (board[row][col] == 0) {
                    //check every possible number
                    for (int num = 1; num <=9; num++) {
                        //check if it's valid - no matches in column, row and 3x3 box
                        if (checkNum(board, num, row, col)) {
                            //try to set num and go to next cell
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            }
                            //no bueno - unset and continue
                            board[row][col] = 0;
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean checkNum(int[][] board, int num, int row, int col) {
        //check row
        for (int rowCount = 0; rowCount < board.length; rowCount++ ) {
            if (board[rowCount][col] == num) {
                return false;
            }
        }
        //check col
        for (int colCount = 0; colCount < board.length; colCount++ ) {
            if (board[row][colCount] == num) {
                return false;
            }
        }
        //check box
        int boxRow = row / 3;
        int boxCol = col / 3;
        for (int rowCount = boxRow * 3; rowCount < (boxRow +1)* 3; rowCount++ ) {
            for (int colCount = boxCol * 3; colCount < (boxCol + 1) * 3; colCount++ ) {
                if (board[rowCount][colCount] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This problem was asked by Google.
     *
     * Given an undirected graph represented as an adjacency matrix and an integer k, write a function to determine
     * whether each vertex in the graph can be colored such that no two adjacent vertices share the same color using
     * at most k colors.
     *
     * @param g
     * @param k
     * @return
     */
    boolean checkColor(int[][] g, int k) {
        //idea is to use recursion + backtracking
        //solution has - vertex as index, color as value
        int[] solution = new int[g.length];
        boolean res = color(g, 0, solution, k);
        System.out.print("Solution : [ ");
        for (int i = 0; i < solution.length; i++) {
            System.out.print(i + "=" + solution[i]+", ");
        }
        System.out.println(" ]");
        return res;
    }

    boolean color(int[][] g, int v, int[] sol, int m) {
        //if we checked all vertexes means we good with solution
        if (v >= sol.length)
            return true;
        //check every color in sequence
        for(int c = 1; c <= m; c++) {
            //check if vertex can be colored (but don't color it actually). check is following -
            //if any adjacent vertex to this vertex is of the same color
            if (isSave(sol, v, c, g, v)) {
                //if it's ok - then color the vertex and go on next level of recursion
                sol[v] = c;
                if (color(g, v + 1, sol, m))
                    return true;
            }
            //if no bueno - uncolor the vertex
            sol[v] = 0;
        }

        return false;
    }

    boolean isSave(int[] sol, int v, int c, int[][] graph, int m) {
        for (int adjC = 0; adjC < graph.length; adjC++) {
            if (adjC == v)
                continue;
            if (graph[adjC][v] == 1 && sol[adjC] == c)
                return false;
        }

        return true;
    }

    /**
     * This problem was asked by Amazon.
     *
     * Given a string s and an integer k, break up the string into multiple texts such that each text has a length of k
     * or less. You must break it up so that words don't break across lines. If there's no way to break the text up,
     * then return null.
     *
     * You can assume that there are no spaces at the ends of the string and that there is exactly one space between
     * each word.
     *
     * For example, given the string "the quick brown fox jumps over the lazy dog" and k = 10, you should return:
     * ["the quick", "brown fox", "jumps over", "the lazy", "dog"]. No string in the list has a length of more than 10.
     *
     * @param s
     * @param k
     * @return
     */
    public List<String> lines(String s, int k) {
        List<String> res = new ArrayList();
        String[] words = s.split(" ");
        if (fillLines(res, k, 0, words))
            return res;
        else
            return null;
    }

    boolean fillLines(List<String> res, int k, int index, String[] words) {
        if (index >= words.length) {
            return true;
        }
        int accuLength = 0;
        int wordCount = 0;
        while(index < words.length) {
            int newLength = accuLength == 0 ? 0 : 1;
            newLength += accuLength + words[index].length();
            if (newLength <= k) {
                wordCount++;
                accuLength = newLength;
                index++;
            } else {
                break;
            }
        }
        if (wordCount == 0)
            return false;
        StringBuilder sb = new StringBuilder();
        for (int i = index - wordCount; i < index; i++) {
            sb.append(words[i]);
            if ( i < index - 1)
                sb.append(" ");
        }
        res.add(sb.toString());
        return fillLines(res, k, index, words);
    }

    /**
     * This problem was asked by Amazon.
     *
     * An sorted array of integers was rotated an unknown number of times.
     *
     * Given such an array, find the index of the element in the array in faster than linear time.
     * If the element doesn't exist in the array, return null.
     *
     * For example, given the array [13, 18, 25, 2, 8, 10] and the element 8, return 4 (the index of 8 in the array).
     *
     * You can assume all the integers in the array are unique.
     *
     */
    int getIndex(int[] arr, int k) {
        //idea is to use double binary searches. First we find the index of initial array start using first binary search
        //then run second binary search for the half of the array that probably has our element
        int l = 0, r = arr.length - 1;
        int s = -1;
        while (l < r) {
            int m = l + (r-l)/2;
            if (arr[m] > arr[m+1]) {
                s = m + 1;
                break;
            }
            else if (arr[l] > arr[m])
                r = m;
            else
                l = m;
        }
        //in case we missed our element in loop - it must be in r
        if (s == -1)
            s = r;

        //choose between array halves
        if (arr[arr.length - 1] >= k) {
            r = arr.length - 1;
            l = s;
        }
        else {
            l = 0;
            r = s;
        }
        //start second binary search for the element itself
        while (l <= r) {
            int m = (r + l)/2;
            if (arr[m] == k)
                return m;
            else if (arr[m] < k)
                l = m + 1;
            else
                r = m - 1;
        }
        return -1;
    }

    /**
     * This problem was asked by Facebook.
     *
     * Given a multiset of integers, return whether it can be partitioned into two subsets whose sums are the same.
     *
     * For example, given the multiset {15, 5, 20, 10, 35, 15, 10}, it would return true, since we can split it up into
     * {15, 5, 10, 15, 10} and {20, 35}, which both add up to 55.
     *
     * Given the multiset {15, 5, 20, 10, 35}, it would return false, since we can't split it up into two subsets that
     * add up to the same sum.
     *
     * @param arr
     * @return
     */
    boolean isEqualSums(int[] arr) {
        /*int sum = 0;
        for(int i : arr)
            sum += i;

        if (sum % 2 == 1)
            return false;
        boolean check = check(arr, arr.length, sum/2);
        return check;*/
        return isEqualSumsDP(arr);
    }

    boolean check(int[] arr, int n, int sum) {
        if (sum == 0)
            return true;
        if (n == 0 && sum != 0) {
            return false;
        }

        if (arr[n - 1] > sum)
            return check(arr, n - 1, sum);

        return check(arr, n - 1, sum) || check(arr, n - 1, sum - arr[n - 1]);
    }

    boolean isEqualSumsDP(int[] arr) {
        int sum = 0;
        for(int i : arr)
            sum += i;

        if (sum % 2 == 1)
            return false;
        boolean[][] memo = new boolean[arr.length + 1][sum/2 + 1];
        for (int i = 0; i <= arr.length; i++)
            memo[i][0] = true;

        for (int j = 1; j <= sum / 2; j++) {
            for (int i = 1; i <= arr.length; i++) {
                memo[i][j] = memo[i - 1][ j];
                if (j >= arr[i - 1]) {
                    memo[i][j] = memo[i][j] || memo[i-1][j - arr[i - 1]];
                }
            }
        }

        for (int j = 0; j <= sum / 2; j++) {
            for (int i = 0; i <= arr.length; i++) {
                System.out.print(memo[i][j] +",");
            }
            System.out.println("");
        }
        return memo[arr.length][sum/2];
    }

    /**
     * This problem was asked by Google.
     *
     * Implement integer exponentiation. That is, implement the pow(x, y) function, where x and y are integers
     * and returns x^y.
     *
     * Do this faster than the naive method of repeated multiplication.
     *
     * @param x
     * @param y
     * @return
     */
    float myPow(int x, int y) {
        return helper(x, y);
    }

    float helper(float x, int y) {
        //idea is to use divide n conquer approach: to calculate x^y we do x^(y/2) * x^(y/2). We build
        //recursion tree down to 0 and 1 base cases, then join results.
        //In case if y is odd to add additional x*
        //if y < 0 we change *x to /x
        if (y == 0) return 1;
        if (y == 1) return x;
        int yhalf = y / 2;
        float halfRes = helper(x, yhalf);
        if (y % 2 == 0)
            return halfRes * halfRes;
        else {
            if (y > 0)
                return halfRes * halfRes * x;
            else
                return halfRes * halfRes / x;
        }
    }

    public static void main(String[] args) {
        SolutionDailyCodingSeptember2018 obj = new SolutionDailyCodingSeptember2018();

        int inversions;
        inversions = obj.countInversions(new int[]{1, 3, 2});
        System.out.println(inversions);

        inversions = obj.countInversions(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println(inversions);

        inversions = obj.countInversions(new int[]{6, 5, 4, 3, 2, 1});
        System.out.println(inversions);

        inversions = obj.countInversions(new int[]{2, 4, 1, 3, 5});
        System.out.println(inversions);

        System.out.println("--------- random_7 from random_5 ---------");
        /*List<Integer> randNums = new ArrayList<>();
        Map<Integer, Integer> numCount = new HashMap();
        int _NUM_OF_TRIES = 1 << 24;
        for (int i = 0; i < _NUM_OF_TRIES; i++) {
            int nextNum = obj.random_7();
            randNums.add(nextNum);
            if (numCount.containsKey(nextNum)) {
                int count = numCount.get(nextNum);
                numCount.put(nextNum, count+1);
            } else
                numCount.put(nextNum, 1);
        }
        for (int num: numCount.keySet()) {
            System.out.println(num + " = " + (float)numCount.get(num)/ _NUM_OF_TRIES );
        }

        /*System.out.println("---- longest palindrome in string -----------------");
        String s = "";
        s = "abcdefed1234567890";
        System.out.println(obj.getLongestPalindrome(s));

        s = "abcdefgh";
        System.out.println(obj.getLongestPalindrome(s));

        s = "a1234554wertasdf1234564321as";
        System.out.println(obj.getLongestPalindrome(s));

        s = "abcdef_12321";
        System.out.println(obj.getLongestPalindrome(s));

        s = "ALICE was beginning to get very tired of\n" +
                "sitting by her sister on the bank, and of having\n" +
                "nothing to do: once or twice she had peeped into\n" +
                "the book her sister was reading, but it had no\n" +
                "pictures or conversations in it, “ and what is\n" +
                "B\n" +
                "Navigate Control Internet Digital Interface by BookVirtual Corp. U.S. Patent Pending. © 2000 All Rights Reserved.\n" +
                "Fit Page Full Screen On/Off Close Book\n" +
                "Navigate Control Internet\n" +
                "burning with curiosity, she ran across the field\n" +
                "after it, and was just in time to see it pop\n" +
                "down a large rabbit-hole under the hedge.\n" +
                "In another moment down went Alice after\n" +
                "it, never once considering how in the world\n" +
                "she was to get out again.\n" +
                "The rabbit-hole went straight on like a\n" +
                "tunnel for some way, and then dipped suddenly\n" +
                "down, so suddenly that Alice had not a moment\n" +
                "to think about stopping herself before she found\n" +
                "herself falling down what seemed to be a very\n" +
                "deep well.\n" +
                "Either the well was very deep, or she fell\n" +
                "very slowly, for she had plenty of time as she\n" +
                "went down to look about her, and to wonder\n" +
                "what was going to happen next. First, she tried\n" +
                "to look down and make out what she was\n" +
                "coming to, but it was too dark to see anything :\n" +
                "then she looked at the sides of the well, and\n" +
                "noticed that they were filled with cupboards\n" +
                "and bookshelves: here and there she saw maps\n" +
                "and pictures hung upon pegs. She took down\n";
        System.out.println(obj.getLongestPalindrome(s)); */

        /*System.out.println("---- max profit of selling stocks ones -----");
        System.out.println(obj.maxProfit(new int[] {9, 11, 8, 5, 7, 10 ,9 }));//5
        System.out.println(obj.maxProfit(new int[] {8, 4, 1, 3, 1, 10, 5, 6, 9, 1 }));//9
        System.out.println(obj.maxProfit(new int[] {8, 4, 3, 8, 5, 1, 2, 1, 7, 1 }));//6
        */

        System.out.println("----- max sum of contiguous subarray");
        System.out.println(obj.maxContiguousSubArraySum(new int[] {1, -3, 2, 1, -1 }));//3
        System.out.println(obj.maxContiguousSubArraySum(new int[] {34, -50, 42, 14, -5, 86}));//137
        System.out.println(obj.maxContiguousSubArraySum(new int[] {-5, -1, -8, -9}));//0

        System.out.println(" ----- compute result from tree -----------");
        BSTNodeChar tree = new BSTNodeChar('*', new BSTNodeChar('2'), new BSTNodeChar('4'));
        System.out.println(obj.compute(tree));

        tree = new BSTNodeChar('*', new BSTNodeChar('+', new BSTNodeChar('3'), new BSTNodeChar('5')), new BSTNodeChar('4'));
        System.out.println(obj.compute(tree));

        tree = new BSTNodeChar('*',
                new BSTNodeChar('+',
                        new BSTNodeChar('3'), new BSTNodeChar('5')),
                new BSTNodeChar('-',
                        new BSTNodeChar('9'), new BSTNodeChar('7')));
        System.out.println(obj.compute(tree));

        System.out.println("----- card deck shuffling -----");
        int[] deck = new int[52];
        for (int i = 0; i < deck.length; i++)
            deck[i] = i;

        deck = obj.shuffle(deck);
        long counter = 0;
        for(int card : deck) {
            System.out.print(card + ", " );
            if ((counter & (1l << card)) > 0 )
                System.out.println("Duplicate found " + card);
            counter |= (1l << card);
        }
        System.out.println("\n -- next deck ----- ");
        for (int i = 0; i < deck.length; i++)
            deck[i] = i;
        deck = obj.shuffle(deck);
        counter = 0;
        for(int card : deck) {
            System.out.print(card + ", ");
            if ((counter & (1l << card)) > 0 )
                System.out.println("Duplicate found " + card);
            counter |= (1l << card);
        }

        System.out.println("----- solve sudoku -------");
        int[][] sudoku = {
                {2, 3, 0, 4, 1, 5, 0, 6, 8},
                {0, 8, 0, 2, 3, 6, 5, 1, 9},
                {1, 6, 0, 9, 8, 7, 2, 3, 4,},
                {3, 1, 7, 0, 9, 4, 0, 2, 5},
                {4, 5, 8, 1, 2, 0, 6, 9, 7},
                {9, 2, 6, 0, 5, 8, 3, 0, 1},
                {0, 0, 0, 5, 0, 0, 1, 0, 2},
                {0, 0, 0, 8, 4, 2, 9, 0, 3},
                {5, 9, 2, 3, 7, 1, 4, 8, 6}
        };
        System.out.print("initial board \n" + StringUtils.int2DArrayToString(sudoku));
        boolean isSudoku = obj.solveSudoku(sudoku);
        if (isSudoku){
            System.out.println("solution:\n");
            System.out.println(StringUtils.int2DArrayToString(sudoku));
        }
        else
            System.out.println("Board is not solvable");

        //---modif #1
        sudoku = new int[][] {
                {2, 3, 0, 4, 1, 5, 0, 6, 8},
                {0, 8, 0, 2, 3, 6, 5, 1, 9},
                {1, 6, 0, 9, 8, 7, 2, 3, 4,},
                {3, 1, 7, 0, 9, 4, 0, 2, 5},
                {4, 5, 8, 1, 2, 0, 6, 9, 7},
                {9, 2, 6, 0, 5, 8, 3, 0, 1},
                {0, 0, 0, 5, 0, 0, 1, 0, 2},
                {0, 0, 0, 8, 4, 0, 9, 0, 3},
                {0, 9, 0, 3, 7, 1, 4, 8, 6}
        };
        /*System.out.print("initial board \n" + StringUtils.int2DArrayToString(sudoku));
        isSudoku = obj.solveSudoku(sudoku);
        if (isSudoku){
            System.out.println("solution:\n");
            System.out.println(StringUtils.int2DArrayToString(sudoku));
        }
        else
            System.out.println("Board is not solvable");
*/
        /*sudoku = new int[][]{
                {3, 0, 7, 0, 2, 5, 6, 4, 9},
                {2, 4, 0, 3, 0, 0, 1, 0, 8},
                {0, 1, 0, 0, 8, 6, 2, 3, 7},
                {1, 2, 0, 7, 5, 4, 3, 0, 6},
                {0, 0, 3, 2, 0, 0, 7, 0, 4},
                {4, 0, 9, 0, 3, 0, 5, 2, 1},
                {0, 3, 1, 9, 4, 0, 8, 6, 5},
                {6, 5, 0, 8, 7, 3, 9, 1, 2},
                {0, 9, 0, 5, 0, 1, 4, 7, 3}};
        System.out.print("initial board \n" + StringUtils.int2DArrayToString(sudoku));
        isSudoku = obj.solveSudoku(sudoku);
        if (isSudoku){
            System.out.println("solution:\n");
            System.out.println(StringUtils.int2DArrayToString(sudoku));
        }
        else
            System.out.println("Board is not solvable");*/

        int[][] graph = new int[][] {
                {1, 1, 0, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1},
                {1, 1, 1, 1}
        };
        System.out.println("---- graph coloring -----");
        System.out.println(obj.checkColor(graph, 3));
        System.out.println(obj.checkColor(graph, 2));
        System.out.println(obj.checkColor(graph, 1));

        graph = new int[][] {
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 1, 1},
                {1, 0, 1, 1, 0},
                {0, 0, 1, 0, 1}
        };
        System.out.println("---- graph coloring -----");
        System.out.println(obj.checkColor(graph, 3));
        System.out.println(obj.checkColor(graph, 2));
        System.out.println(obj.checkColor(graph, 1));

        graph = new int[][] {
                {1, 1, 1, 1, 0},
                {1, 1, 0, 0, 0},
                {1, 0, 1, 1, 1},
                {1, 0, 1, 1, 0},
                {0, 0, 1, 0, 1}
        };
        System.out.println("---- graph coloring -----");
        System.out.println(obj.checkColor(graph, 3));
        System.out.println(obj.checkColor(graph, 2));

        graph = new int[][] {
                {1, 1, 1, 1, 0},
                {1, 1, 0, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 0, 1, 1, 0},
                {0, 1, 1, 0, 1}
        };
        System.out.println("---- graph coloring -----");
        System.out.println(obj.checkColor(graph, 3));
        System.out.println(obj.checkColor(graph, 2));

        graph = new int[][] {
                {1, 1, 1, 1, 1},
                {1, 1, 0, 1, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 0, 1}
        };
        System.out.println("---- graph coloring -----");
        System.out.println(obj.checkColor(graph, 4));

        graph = new int[][] {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        System.out.println("---- graph coloring -----");
        System.out.println(obj.checkColor(graph, 5));

        System.out.println("-------- form lines of length k from string array ----------");
        String words = "the quick brown fox jumps over the lazy dog";
        List<String> lines = obj.lines(words, 10);
        if (lines == null)
            System.out.println("Lines are not possible");
        else {
            for (String line : lines) {
                System.out.println("[ " + line + " ]");
            }
        }

        System.out.println(" ---- find index of lement in rotated array -------");
        int[] arr = new int[] {13, 18, 25, 2, 8, 10};
        System.out.println(obj.getIndex(arr, 8));
        System.out.println(obj.getIndex(arr, 18));
        System.out.println(obj.getIndex(arr, 5));

        arr = new int[] {16, 18, 25, 26, 1, 2, 8, 10, 12, 14};
        System.out.println(obj.getIndex(arr, 8));
        System.out.println(obj.getIndex(arr, 18));
        System.out.println(obj.getIndex(arr, 5));
        System.out.println(obj.getIndex(arr, 14));
        System.out.println(obj.getIndex(arr, 16));
        System.out.println(obj.getIndex(arr, 15));

        arr = new int[] {15, 20, 2, 3, 5, 7, 11, 13};
        System.out.println(obj.getIndex(arr, 15));

        System.out.println("----- sub-arrays of array that form equal sums -------");
        /*System.out.println(obj.isEqualSums(new int[] {10, 1, 5, 1, 7}));

        System.out.println(obj.isEqualSums(new int[] {10, 1, 5, 1, 7, 6}));

        System.out.println(obj.isEqualSums(new int[] {1, 10, 1}));
        */
        System.out.println(obj.isEqualSums(new int[] {1, 3, 4, 6}));

        System.out.println("------- custom pow(x, y) function -------");
        System.out.println(obj.myPow(4, 3));
        System.out.println(obj.myPow(4, -3));
        System.out.println(obj.myPow(9, 16));
        System.out.println(obj.myPow(10, 12));
        System.out.println(obj.myPow(81, -2));
    }
}
