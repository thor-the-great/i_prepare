package locks;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RWLockPerformanceTest {

    public static final int NUM_OF_ITERATIONS = 1_000_000;
    public static final int NUM_OF_READS = 12;

    public static void main(String[] args) {
        SingleLockMap<Integer, String> singleLockMap = new SingleLockMap<>();
        Random rand = new Random();
        //single type of lock
        ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
        long start = System.currentTimeMillis();
        for (int idx = 0; idx < NUM_OF_ITERATIONS; idx++) {
            int i1 = idx;
            exec.execute(() -> singleLockMap.put(i1, Integer.toString(i1)));
            for (int j = 0; j < NUM_OF_READS; j++) {
                exec.execute(() -> {
                    singleLockMap.get(rand.nextInt(Integer.MAX_VALUE));
                });
            }
            //singleLockMap.put(i, Integer.toString(i));

        }
        exec.shutdown();
        try {
            exec.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {

        }
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Elapsed single lock : " + elapsed);

        //separate read write locks
        ReadWriteLockMap<Integer, String> rwLockMap = new ReadWriteLockMap<>();
        exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
        start = System.currentTimeMillis();
        for (int idx = 0; idx < NUM_OF_ITERATIONS; idx++) {
            int i = idx;
            exec.execute(() -> rwLockMap.put(i, Integer.toString(i)));
            for (int j = 0; j < NUM_OF_READS; j++) {
                exec.execute(() -> {
                    rwLockMap.get(rand.nextInt(Integer.MAX_VALUE));
                });
            }
        }
        exec.shutdown();
        try {
            exec.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {

        }
        elapsed = System.currentTimeMillis() - start;
        System.out.println("Elapsed rw lock : " + elapsed);
    }
}
