package path.linkedin;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 547. Friend Circles
 * Medium
 * There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in
 * nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C.
 * And we defined a friend circle is a group of students who are direct or indirect friends.
 *
 * Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the
 * ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of
 * friend circles among all the students.
 *
 * Example 1:
 * Input:
 * [[1,1,0],
 *  [1,1,0],
 *  [0,0,1]]
 * Output: 2
 * Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
 * The 2nd student himself is in a friend circle. So return 2.
 * Example 2:
 * Input:
 * [[1,1,0],
 *  [1,1,1],
 *  [0,1,1]]
 * Output: 1
 * Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
 * so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
 * Note:
 * N is in range [1,200].
 * M[i][i] = 1 for all students.
 * If M[i][j] = 1, then M[j][i] = 1.
 */
public class FriendCircles {

    /**
     * Using DFS to visit all cells (connected via 1-s)
     * increment the count of connected components for every new starting node.
     *
     * @param M
     * @return
     */
    public int findCircleNumDFS(int[][] M) {
        int N = M.length;
        boolean[] visited = new boolean[N];
        int count = 0;
        for (int r = 0; r < N; r++) {
            if (!visited[r]) {
                dfs(M, visited, r);
                ++count;
            }
        }
        return count;
    }

    void dfs(int[][] M, boolean[] visited, int p) {
        for (int i = 0; i < M.length; i++) {
            if (M[p][i] == 1 && !visited[i]) {
                visited[i] = true;
                dfs(M, visited, i);
            }
        }
    }

    public int findCircleNumDfsIterative(int[][] M) {
        int N = M.length;
        boolean[] visited = new boolean[N];
        int res = 0;
        for (int student = 0; student < N; student++) {
            if (visited[student])
                continue;
            Stack<Integer> stack = new Stack();
            stack.push(student);
            while(!stack.isEmpty()) {
                int next = stack.pop();
                visited[next] = true;
                for (int j = 0; j < N; j++) {
                    if (j != next && M[j][next] == 1 && !visited[j])
                        stack.push(j);
                }
            }
            ++res;
        }
        return res;
    }

    /**
     * Idea - iterate over the matrix of friends and add every friend to a UF. Then count number of connected components
     * in graph - number of unique parents - it will be number of circles
     * @param M
     * @return
     */
    public int findCircleNum(int[][] M) {
        int N = M.length;
        UnionFind uf = new UnionFind(N);
        for (int r =0; r < N; r++) {
            for (int c = r + 1; c < N; c++) {
                if (M[r][c] == 1)
                    uf.union(r, c);
            }
        }

        Set<Integer> cc = new HashSet();
        for (int i =0; i < N; i++) {
            cc.add(uf.find(i));
        }
        return cc.size();
    }

    class UnionFind {
        int[] parents;
        int[] rank;

        UnionFind(int N) {
            parents = new int[N];
            for (int i =0; i < N; i++) {
                parents[i] = i;
            }
            rank = new int[N];
        }

        int find(int x) {
            while (x != parents[x])
                x = parents[x];
            return parents[x];
        }

        void union(int u, int v) {
            int uParent = find(u);
            int vParent = find(v);
            if (uParent != vParent) {
                int uRank = rank[uParent];
                int vRank = rank[vParent];
                if (uRank > vRank) {
                    parents[vParent] = uParent;
                } else if (uRank < vRank) {
                    parents[uParent] = vParent;
                } else {
                    parents[vParent] = uParent;
                    rank[uParent]++;
                }
            }
        }
    }
}
