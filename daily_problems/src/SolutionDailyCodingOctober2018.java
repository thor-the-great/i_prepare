import linked_list.ListNode;
import linked_list.StringUtils;

import java.util.*;

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
        Set<Integer> usedNums = new HashSet<>();
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
        int rightPointer = 0;
        for( int arrayElement : arr) {
            int indexToInsert = -(Arrays.binarySearch(increasingArr, 0, rightPointer, arrayElement)) - 1;
            increasingArr[indexToInsert] = arrayElement;
            if (indexToInsert == rightPointer)
                rightPointer++;
        }
        return rightPointer;
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

    /**
     * This problem was asked by Google.
     *
     * You are given an N by M 2D matrix of lowercase letters. Determine the minimum number of columns that can be
     * removed to ensure that each row is ordered from top to bottom lexicographically. That is, the letter at each
     * column is lexicographically later as you go down each row. It does not matter whether each row itself is ordered
     * lexicographically.
     *
     * For example, given the following table:
     *
     * cba
     * daf
     * ghi
     * This is not ordered because of the a in the center. We can remove the second column to make it ordered:
     *
     * ca
     * df
     * gi
     * So your function should return 1, since we only needed to remove 1 column.
     *
     * As another example, given the following table:
     *
     * abcdef
     * Your function should return 0, since the rows are already ordered (there's only one row).
     *
     * As another example, given the following table:
     *
     * zyx
     * wvu
     * tsr
     * Your function should return 3, since we would need to remove all the columns to order it.
     *
     * @param matrix
     * @return
     */
    public int columnsToRemove(char[][] matrix) {
        int count = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (rows == 0 || cols == 0)
            return 0;
        for (int col = 0; col < cols; col++) {
            char currentChar = matrix[0][col];
            for (int row = 1; row < rows; row++) {
                if (matrix[row][col] > currentChar) {
                    currentChar = matrix[row][col];
                }
                else {
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    /**
     * This problem was asked by Snapchat.
     *
     * Given a list of possibly overlapping intervals, return a new list of intervals where all overlapping intervals
     * have been merged.
     *
     * The input list is not necessarily ordered in any way.
     *
     * For example, given [(1, 3), (5, 8), (4, 10), (20, 25)], you should return [(1, 3), (4, 10), (20, 25)].
     *
     * @param intervals
     * @return
     */
    List<int[]> mergeIntervals(List<int[]> intervals) {
        //idea is to sort intervals (per start time) and then iterate, comparing current interval with previously saved.
        //by sorting original list we save on time it's O(1)
        //sorting gives O(n*log(n)) running time, iteration - O(n), so overall - O(n*log(n))
        List<int[]> res = new ArrayList<>();
        int n = intervals.size();
        if (n == 0)
            return res;
        //sorting, lambda notation
        Collections.sort(intervals, (o1, o2) -> (o1[0] - o2[0]));
        int sPointer = 1;
        int[] mergedInterval = new int[2];
        mergedInterval[0] = intervals.get(0)[0];
        mergedInterval[1] = intervals.get(0)[1];
        while(sPointer < n) {
            if (mergedInterval[1] < intervals.get(sPointer)[0]) {
                res.add(mergedInterval);
                mergedInterval = new int[2];
                mergedInterval[0] = intervals.get(sPointer)[0];
                mergedInterval[1] = intervals.get(sPointer)[1];
            } else if (mergedInterval[1] < intervals.get(sPointer)[1]) {
                mergedInterval[1] = intervals.get(sPointer)[1];
            }
            sPointer++;
        }
        //after last iteration we haven't added array, so do it
        res.add(mergedInterval);
        return res;
    }

    /**
     * This problem was asked recently by Google.
     *
     * Given k sorted singly linked lists, write a function to merge all the lists into one sorted singly linked list.
     *
     * @param lists
     * @return
     */
    ListNode mergeToSorted(List<ListNode> lists) {
        //idea is - use mixHeap, put head of each list in it, then poll min element sequentially and add next to the
        //minHeap. If list is exhausted next will be null so no elements from this list stay in the heap
        PriorityQueue<ListNode> minQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for(int i = 0; i < lists.size(); i++) {
            ListNode head = lists.get(i);
            minQueue.add(head);
        }
        ListNode firstMinNode = minQueue.poll();
        ListNode curr = firstMinNode;
        ListNode head = firstMinNode;
        if (curr.next != null)
            minQueue.add(curr.next);
        while(!minQueue.isEmpty()) {
            ListNode nextMinNode = minQueue.poll();
            curr.next = nextMinNode;
            curr = nextMinNode;
            if (nextMinNode.next != null) {
                minQueue.add(nextMinNode.next);
            }
        }
        return head;
    }

    /**
     * This problem was asked by Facebook.
     *
     * Given an array of integers, write a function to determine whether the array could become non-decreasing by
     * modifying at most 1 element.
     *
     * For example, given the array [10, 5, 7], you should return true, since we can modify the 10 into a 1 to make
     * the array non-decreasing.
     *
     * Given the array [10, 5, 1], you should return false, since we can't modify any one element to get a
     * non-decreasing array.
     *
     * @param nums
     * @return
     */
    boolean isNonDecreasingPossible(int[] nums) {
        //idea is to catch those increasing/decreasing points. If there are two or more of those - we definitely can't
        // do the requirement
        //otherwise it depends - if it's at certain index like 0 or last - 1 - we can do it. Otherwise need to check
        //surrounders of that index. if nums[index - 1] <= nums[index + 1] or nums[index] <= nums[index + 2] we can do it.
        //edge index cases covered by previous if
        //time - O(n) - full array scan
        //space - O(1) - just couple of extra variables
        int index = -1;
        int n = nums.length;
        if (n < 2)
            return true;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                if (index != -1)
                    return false;
                else
                    index = i;
            }
        }
        if (index == -1 || index == 0|| index == n - 2) return true;
        return nums[index - 1] <= nums[index + 1] || nums[index] <= nums[index + 2];
    }

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

        System.out.println("--- num of unsorted cols in matrix ----");
        char[][] charMatrix;
        charMatrix = new char[][] {
                {'c', 'b', 'a'},
                {'d', 'a', 'f'},
                {'g', 'h', 'i'}
        };
        System.out.println(obj.columnsToRemove(charMatrix));

        charMatrix = new char[][] {
                {'a', 'b', 'c', 'd', 'e', 'f'}
        };
        System.out.println(obj.columnsToRemove(charMatrix));

        charMatrix = new char[][] {
                {'z', 'y', 'x'},
                {'w', 'v', 'u'},
                {'t', 's', 'r'}
        };
        System.out.println(obj.columnsToRemove(charMatrix));

        System.out.println("---- return list of merged overlapped intervals -----");
        List<int[]> intervals = new ArrayList<>();
        intervals.add(new int[] {1, 3});
        intervals.add(new int[] {5, 8});
        intervals.add(new int[] {4, 10});
        intervals.add(new int[] {20, 25});

        List<int[]> merged = obj.mergeIntervals(intervals);

        for (int [] array : merged ) {
            System.out.print("[" + array[0] + ", "+array[1] + "], ");
        }
        System.out.println("");

        intervals = new ArrayList<>();
        intervals.add(new int[] {1, 3});
        intervals.add(new int[] {2, 7});
        intervals.add(new int[] {5, 8});
        intervals.add(new int[] {4, 10});
        intervals.add(new int[] {20, 25});
        merged = obj.mergeIntervals(intervals);
        for (int [] array : merged ) {
            System.out.print("[" + array[0] + ", "+array[1] + "], ");
        }
        System.out.println("");

        intervals = new ArrayList<>();
        intervals.add(new int[] {2, 8});
        intervals.add(new int[] {1, 7});
        intervals.add(new int[] {5, 8});
        intervals.add(new int[] {4, 10});
        intervals.add(new int[] {20, 25});
        merged = obj.mergeIntervals(intervals);
        for (int [] array : merged ) {
            System.out.print("[" + array[0] + ", "+array[1] + "], ");
        }
        System.out.println("");

        intervals = new ArrayList<>();
        intervals.add(new int[] {2, 8});
        merged = obj.mergeIntervals(intervals);
        for (int [] array : merged ) {
            System.out.print("[" + array[0] + ", "+array[1] + "], ");
        }
        System.out.println("");

        intervals = new ArrayList<>();
        merged = obj.mergeIntervals(intervals);
        for (int [] array : merged ) {
            System.out.print("[" + array[0] + ", "+array[1] + "], ");
        }
        System.out.println("");

        System.out.println("----- merge k sorted lists --------");
        ListNode headOne = new ListNode(2, new ListNode(4, new ListNode(8)));
        ListNode headTwo = new ListNode(1, new ListNode(6, new ListNode(12, new ListNode(15))));
        ListNode headThree = new ListNode(0, new ListNode(4, new ListNode(7, new ListNode(9, new ListNode(11)))));
        List<ListNode> lists = new ArrayList<>();
        lists.add(headOne);
        lists.add(headTwo);
        lists.add(headThree);
        ListNode sortedMerged = obj.mergeToSorted(lists);
        System.out.println(StringUtils.singlyListNodeToString(sortedMerged));

        System.out.println("----- is non-decreasing array possible with one replacement max -------");
        System.out.println(obj.isNonDecreasingPossible(new int[] {10, 5, 7}));//true
        System.out.println(obj.isNonDecreasingPossible(new int[] {10, 5, 1}));//false
        System.out.println(obj.isNonDecreasingPossible(new int[] {-5, 1, 2, 3, 2, 3, 6}));//true
        System.out.println(obj.isNonDecreasingPossible(new int[] {5, 1, 2, 3, 2, 3, 6}));//false
        System.out.println(obj.isNonDecreasingPossible(new int[] {3, 4, 2, 3}));//false
    }
}
