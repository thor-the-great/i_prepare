
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;
import java.util.stream.IntStream;

/**
 * Quicksort, partitioning based on left element
 */
public class QuickSortRandPivot {

    static Random rand = new Random();

    static int[] arr;

    public static void sortIterative(int[] array) {
        arr = array;
        Stack<Integer> stack = new Stack();
        stack.push(0);
        stack.push(arr.length - 1);

        while (!stack.isEmpty()) {
            int end = stack.pop();
            int start = stack.pop();
            if (start >= end)
                continue;
            int pivotIdx =
                partition(start, end, start + rand.nextInt(end - start));
            stack.push(pivotIdx + 1);
            stack.push(end);
            stack.push(start);
            stack.push(pivotIdx);
        }
    }

    public static void sortRecursive(int[] array) {
        arr = array;
        doSort(0, arr.length - 1);
    }

    static void doSort(int l, int r) {
        if (l < r) {
            int pivotIdx = l + rand.nextInt(r - l);
            pivotIdx = partition(l, r, pivotIdx);
            doSort(l, pivotIdx - 1);
            doSort(pivotIdx + 1, r);
        }
    }

    static int partition(int l, int r, int pIdx) {
        int pivotVal = arr[pIdx];
        swap(pIdx, r);
        int saveIdx = l;
        for (int i = l; i < r; i++) {
            if (arr[i] < pivotVal)
                swap(i, saveIdx++);
        }
        swap(saveIdx, r);
        return saveIdx;
    }

    static private void swap(int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public static void main(String[] args) {
        QuickSortRandPivot qs = new QuickSortRandPivot();
        int[] arr = new int[] {5, 2, 7, 3, 1, 6, 0, 4} ;
        qs.sortIterative(arr);
        System.out.println(Arrays.toString(arr));

        Random rand =  new Random();
        arr = IntStream
            .generate(
                ()-> rand.nextInt(20))
            .limit(8)
            .toArray();
        qs.sortIterative(arr);
        System.out.println(Arrays.toString(arr));

        //System.out.println("\nSorted array :");
        arr = utils.ArrayUtil.getRandomIntArray(1_000_000, 100000);
        long start = System.currentTimeMillis();
        qs.sortIterative(arr);
        long end = System.currentTimeMillis() - start;
        System.out.println("Time elapsed : " + end);

        arr = utils.ArrayUtil.getRandomIntArray(10_000_000, 100000);
        start = System.currentTimeMillis();
        qs.sortIterative(arr);
        end = System.currentTimeMillis() - start;
        System.out.println("Time elapsed : " + end);

        arr = utils.ArrayUtil.getRandomIntArray(50_000_000, 100000);
        start = System.currentTimeMillis();
        qs.sortIterative(arr);
        end = System.currentTimeMillis() - start;
        System.out.println("Time elapsed : " + end);

        arr = utils.ArrayUtil.getRandomIntArray(100_000_000, 100000);
        start = System.currentTimeMillis();
        qs.sortIterative(arr);
        end = System.currentTimeMillis() - start;
        System.out.println("Time elapsed : " + end);
        //Arrays.stream(arr).forEach(i->System.out.print(i +" "));
    }
}
