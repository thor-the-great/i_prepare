import cracking.trees_graphs.DiGraph;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BFSParallel {
    Random r = new Random();

    void finPath(DiGraph g, int startVertex, int endVertex ) {

        long start = System.currentTimeMillis();

        //do single-threaded BFS
        boolean found = false;
        Set<Integer> visited = new HashSet();
        Queue<Integer> q = new LinkedList<>();
        q.add(startVertex);
        while(!q.isEmpty()) {
            int v = q.poll();
            if (!visited.contains(v)) {
                visitVertex(v);
                visited.add(v);
                if (v == endVertex) {
                    long elapsed = System.currentTimeMillis() - start;
                    System.out.println("Path found! Time : " + elapsed + " visited " + visited.size());
                    found = true;
                    break;
                }
                if (g.adj(v) != null && !g.adj(v).isEmpty()) {
                    for(int adjV : g.adj(v))
                        q.add(adjV);
                }
            }
        }
        if (!found) {
            long elapsed = System.currentTimeMillis() - start;
            System.out.println("No path found :( Time : " + elapsed  + " visited " + visited.size());
        }
    }

    ValueLatch sol;
    ConcurrentHashMap.KeySetView visited;
    DiGraph gConc;
    ConcurrentLinkedQueue<Integer> queue;
    Executor exec;
    final AtomicInteger taskCount = new AtomicInteger(0);

    void findPathConcurrent(DiGraph g, int startVertex, int endVertex ) {
        gConc = g;
        try {
            taskCount.set(0);
            long start = System.currentTimeMillis();
            sol = new ValueLatch ();
            exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
            visited = ConcurrentHashMap.newKeySet();
            queue = new ConcurrentLinkedQueue<>();
            queue.add(startVertex);
            exec.execute(new CalcTask(startVertex, endVertex));
            Integer solution = sol.getValue();
            long elapsed = System.currentTimeMillis() - start;
            if (solution != null)
                System.out.println("Path found! Time " + elapsed + " visited " + visited.size());
            else
                System.out.println("Path not found( Time " + elapsed + " visited " + visited.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            ((ExecutorService) exec).shutdown();
        }
    }

    class CalcTask implements Runnable {
        int v;
        int endVertex;

        public CalcTask(int v, int endVertex) {
            this.v = v;
            this.endVertex = endVertex;
            taskCount.incrementAndGet();
        }

        @Override
        public void run() {
            int v = queue.poll();
            if (!sol.isSet() && !visited.contains(v)) {
                visited.add(v);
                visitVertex(v);
                if (v == endVertex) {
                    sol.setValue(endVertex);
                }
                else {
                    if (gConc.adj(v) != null && !gConc.adj(v).isEmpty()) {
                        for (int adjV : gConc.adj(v)) {
                            if (!((ExecutorService) exec).isShutdown()) {
                                queue.add(adjV);
                                exec.execute(new CalcTask(adjV, endVertex));
                            }
                        }
                    }
                }
            }

            if (taskCount.decrementAndGet() == 0) {
                sol.setValue(null);
            }
        }
    }

    class ValueLatch {
        Integer value;
        CountDownLatch done = new CountDownLatch(1);

        boolean isSet(){
            return done.getCount() == 0;
        }

        synchronized void setValue(Integer value) {
            if (!isSet()) {
                this.value = value;
                done.countDown();
            }
        }

        Integer getValue() throws InterruptedException {
            done.await();
            synchronized (this) {
                return value;
            }
        }
    }

    /**
     * Doing BFS for the graph
     *              0
     *          /   |   \
     *        1     2    3
     *       / \   / \    \
     *     4   5  6   7    8
     *        / \     |
     *       9  10    11
     */
    private DiGraph getDiGraph1() {
        DiGraph g = new DiGraph(12);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(1, 4);
        g.addEdge(1, 5);
        g.addEdge(2, 6);
        g.addEdge(2, 7);
        g.addEdge(3, 8);
        g.addEdge(5, 9);
        g.addEdge(5, 10);
        g.addEdge(7, 11);
        return g;
    }

    /**
     * Doing BFS for the graph
     *              0
     *          /   |   \
     *        1     2    3
     *       / \   / \    \
     *     4   5  6   7   |
     *     \  / \     |  /
     *     8 9  10    11
     */
    private DiGraph getDiGraph2() {
        DiGraph g = new DiGraph(13);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(1, 4);
        g.addEdge(1, 5);
        g.addEdge(2, 6);
        g.addEdge(2, 7);
        g.addEdge(3, 11);
        g.addEdge(4, 8);
        g.addEdge(5, 9);
        g.addEdge(5, 10);
        g.addEdge(7, 11);
        return g;
    }

    private void visitVertex(int v) {
        String s = "ddfgdfgdfgdfghgas";
        int iter = 5_000_000 + r.nextInt(1000);
        for (int j =0; j < iter; j++) {
            int k = j*j;
            for (int l = 0; l < s.length();l++) {
                String b = s.substring(0, l + 1);
            }
        }
    }

    public static void main(String[] args) {
        BFSParallel obj = new BFSParallel();
        obj.finPath(obj.getDiGraph1(), 0, 11);
        obj.findPathConcurrent(obj.getDiGraph1(),0, 11);

        obj.finPath(obj.getDiGraph2(), 0, 5);
        obj.findPathConcurrent(obj.getDiGraph2(),0, 5);
    }

}
