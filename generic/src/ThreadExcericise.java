import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ThreadExcericise {

    AtomicInteger state1 = new AtomicInteger(0);
    AtomicInteger state2 = new AtomicInteger(0);

    void doTest() {
        List<Integer> res = new ArrayList<>();
        IntStream.range(0, 1000).parallel().forEach(i -> {
            Runnable r = () -> {
                AtomicInteger s1 = getState1();
                AtomicInteger s2 = getState2();
                //synchronized(this) {
                    int s1Val = s1.get();
                    s1.set(s1Val + 1);
                    //int s2Val = s2.get();
                    //System.out.println(s1Val + "; " + s2.get());
                    res.add(s1Val);
                    res.add(s2.get());
                    s2.set(s1Val + 1);
                //}
            };
            r.run();
        });

        res.forEach(i-> { if (i == null)System.out.println(i +" ");});
    }

    AtomicInteger getState1() {
        return state1;
    }

    void setState1(AtomicInteger s) {
        this.state1 = s;
    }

    public AtomicInteger getState2() {
        return state2;
    }

    public void setState2(AtomicInteger state2) {
        this.state2 = state2;
    }

    public static void main(String[] args) {
        ThreadExcericise obj = new ThreadExcericise();
        obj.doTest();
    }
}
