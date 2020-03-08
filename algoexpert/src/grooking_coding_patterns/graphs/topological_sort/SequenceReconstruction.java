package grooking_coding_patterns.graphs.topological_sort;

import java.util.*;

/**
 * Reconstructing a Sequence (hard) #
 * Given a sequence originalSeq and an array of sequences, write a method to find if originalSeq can be uniquely
 * reconstructed from the array of sequences.
 *
 * Unique reconstruction means that we need to find if originalSeq is the only sequence such that all sequences in
 * the array are subsequences of it.
 *
 * Example 1:
 *
 * Input: originalSeq: [1, 2, 3, 4], seqs: [[1, 2], [2, 3], [3, 4]]
 * Output: true
 * Explanation: The sequences [1, 2], [2, 3], and [3, 4] can uniquely reconstruct
 * [1, 2, 3, 4], in other words, all the given sequences uniquely define the order of numbers
 * in the 'originalSeq'.
 * Example 2:
 *
 * Input: originalSeq: [1, 2, 3, 4], seqs: [[1, 2], [2, 3], [2, 4]]
 * Output: false
 * Explanation: The sequences [1, 2], [2, 3], and [2, 4] cannot uniquely reconstruct
 * [1, 2, 3, 4]. There are two possible sequences we can construct from the given sequences:
 * 1) [1, 2, 3, 4]
 * 2) [1, 2, 4, 3]
 * Example 3:
 *
 * Input: originalSeq: [3, 1, 4, 2, 5], seqs: [[3, 1, 5], [1, 4, 2, 5]]
 * Output: true
 * Explanation: The sequences [3, 1, 5] and [1, 4, 2, 5] can uniquely reconstruct
 * [3, 1, 4, 2, 5].
 */
public class SequenceReconstruction {

    /**
     * Idea - the topological sort will be our sequence. If more than one possible or the sort
     * doesn't match the sequence return false
     * Build the graph normally - list og adjacent nodes and in-degree map
     * @param originalSeq
     * @param sequences
     * @return
     */
    public static boolean canConstruct(int[] originalSeq, int[][] sequences) {
        Map<Integer, List<Integer>> graph = new HashMap();
        Map<Integer, Integer> indegree = new HashMap();
        //init for graph
        for (int[] seq : sequences) {
            for (int num : seq) {
                if (!graph.containsKey(num))
                    graph.put(num, new ArrayList());
                indegree.putIfAbsent(num, 0);
            }
        }
        //init graph nd in-degree maps
        for (int[] seq : sequences) {
            for (int i  = 1; i < seq.length; i++) {
                indegree.put(seq[i], indegree.get(seq[i]) + 1);
                graph.get(seq[i - 1]).add(seq[i]);
            }
        }
        //check if we can potentilly build correct sort
        if (indegree.size() != originalSeq.length)
            return false;

        Queue<Integer> q = new LinkedList();
        int[] topoSort = new int[indegree.size()];
        int p = 0;
        //adding source points
        for (int num : indegree.keySet()) {
            if (indegree.get(num) == 0) {
                q.add(num);
            }
        }
        //start traversing graph and build topological sort
        while(!q.isEmpty()) {
            //we can have only one next source at each point
            if (q.size() > 1)
                return false;
            int num = q.poll();
            topoSort[p++] = num;
            for (int next : graph.get(num)) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) {
                    q.add(next);
                }
            }
        }
        //comparing topological sort with the sequence, it must match
        for (int i = 0; i < originalSeq.length; i++) {
            if (topoSort[i] != originalSeq[i])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        boolean result = SequenceReconstruction.canConstruct(new int[] { 1, 2, 3, 4 },
                new int[][] { new int[] { 1, 2 }, new int[] { 2, 3 }, new int[] { 3, 4 } });
        System.out.println("Can we uniquely construct the sequence: " + result);

        result = SequenceReconstruction.canConstruct(new int[] { 1, 2, 3, 4 },
                new int[][] { new int[] { 1, 2 }, new int[] { 2, 3 }, new int[] { 2, 4 } });
        System.out.println("Can we uniquely construct the sequence: " + result);

        result = SequenceReconstruction.canConstruct(new int[] { 3, 1, 4, 2, 5 },
                new int[][] { new int[] { 3, 1, 5 }, new int[] { 1, 4, 2, 5 } });
        System.out.println("Can we uniquely construct the sequence: " + result);
    }
}
