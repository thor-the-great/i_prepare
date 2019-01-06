import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Memoizer {

    ConcurrentHashMap<Integer, FutureTask> map;
    Computable<Integer, Integer> computable;

    Memoizer(Computable<Integer, Integer> comp) {
        this.computable = comp;
        map = new ConcurrentHashMap<>();
    }

    public int compute(int arg) throws InterruptedException {

        int res = getCached(arg);

        return res;
    }

    int getCached(int i) throws InterruptedException {
        if (i == 1 || i == 2)
            return 1;
        Future<Integer> future;
        if (map.containsKey(i)) {
            future = map.get(i);
        } else {
            int p1 = getCached(i - 1);
            int p2 = getCached(i - 2);
            FutureTask ft = new FutureTask(() -> computable.compute(i, p1, p2));
            map.putIfAbsent(i, ft);
            future = ft;
            ft.run();
        }

        try {
            return future.get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

