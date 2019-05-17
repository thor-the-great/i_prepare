
import java.util.Arrays;

/**
 * Quicksort, partitioning based on left element
 */
public class QuickSort {

    public static void sort(int[] arr) {
        doSort(arr, 0, arr.length - 1);
    }

    static void doSort(int[] arr, int l, int r) {
        if (l < r) {
            int pivotIdx = partitioning(arr, l, r);
            doSort(arr, l, pivotIdx - 1);
            doSort(arr, pivotIdx + 1, r);
        }
    }

    static int partitioning(int[] arr, int l, int r) {
        int pivot = arr[l];
        int t;
        int j = r + 1;
        for (int i = r; i >= l; i--) {
            if (arr[i] > pivot) {
                j--;
                t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        t = arr[j -1];
        arr[j - 1] = arr[l];
        arr[l] = t;
        return j - 1;
    }

    public static void main(String[] args) {
        QuickSort qs = new QuickSort();
        int[] arr = new int[] {5, 2, 7, 3, 1, 6, 0, 4} ;
        qs.sort(arr);
        Arrays.stream(arr).forEach(i->System.out.print(i +" "));

        //System.out.println("\nSorted array :");
        arr = utils.ArrayUtil.getRandomIntArray(1_000_000, 100000);
        long start = System.currentTimeMillis();
        qs.sort(arr);
        long end = System.currentTimeMillis() - start;
        System.out.println("Time elapsed : " + end);

        arr = utils.ArrayUtil.getRandomIntArray(10_000_000, 100000);
        start = System.currentTimeMillis();
        qs.sort(arr);
        end = System.currentTimeMillis() - start;
        System.out.println("Time elapsed : " + end);

        arr = utils.ArrayUtil.getRandomIntArray(50_000_000, 100000);
        start = System.currentTimeMillis();
        qs.sort(arr);
        end = System.currentTimeMillis() - start;
        System.out.println("Time elapsed : " + end);

        arr = utils.ArrayUtil.getRandomIntArray(100_000_000, 100000);
        start = System.currentTimeMillis();
        qs.sort(arr);
        end = System.currentTimeMillis() - start;
        System.out.println("Time elapsed : " + end);
        //Arrays.stream(arr).forEach(i->System.out.print(i +" "));
    }
}
