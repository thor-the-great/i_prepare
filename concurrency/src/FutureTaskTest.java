import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class FutureTaskTest {

    java.util.concurrent.FutureTask futureTask = new java.util.concurrent.FutureTask((Callable) () -> {
        Random rand = new Random();
        String s = "ddfgdfgdfgdfghggytyturtggrgt";
        //int iter = 1000 + rand.nextInt(5_000_000);
        int iter = 5_000_000 + rand.nextInt(300);
        for (int j =0; j < iter; j++) {
            int k = j*j;
            for (int l = 0; l < s.length();l++) {
                String b = s.substring(0, l + 1);
            }
        }
        return "task completed";
    });
    Thread runner = new Thread(futureTask);

    void startTestTask() {
        runner.start();
    }

    void doOtherThing() {
        System.out.println("Start doing thing 2");
        try {
            System.out.println(futureTask.get().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Continue doing thing 2");
    }

    public static void main(String[] args) {
        FutureTaskTest obj = new FutureTaskTest();
        obj.startTestTask();
        obj.doOtherThing();
    }
}
