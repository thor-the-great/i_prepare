import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorPools {

    void testPool(int n) {
        //Executor exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
        //int numOfThread = 1;
        int numOfThread = Runtime.getRuntime().availableProcessors() + 1;
        //int numOfThread = n;
        Executor exec = Executors.newFixedThreadPool(numOfThread);
        CountDownLatch endCounter = new CountDownLatch(n);
        long start = System.currentTimeMillis();
        for (int i =0; i < n; i++) {
            int finalI = i;
            Runnable run = ()-> {
                String s = "ddfgdfgdfgdfghg";
                int iter = 1_000_000 + new Random().nextInt(1000);
                for (int j =0; j < iter; j++) {
                    int k = j*j;
                    for (int l = 0; l < s.length();l++) {
                        String b = s.substring(0, l + 1);
                    }
                }
                //System.out.println("Done "  + finalI);
                endCounter.countDown();
            };
            exec.execute(run);
        }

        try {
            endCounter.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("Overall time: " + end);
        ((ExecutorService) exec).shutdown();
    }

    public static void main(String[] args) {
        ExecutorPools obj = new ExecutorPools();
        obj.testPool(50);
    }
}
