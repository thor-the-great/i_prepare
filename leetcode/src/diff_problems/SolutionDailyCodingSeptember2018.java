package diff_problems;

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

        System.out.println("---- max profit of selling stocks ones -----");

        System.out.println(obj.maxProfit(new int[] {9, 11, 8, 5, 7, 10 ,9 }));//5
        System.out.println(obj.maxProfit(new int[] {8, 4, 1, 3, 1, 10, 5, 6, 9, 1 }));//9
        System.out.println(obj.maxProfit(new int[] {8, 4, 3, 8, 5, 1, 2, 1, 7, 1 }));//6
    }
}
