package path.google;

import java.util.*;

/**
 * 444. Sequence Reconstruction
 * Medium
 *
 * Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence
 * is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. Reconstruction means building a shortest common
 * supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences
 * of it). Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.
 *
 * Example 1:
 *
 * Input:
 * org: [1,2,3], seqs: [[1,2],[1,3]]
 *
 * Output:
 * false
 *
 * Explanation:
 * [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can
 * be reconstructed.
 * Example 2:
 *
 * Input:
 * org: [1,2,3], seqs: [[1,2]]
 *
 * Output:
 * false
 *
 * Explanation:
 * The reconstructed sequence can only be [1,2].
 * Example 3:
 *
 * Input:
 * org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]
 *
 * Output:
 * true
 *
 * Explanation:
 * The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
 * Example 4:
 *
 * Input:
 * org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]
 *
 * Output:
 * true
 */
public class SequenceReconstruction {

    /**
     * Idea - just compare sequence of elements in every array of sequences and check that it's increasing in original
     * array
     * @param org
     * @param seqs
     * @return
     */
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        int len  = org.length;
        int[] pos = new int[len + 1];
        boolean[] flgs = new boolean[len + 1];
        for(int i = 0; i < len; i++){
            pos[org[i]] = i;
        }
        int cnt = len - 1;
        boolean mark = false;
        for(List<Integer> list : seqs){

            for(int i = 0; i < list.size(); i++){
                mark = true;
                if(list.get(i) <= 0 || list.get(i) > len){
                    return false;
                }
                if(i == 0){
                    continue;
                }
                int pre = list.get(i - 1), cur = list.get(i);
                if(pos[pre] >= pos[cur]){
                    return false;
                }
                if(!flgs[cur] && pos[pre] + 1 == pos[cur]){
                    flgs[cur] = true;
                    cnt--;
                }
            }
        }
        return cnt == 0 && mark;
    }

    public static void main(String[] args) {
        SequenceReconstruction obj = new SequenceReconstruction();
        List<List<Integer>> res;
        int[] org;

        org = new int[] {1,2,3};
        res = new LinkedList<>();
        res.add(Arrays.asList(new Integer[]{1, 2}));
        res.add(Arrays.asList(new Integer[]{1, 3}));
        res.add(Arrays.asList(new Integer[]{2, 3}));

        System.out.println(obj.sequenceReconstruction(org, res));

        org = new int[] {1};
        res = new LinkedList<>();
        res.add(Arrays.asList(new Integer[]{}));
        res.add(Arrays.asList(new Integer[]{}));

        System.out.println(obj.sequenceReconstruction(org, res));
    }
}
