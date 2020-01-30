package arrays;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Larry has been given a permutation of a sequence of natural numbers incrementing from  as an
 * array. He must determine whether the array can be sorted using the following operation any
 * number of times:
 *
 * Choose any  consecutive indices and rotate their elements in such a way that .
 * For example, if :
 *
 * A		rotate
 * [1,6,5,2,4,3]	[6,5,2]
 * [1,5,2,6,4,3]	[5,2,6]
 * [1,2,6,5,4,3]	[5,4,3]
 * [1,2,6,3,5,4]	[6,3,5]
 * [1,2,3,5,6,4]	[5,6,4]
 * [1,2,3,4,5,6]
 *
 * YES
 * On a new line for each test case, print YES if  can be fully sorted. Otherwise, print NO.
 *
 * Function Description
 *
 * Complete the larrysArray function in the editor below. It must return a string, either YES or NO.
 *
 * larrysArray has the following parameter(s):
 *
 * A: an array of integers
 * Input Format
 *
 * The first line contains an integer , the number of test cases.
 *
 * The next  pairs of lines are as follows:
 *
 * The first line contains an integer , the length of .
 * The next line contains  space-separated integers .
 * Constraints
 *
 *  integers that increment by  from  to
 * Output Format
 *
 * For each test case, print YES if  can be fully sorted. Otherwise, print NO.
 *
 * Sample Input
 *
 * 3
 * 3
 * 3 1 2
 * 4
 * 1 3 4 2
 * 5
 * 1 2 3 5 4
 * Sample Output
 *
 * YES
 * YES
 * NO
 * Explanation
 *
 * In the explanation below, the subscript of  denotes the number of operations performed.
 *
 * Test Case 0:
 *
 *  is now sorted, so we print  on a new line.
 *
 * Test Case 1:
 * .
 * .
 *  is now sorted, so we print  on a new line.
 *
 * Test Case 2:
 * No sequence of rotations will result in a sorted . Thus, we print  on a new line.
 */
public class SortableWithRotations {

    /**
     * There is an invariant as per this link below:
     * https://www.cs.bham.ac.uk/~mdr/teaching/modules04/java2/TilesSolvability.html
     *
     * Idea is - count inversions in the original array position, then as "empty" space
     * is on the last row we are looking for the even numbers of it for the problem to be
     * solvable
     *
     * @param A
     * @return
     */
    static String larrysArray(int[] A) {
        int inversions = 0;
        for (int i = 0; i < A.length; i++)
            for (int j = i + 1; j < A.length; j++)
                if (A[j] < A[i]) ++inversions;

        return inversions % 2 == 0 ? "YES" : "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] A = new int[n];

            String[] AItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int AItem = Integer.parseInt(AItems[i]);
                A[i] = AItem;
            }

            String result = larrysArray(A);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
