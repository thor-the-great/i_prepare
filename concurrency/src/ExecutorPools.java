import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorPools {

    void testPool(int n) {
        Executor exec = Executors.newFixedThreadPool(4);
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
                System.out.println("Done "  + finalI);
            };
            exec.execute(run);
        }
        ((ExecutorService) exec).shutdown();
    }

    public static void main(String[] args) {
        ExecutorPools obj = new ExecutorPools();
        obj.testPool(50);
    }
}
