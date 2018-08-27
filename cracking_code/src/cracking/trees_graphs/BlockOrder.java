package cracking.trees_graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Martin G on 1/12/2017.
 */
public class BlockOrder {

    public String[] orderProjects(String[] projects, String[][] dependencies) {
        DiGraphGenerixExt<String> G = new DiGraphGenerixExt<>();
        for (String project : projects) {
            G.addVertex(project);
        }

        for (String[] dep : dependencies) {
            G.addEdge(dep[0], dep[1]);
        }
        String[] deps = new String[projects.length];
        int depsCount = 0;
        while(G.getV() > 0 ) {
            List<String> nextProjectOrder = G.getVertecesZeroInDegree();
            if (nextProjectOrder.size() > 0) {
                for (String indepProject : nextProjectOrder) {
                    deps[depsCount] = indepProject;
                    depsCount++;
                    G.removeVertex(indepProject);
                }
            }
            else
                throw new RuntimeException("Order is not possible");
        }
        return deps;
    }

    class DiGraphGenerixExt<V> extends DiGraphGeneric<V> {

        Map<V, List<V>> inDegree;

        DiGraphGenerixExt() {
            super();
            inDegree = new HashMap<>();
        }

        @Override
        void addEdge(V v1, V v2) {
            super.addEdge(v1, v2);

            List<V> inDegreeForVertex = inDegree.get(v2);
            if (inDegreeForVertex == null) {
                inDegreeForVertex = new ArrayList<>();
                inDegree.put(v2, inDegreeForVertex);
            }
            inDegreeForVertex.add(v1);

        }

        List<V> getVertecesZeroInDegree() {
            List<V> zeroInDegree = new ArrayList<>();
            for (V vertex : this.vertices) {
                List<V> inDegreeList = inDegree.get(vertex);
                if (inDegreeList == null || inDegreeList.size() == 0 ) {
                    zeroInDegree.add(vertex);
                }
            }
            return zeroInDegree;
        }

        void removeVertex(V v1) {
            List<Edge<V>> outEdges = this.edges.get(v1);
            //remove vertex from all in-degree's lists
            if (outEdges != null) {
                for (Edge<V> edge : outEdges) {
                    List<V> inDegree = this.inDegree.get(edge.to);
                    inDegree.remove(v1);
                }
                E = E - outEdges.size();
                this.edges.remove(v1);
            }
            this.vertices.remove(v1);
        }
    }

    public static void main(String[] args) {
        BlockOrder obj = new BlockOrder();
        String[] projects;
        String[][] deps;
        String[] projectOrder;
        String[] orderedProj;
        projects = new String[] {"a", "b", "c", "d", "e", "f"};
        deps = new String[][]{{"a", "d"}, {"f", "b"}, {"b", "d"}, {"f", "a"}, {"d", "c"}};
        projectOrder = new String[] {"f", "e", "a", "b", "d", "c"};

        orderedProj = obj.orderProjects(projects, deps);
        System.out.println("Ordered projects : ");
        for (String nextProj : orderedProj) {
            System.out.print(nextProj + ", ");
        }

        projects = new String[] {"a", "b", "c", "d", "e", "f"};
        deps = new String[][]{{"a", "b"}, {"a", "c"}, {"b", "d"}, {"c", "e"}, {"d", "f"}, {"e", "f"}};

        orderedProj = obj.orderProjects(projects, deps);
        System.out.println("Ordered projects : ");
        for (String nextProj : orderedProj) {
            System.out.print(nextProj + ", ");
        }

        projects = new String[] {"a", "b", "c", "d"};
        deps = new String[][]{{"a", "b"}, {"b", "d"}, {"d", "c"}, {"c", "a"}};

        orderedProj = obj.orderProjects(projects, deps);
        System.out.println("Ordered projects : ");
        for (String nextProj : orderedProj) {
            System.out.print(nextProj + ", ");
        }
    }
}
