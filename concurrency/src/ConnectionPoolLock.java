import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This is connection pool implementation based on producer-consumer and blocking queue.
 *
 * Pool has initial capacity and maximum capacity. Also it has a timeout for getConnection method, returns null in
 * case of timeout
 *
 * there are no synchronized methods
 */
public class ConnectionPoolLock {
    int count;
    AtomicInteger current;
    BlockingQueue<Connection> q;
    Lock lock = new ReentrantLock();
    int timeout = 5000;

    ConnectionPoolLock(int overallCapacity, int initCapacity) {
        this.count = overallCapacity;
        this.current = new AtomicInteger(0);
        q = new LinkedBlockingQueue(overallCapacity);
        for (int i = 0; i < initCapacity; i++)
            getNewConnection();
    }

    Connection getConnection() {
        if (q.peek() == null && current.get() < count) {
            getNewConnection();
        }
        Connection conn = null;
        try {
            //conn = q.take();
            conn = q.poll(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private void getNewConnection() {
        Connection conn = new Connection(current.incrementAndGet());
        q.offer(conn);
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
        ConnectionPoolLock pool = new ConnectionPoolLock(8, 4);
        Random rand = new Random();
        int numOfTasks = 20;
        int numOfThreads = Runtime.getRuntime().availableProcessors() + 1;
        //int numOfThreads = 10;
        ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);
        long start = System.currentTimeMillis();
        for (int i = 0; i < numOfTasks; i++) {
            Runnable r = () -> {
                //System.out.println("requesting ");
                Connection conn = pool.getConnection();
                if (conn == null) {
                    System.out.println("Connection timeout");
                    return;
                }
                System.out.println("acquired " + conn.id );
                String s = "ddfgdfgdfgdfghg";
                //int iter = 1000 + rand.nextInt(5_000_000);
                int iter = 5_000_000 + rand.nextInt(300);
                for (int j =0; j < iter; j++) {
                    int k = j*j;
                    for (int l = 0; l < s.length();l++) {
                        String b = s.substring(0, l + 1);
                    }
                }
                //System.out.println("released " + conn.id);
                pool.releaseConn(conn);

            };
            executor.execute(r);
        }
        executor.shutdown();
        while(!executor.isTerminated()) {

        }
        System.out.println("finished in " +(System.currentTimeMillis() - start) +" ms");
    }

}
