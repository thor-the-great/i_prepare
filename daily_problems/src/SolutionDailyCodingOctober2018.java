import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SolutionDailyCodingOctober2018 {

    /**
     * This problem was asked by Apple.
     *
     * Suppose you have a multiplication table that is N by N. That is, a 2D array where the value at the i-th row and
     * j-th column is (i + 1) * (j + 1) (if 0-indexed) or i * j (if 1-indexed).
     *
     * Given integers N and X, write a function that returns the number of times X appears as a value in an N by N
     * multiplication table.
     *
     * For example, given N = 6 and X = 12, you should return 4, since the multiplication table looks like this:
     *
     * | 1 | 2 | 3 | 4 | 5 | 6 |
     *
     * | 2 | 4 | 6 | 8 | 10 | 12 |
     *
     * | 3 | 6 | 9 | 12 | 15 | 18 |
     *
     * | 4 | 8 | 12 | 16 | 20 | 24 |
     *
     * | 5 | 10 | 15 | 20 | 25 | 30 |
     *
     * | 6 | 12 | 18 | 24 | 30 | 36 |
     *
     * And there are 4 12's in the table.
     *
     * @param N
     * @param X
     * @return
     *
     */
    int numInGrid(int N, int X) {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (X % i == 0 && X / i <= N)
                count++;
        }
        return count;
        //return seiveBasedComplex(N, X);
    }

    private int seiveBasedComplex(int N, int X) {
        //calculate all prime numbers using seive method
        boolean[] seive = new boolean[X + 1];
        Arrays.fill(seive, true);
        //primes will be marked as true, then eliminate not primes
        for (int i = 2; i*i < X; i++) {
            if (seive[i]) {
                for (int j = i*2; j < X; j += i) {
                    seive[j] = false;
                }
            }
        }
        //now iterate over primes, divisors are factors of primes
        int numOfDivisors = 1;
        Set<Integer> usedNums = new HashSet();
        int n = X;
        for (int p = 2; p <= N; p++) {
            if (seive[p]) {
                int count = 0;
                if (X % p == 0) {
                    usedNums.add(p);
                    while (n % p == 0 ) {
                        n = n / p;
                        if (!usedNums.contains(n) && n <= N && X/n <= N) {
                            count++;
                            usedNums.add(n);
                        }
                    }
                    if (count > 0)
                        numOfDivisors *= count;
                }
            }
        }
        int result = 2*numOfDivisors;
        //check that it's not in the table diagonal
        double sqrt = Math.sqrt(X);
        if (sqrt - Math.floor(sqrt) == 0 && result > 0) {
            result -= 1;
        }
        return result;
    }

    /**
     * This problem was asked by Microsoft.
     *
     * Given an array of numbers, find the length of the longest increasing subsequence in the array.
     * The subsequence does not necessarily have to be contiguous.
     *
     * For example, given the array [0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15], the longest increasing
     * subsequence has length 6: it is 0, 2, 6, 9, 11, 15.
     *
     * @param arr
     * @return
     */
    int getLIS(int[] arr) {
        //return getLISRecursiveSlow(arr);
        //return getLISDP(arr);
        return getLISBSDP(arr);
    }

    private int getLISBSDP(int[] arr) {
        //idea is to construct the array with increasing sequence. The array is not correct, on every iteration we can
        //replace some elements. Position of the element to replace is given by the binary search.
        //time - O(log(n)) BS + O(1), n times => O(n*logn)
        int n = arr.length;
        int[] increasingArr = new int[n];
        int right = 0;
        for( int el : arr) {
            int index = Arrays.binarySearch(increasingArr, 0, right, el);
            if (index < 0)
                index = - index - 1;
            increasingArr[index] = el;
            if (index == right)
                right++;
        }
        return right;
    }

    private int getLISDP(int[] arr) {
        //dp solution based on optimized recursive solution
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int max = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        for (int dpEl : dp) if (dpEl > max) max = dpEl;
        return max;
    }

    private int getLISRecursiveSlow(int[] arr) {
        max = 1;
        check(arr, arr.length);
        return max;
    }

    int check(int[] arr, int index) {
        if (index == 1) return 1;
        int res, max_here = 1;
        for (int i = 1; i < index; i++) {
            res = check(arr, i);
            if (arr[i - 1] < arr[index - 1] && res + 1 > max_here){
                max_here = res + 1;
            }
            if (max_here > max) max = max_here;
        }
        return max_here;
    }

    int max;


    public static void main(String[] args) {
        SolutionDailyCodingOctober2018 obj = new SolutionDailyCodingOctober2018();

        System.out.println("------ find number of times number X is in grid of multiplication N by N ---------");
        System.out.println("N=6, X=12; " + obj.numInGrid(6, 12));
        System.out.println("N=6, X=6; " + obj.numInGrid(6, 6));
        System.out.println("N=6, X=8; " + obj.numInGrid(6, 8));
        System.out.println("N=6, X=30; " + obj.numInGrid(6, 30));
        System.out.println("N=8, X=16; " + obj.numInGrid(8, 16));
        System.out.println("N=10, X=24; " + obj.numInGrid(10, 24));
        System.out.println("N=15, X=20; " + obj.numInGrid(15, 20));

        System.out.println("------ get Longest increasing sequence (LIS) ---------");
        System.out.println(obj.getLIS(new int[] {3, 1, 4}));
        System.out.println(obj.getLIS(new int[] {3, 2, 4, 6, 1, 9, 5}));
        System.out.println(obj.getLIS(new int[] {3, 2}));
        System.out.println(obj.getLIS(new int[] {8, 6, 4, 2, 1}));
        System.out.println(obj.getLIS(new int[] {1, 3, 2, 4, 5, 6, 7}));
        System.out.println(obj.getLIS(new int[] {10, 7, 2, 6, 1, 0}));
    }
}
