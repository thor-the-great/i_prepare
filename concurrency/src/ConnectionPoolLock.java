import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ConnectionPoolLock {
    int count;
    int current;
    BlockingQueue<Connection> q = new LinkedBlockingQueue();
    Lock lock = new ReentrantLock();

    ConnectionPoolLock(int c) {
        this.count = c;
        this.current = 0;
        //lock = new ReentrantLock();
        //for (int i = 0; i < c; i++)
        //    getNewConnection();
    }

    Connection getConn() {
        //lock.lock();
        if (q.peek() == null && current < count) {
        //if (current < count) {
            getNewConnection();
        }
        //lock.unlock();
        Connection conn = null;
        try {
            conn = q.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private void getNewConnection() {
        Connection conn = new Connection(current);
        q.offer(conn);
        current++;
    }

    void releaseConn(Connection c) {
        q.offer(c);

    }

    class Connection {
        int id;
        Connection(int i) {
            id = i;
        }
    }

    public static void main(String[] args) {
        ConnectionPoolLock pool = new ConnectionPoolLock(1);
        Random rand = new Random();
        int numOfThreds = 25;
        ExecutorService executor = Executors.newFixedThreadPool(numOfThreds);
        long start = System.currentTimeMillis();
        for (int i = 0; i < numOfThreds; i++) {
            Runnable r = () -> {
                //System.out.println("requesting ");
                Connection conn = pool.getConn();
                //System.out.println("acquired " + conn.id );
                String s = "ddfgdfgdfgdfghgyt";
                //int iter = 1000 + rand.nextInt(5_000_000);
                int iter = 5_000_000 + rand.nextInt(500);
                for (int j =0; j < iter; j++) {
                    int k = j*j;
                    for (int l = 0; l < s.length();l++) {
                        String b = s.substring(0, l + 1);
                    }
                }
                //System.out.println("released " + conn.id);
                pool.releaseConn(conn);

            };
            //r.run();
            executor.execute(r);
        }
        executor.shutdown();
        while(!executor.isTerminated()) {

        }
        System.out.println("finished in " +(System.currentTimeMillis() - start) +" ms");
    }

}
