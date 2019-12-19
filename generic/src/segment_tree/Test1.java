package segment_tree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.Random;
import segment_tree.MutableArrayImpl.CummulativeSum;
import segment_tree.MutableArrayImpl.Naive;

public class Test1 {

    public static final int NUMBER_BOUND = 500000;
    public static final int SIZE = 1000000;
    int NUM_OF_CALLS_1 = 10000;
    int NUM_OF_CALLS_2 = 100000;
    Random rand = new Random();

    @Test
    void testCorrectnessNaive() {
        int[] arr = new int[] {3, 1, -2, 5, 20, 15, 4, -3};
        Naive naive = new Naive(arr);
        doCorrectTest(naive);
    }

    @Test
    void testCorrectnessCumSum() {
        int[] arr = new int[] {3, 1, -2, 5, 20, 15, 4, -3};
        CummulativeSum cumSum = new CummulativeSum(arr);
        doCorrectTest(cumSum);
    }

    void doCorrectTest(MutableArray algo) {
        assertEquals(algo.range(0, 2), 2);
        assertEquals(algo.range(1, 6), 43);
        algo.update(1, -10);
        assertEquals(algo.range(0, 2), -9);
        algo.update(2, 2);
        algo.update(5, -1);
        //{3, -10, 2, 5, 20, -1, 4, -3}
        assertEquals(algo.range(0, 2), -5);
        assertEquals(algo.range(1, 6), 20);
    }

    @Test
    void testNumOfCalls1() {
        doTest(NUM_OF_CALLS_1);
    }

    @Test
    void testNumOfCalls2() {
        doTest(NUM_OF_CALLS_2);
    }

    void doTest(int numOfCalls) {
        int[] arr = rand.ints(SIZE, 0, NUMBER_BOUND).toArray();
        long start = System.currentTimeMillis();
        doNaive(arr, numOfCalls);
        long end = System.currentTimeMillis();
        System.out.println("Elapsed time naive (" + numOfCalls + " calls) : " + (end - start));

        start = System.currentTimeMillis();
        doCumSum(arr, numOfCalls);
        end = System.currentTimeMillis();
        System.out.println("Elapsed time cum sum (" + numOfCalls + " calls) : " + (end - start));
    }

    void doNaive(int[] arr, int numOfCalls) {
        Naive naive = new Naive(arr);
        for (int i = 0; i < numOfCalls; i++) {
            naive.update(rand.nextInt(arr.length), rand.nextInt(NUMBER_BOUND));
            int rand1 = rand.nextInt(arr.length), rand2 = rand.nextInt(arr.length);
            int sum = naive.range(Math.min(rand1, rand2), Math.max(rand1, rand2));
        }
    }

    void doCumSum(int[] arr, int numOfCalls) {
        CummulativeSum algo = new CummulativeSum(arr);
        for (int i = 0; i < numOfCalls; i++) {
            algo.update(rand.nextInt(arr.length), rand.nextInt(NUMBER_BOUND));
            int rand1 = rand.nextInt(arr.length), rand2 = rand.nextInt(arr.length);
            int sum = algo.range(Math.min(rand1, rand2), Math.max(rand1, rand2));
        }
    }

    private int doCumSumInt(int[] arr, int left, int right) {
        return arr[right + 1] - arr[left];
    }
}
