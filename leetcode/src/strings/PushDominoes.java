package strings;

/**
 * 838. Push Dominoes
Medium

There are n dominoes in a line, and we place each domino vertically upright. In the beginning, we simultaneously push some of 
the dominoes either to the left or to the right.

After each second, each domino that is falling to the left pushes the adjacent domino on the left. Similarly, the dominoes falling 
to the right push their adjacent dominoes standing on the right.

When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.

For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.

You are given a string dominoes representing the initial state where:

dominoes[i] = 'L', if the ith domino has been pushed to the left,
dominoes[i] = 'R', if the ith domino has been pushed to the right, and
dominoes[i] = '.', if the ith domino has not been pushed.
Return a string representing the final state.

 

Example 1:

Input: dominoes = "RR.L"
Output: "RR.L"
Explanation: The first domino expends no additional force on the second domino.
Example 2:


Input: dominoes = ".L.R...LR..L.."
Output: "LL.RR.LLRRLL.."
 

Constraints:

n == dominoes.length
1 <= n <= 105
dominoes[i] is either 'L', 'R', or '.'.

 * 
 * https://leetcode.com/problems/push-dominoes
 */
public class PushDominoes {

    /**
     * Calculated force that is acting on each one domino. Count it first left to right, then right to left.
     * At the end 
     * - if final force > 0 - domino will fall to the right 'R'
     * - if < 0 - to the left 'L' 
     * - if 0 it will stay '.'
     * 
     * @param dominoes
     * @return
     */
    public String pushDominoes(String dominoes) {
        int N = dominoes.length();
        
        int[] forces = new int[N];
        //scan left to right
        int f = 0;
        for (int i = 0; i < N; i++) {
            if (dominoes.charAt(i) == 'R') {
                f = N;
            } else if (dominoes.charAt(i) == 'L') {
                f = 0;
            } else {
                f = Math.max(0,  f - 1);
            }
            forces[i] += f;
        }
        
        //scan right to left
        f = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (dominoes.charAt(i) == 'L') {
                f = N;
            } else if (dominoes.charAt(i) == 'R') {
                f = 0;
            } else {
                f = Math.max(0,  f - 1);
            }
            forces[i] -= f;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int n : forces) {
            if (n > 0 ) {
                sb.append('R');
            } else if (n < 0) {
                sb.append('L');
            } else {
                sb.append('.');
            }
        }
        return sb.toString();
    }
}
