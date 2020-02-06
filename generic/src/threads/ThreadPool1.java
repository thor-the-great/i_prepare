package threads;

import java.util.concurrent.*;

public class ThreadPool1 {

    int MAX_ITERATIONS = 10_000;
    int REPETITIONS = 10_000;

    void doTest() {
        long start = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(REPETITIONS);

        Runnable task = () -> {
          for (int i = 0; i < MAX_ITERATIONS; i++) {
              double j = Math.sqrt(Math.log(i));
              j = Math.max(j, i);
          }
          //latch.countDown();
        };

        Callable<Double> calTask = () -> {
            Double res = 0.0;
            for (int i = 0; i < MAX_ITERATIONS; i++) {
                double j = Math.asin(Math.log(Math.sqrt(i)));
                res += Math.max(j, i);
            }
            //latch.countDown();
            return res;
        };

        ConcurrentLinkedQueue<Double> linkedQueue = new ConcurrentLinkedQueue<>();

        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < REPETITIONS; i++) {
            FutureTask<Double> ft = new FutureTask<>(calTask);
            executor.execute(ft);
            try {
                linkedQueue.add(ft.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        /*try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        executor.shutdown();

        double res = 0;
        for (double d : linkedQueue)
            res += d;

        /*for (int i = 0; i < REPETITIONS; i++)
            task.run();
*/

        System.out.println("Done! " + (System.currentTimeMillis() - start) + " ms");
        System.out.println("Result is " + res);
    }

    public static void main(String[] args) {
        ThreadPool1 obj = new ThreadPool1();
        obj.doTest();
    }
}
