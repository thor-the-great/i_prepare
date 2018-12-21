import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Semaphores {

    void doTest(int countOfThread) {
        Random rand = new Random();
        Semaphore sem = new Semaphore(1, true);
        AtomicInteger res = new AtomicInteger(0);
        ExecutorService executor = Executors.newFixedThreadPool(countOfThread);
        CountDownLatch startCounter = new CountDownLatch(1);
        CountDownLatch endCounter = new CountDownLatch(countOfThread);
        for (int i = 0; i < countOfThread; i++) {
            Runnable task = () -> {
                try {
                    startCounter.await();
                    sem.acquire();
                    String s = "ddfgdfgdfgdfghjh";
                    int iter = 5_000_000 + rand.nextInt(300);
                    for (int j = 0; j < iter; j++) {
                        int k = j * j;
                        for (int l = 0; l < s.length(); l++) {
                            String b = s.substring(0, l + 1);
                        }
                    }
                    res.incrementAndGet();
                    sem.release();
                    endCounter.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            executor.execute(task);
        }
        long start = System.currentTimeMillis();
        startCounter.countDown();
        try {
            endCounter.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long runTime = System.currentTimeMillis() - start;
        System.out.println("Threads finished in ms " + runTime +". Result is " + res.get());
        executor.shutdown();
        while(!executor.isTerminated()) {

        }
    }

    public static void main(String[] args) {
        Semaphores obj = new Semaphores();
        obj.doTest(20);
    }
}
