package challenges;

/**
 * Alice is playing an arcade game and wants to climb to the top of the leaderboard and wants to track her ranking.
 * The game uses Dense Ranking, so its leaderboard works like this:
 *
 * The player with the highest score is ranked number  on the leaderboard.
 * Players who have equal scores receive the same ranking number, and the next player(s) receive the immediately
 * following ranking number.
 * For example, the four players on the leaderboard have high scores of , , , and . Those players will have
 * ranks , , , and , respectively. If Alice's scores are ,  and , her rankings after each game are ,  and .
 *
 * Function Description
 *
 * Complete the climbingLeaderboard function in the editor below. It should return an integer array where each element
 * represents Alice's rank after the  game.
 *
 * climbingLeaderboard has the following parameter(s):
 *
 * scores: an array of integers that represent leaderboard scores
 * alice: an array of integers that represent Alice's scores
 * Input Format
 *
 * The first line contains an integer , the number of players on the leaderboard.
 * The next line contains  space-separated integers , the leaderboard scores in decreasing order.
 * The next line contains an integer, , denoting the number games Alice plays.
 * The last line contains  space-separated integers , the game scores.
 *
 * Constraints
 *
 *  for
 *  for
 * The existing leaderboard, , is in descending order.
 * Alice's scores, , are in ascending order.
 * Subtask
 *
 * For  of the maximum score:
 *
 * Output Format
 *
 * Print  integers. The  integer should indicate Alice's rank after playing the  game.
 *
 * Sample Input 1
 *
 * CopyDownload
 * Array: scores
 * 100
 * 100
 * 50
 * 40
 * 40
 * 20
 * 10
 *
 *
 *
 *
 *
 * Array: alice
 * 5
 * 25
 * 50
 * 120
 *
 *
 * 7
 * 100 100 50 40 40 20 10
 * 4
 * 5 25 50 120
 * Sample Output 1
 *
 * 6
 * 4
 * 2
 * 1
 * Explanation 1
 *
 * Alice starts playing with  players already on the leaderboard, which looks like this:
 *
 * image
 *
 * After Alice finishes game , her score is  and her ranking is :
 *
 * image
 *
 * After Alice finishes game , her score is  and her ranking is :
 *
 * image
 *
 * After Alice finishes game , her score is  and her ranking is tied with Caroline at :
 *
 * image
 *
 * After Alice finishes game , her score is  and her ranking is :
 *
 * image
 *
 *
 * Sample Input 2
 *
 * CopyDownload
 * Array: scores
 * 100
 * 90
 * 90
 * 80
 * 75
 * 60
 *
 *
 *
 *
 *
 * Array: alice
 * 50
 * 65
 * 77
 * 90
 * 102
 *
 *
 * 6
 * 100 90 90 80 75 60
 * 5
 * 50 65 77 90 102
 * Sample Output 2
 *
 * 6
 * 5
 * 4
 * 2
 * 1
 */
public class ClimbingLeaderboard {

    // Complete the climbingLeaderboard function below.
    static int[] climbingLeaderboard(int[] scores, int[] alice) {
        int[] res = new int[alice.length];
        int[] ranks = new int[scores.length];
        ranks[0] = 1;
        for (int i = 1; i < ranks.length; i++) {
            if (scores[i] < scores[i - 1])
                ranks[i] = ranks[i - 1] + 1;
            else
                ranks[i] = ranks[i - 1];
        }
        int prev = -1;
        for (int i = 0; i < alice.length; i++) {
            int a = alice[i];
            if (a > scores[0]) {
                res[i] = 1;
                continue;
            } else if (a < scores[scores.length - 1]) {
                res[i] = ranks[ranks.length - 1] + 1;
                continue;
            }

            int idx = -1;
            int l = 0, r = prev == -1 ? scores.length - 1 : prev;

            while (l < r) {
                int m = (r + l)/2;
                if (scores[m] == a) {
                    idx = m;
                    break;
                }

                if (scores[m] > a) {
                    l = m + 1;
                } else
                    r = m;
            }

            if (idx != -1) {
                res[i] = ranks[idx];
                prev = idx;
            }
            else {
                res[i] = ranks[l];
                prev = l;
            }
        }

        return res;
    }
}
