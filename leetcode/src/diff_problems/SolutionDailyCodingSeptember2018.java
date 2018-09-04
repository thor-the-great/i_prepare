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

    public static void main(String[] args) {
        SolutionDailyCodingSeptember2018 obj = new SolutionDailyCodingSeptember2018();

        int inversions =0;
        inversions = obj.countInversions(new int[]{1, 3, 2});
        System.out.println(inversions);

        inversions = obj.countInversions(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println(inversions);

        inversions = obj.countInversions(new int[]{6, 5, 4, 3, 2, 1});
        System.out.println(inversions);

        inversions = obj.countInversions(new int[]{2, 4, 1, 3, 5});
        System.out.println(inversions);

        System.out.println("--------- random_7 from random_5 ---------");
        List<Integer> randNums = new ArrayList<>();
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
    }
}
