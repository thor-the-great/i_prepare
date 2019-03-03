import cracking.trees_graphs.DiGraph;
import diff_problems.TreeNode;
import graphs.GraphUtils;

import java.util.*;

public class SolutionDailyCodingMarch2019 {

    /**
     * This problem was asked by Amazon.
     *
     * Given a sorted array, find the smallest positive integer that is not the sum of a subset of the array.
     *
     * For example, for the input [1, 2, 3, 10], you should return 7.
     *
     * Do this in O(N) time.
     *
     * @param arr
     * @return
     */
    public int smallestNonSum(int[] arr) {
        /**
         * Idea - start from smallest number 1. Check every next array element two options:
         * - if arr[i] > res - this means res can't be represented neither by this element nor by next one (because all
         * next elements are greater). So we just return res
         * - else we add arr[i] to res and continue to next i.
         */
        int N = arr.length;
        int res = 1;
        for (int i = 0; i < N; i++) {
            if (arr[i] > res)
                break;
            res += arr[i];
        }

        return res;
    }

    /**
     * This problem was asked by Bloomberg.
     *
     * There are N prisoners standing in a circle, waiting to be executed. The executions are carried out starting
     * with the kth person, and removing every successive kth person going clockwise until there is no one left.
     *
     * Given N and k, write an algorithm to determine where a prisoner should stand in order to be the last survivor.
     *
     * For example, if N = 5 and k = 2, the order of executions would be [2, 4, 1, 5, 3], so you should return 3.
     *
     * Bonus: Find an O(log N) solution if k = 2.
     * @return
     */
    public int positionToExecuteLast(int N, int k) {
        if (N == 1)
            return 1;

        return ((positionToExecuteLast(N - 1, k) + k - 1 )% N) + 1 ;
    }

    /**
     * This problem was asked by Bloomberg.
     *
     * There are N prisoners standing in a circle, waiting to be executed. The executions are carried out starting
     * with the kth person, and removing every successive kth person going clockwise until there is no one left.
     *
     * Given N and k, write an algorithm to determine where a prisoner should stand in order to be the last survivor.
     *
     * For example, if N = 5 and k = 2, the order of executions would be [2, 4, 1, 5, 3], so you should return 3.
     *
     * Bonus: Find an O(log N) solution if k = 2.
     * @return
     */
    public int positionToExecuteLastTwo(int N) {
        if (N == 1)
            return 1;

        if (N % 2 == 0)
            return 2*positionToExecuteLastTwo(N / 2) - 1;
        else
            return 2*positionToExecuteLastTwo(N / 2) + 1;
    }

    public static void main(String[] args) {
        SolutionDailyCodingMarch2019 obj = new SolutionDailyCodingMarch2019();

        System.out.println("---- smallest int that is not a sum of subset, array is sorted ----");
        int[] arr;
        arr = new int[] {1, 1, 3, 4, 5};
        System.out.println(obj.smallestNonSum(arr));

        arr = new int[] {1, 3, 4};
        System.out.println(obj.smallestNonSum(arr));//2

        arr = new int[] {1, 1, 3, 4};
        System.out.println(obj.smallestNonSum(arr));//10

        arr = new int[] {1, 1, 1, 1};
        System.out.println(obj.smallestNonSum(arr));//5

        arr = new int[] {1, 2, 5, 10, 20, 40};
        System.out.println(obj.smallestNonSum(arr));//4

        System.out.println("--- position to be executed last ---");
        System.out.println(obj.positionToExecuteLast(5, 2)); //3

        System.out.println(obj.positionToExecuteLast(7, 3)); //4

        System.out.println(obj.positionToExecuteLastTwo(5));//3
        System.out.println(obj.positionToExecuteLastTwo(6));//5
    }
}
