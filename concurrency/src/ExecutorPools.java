import java.util.Random;
import java.util.concurrent.*;

public class ExecutorPools {

    void testPool(int n) {
        //int numOfThread = 1;
        int numOfThread = Runtime.getRuntime().availableProcessors() + 1;
        //int numOfThread = n;
        Executor exec = Executors.newFixedThreadPool(numOfThread);
        long start = System.currentTimeMillis();
        Random r = new Random();
        for (int i =0; i < n; i++) {
            int finalI = i;
            Runnable run = ()-> {
                String s = "ddfgdfgdfgdfghgas";
                int iter = 5_000_000 + r.nextInt(1000);
                for (int j =0; j < iter; j++) {
                    int k = j*j;
                    for (int l = 0; l < s.length();l++) {
                        String b = s.substring(0, l + 1);
                    }
                }
                //System.out.println("Done "  + finalI);
            };
            exec.execute(run);
        }

        ((ExecutorService) exec).shutdown();
        try {
            ((ExecutorService) exec).awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("Overall time: " + end);

    }

    public static void main(String[] args) {
        ExecutorPools obj = new ExecutorPools();
        obj.testPool(25);
    }
}
