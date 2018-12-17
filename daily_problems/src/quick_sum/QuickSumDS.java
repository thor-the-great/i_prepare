package quick_sum;

/**
 * This problem was asked by Goldman Sachs.
 *
 * Given a list of numbers L, implement a method sum(i, j) which returns the sum from the sublist L[i:j] (including i,
 * excluding j).
 *
 * For example, given L = [1, 2, 3, 4, 5], sum(1, 3) should return sum([2, 3]), which is 5.
 *
 * You can assume that you can do some pre-processing. sum() should be optimized over the pre-processing step.
 */
public class QuickSumDS {
    int[] sums;

    /**
     * Idea - create array of prefix sums. Then for every element of array it's sum will be sums[j] - sums[i] because
     * all other elements are cancelled
     * @param arr
     */
    public QuickSumDS(int[] arr) {
        int N = arr.length;
        sums = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            sums[i] = sums[i - 1] + arr[i - 1];
        }
    }

    public int sum(int i, int j) {
        if (j <= i || i >= sums.length || j >= sums.length)
            return -1;
        return sums[j] - sums[i];
    }

    public static void main(String[] args) {
        int[] arr = new int[] {3, 1, 4, 5, 7, 3, 2, 1, 8};
        QuickSumDS obj = new QuickSumDS(arr);
        System.out.println(obj.sum(0, 4)); //13
        System.out.println(obj.sum(2, 4)); //9
        System.out.println(obj.sum(2, 5)); //16
        System.out.println(obj.sum(0, 9)); //34
    }
}
