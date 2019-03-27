import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * You are jumping on the crazy bouncing ball, you are trying to stop while avoiding thorns (spikes) along the way.
 *
 * You're given array that represents flat runaway with spikes on it. It's array of booleans, where index represents
 * exact point on the runaway. It's true if the spot in clear and false if there is a spike on it.
 *
 * You are starting with some initial speed S (non-negative integer). It indicates how many spots you can travel
 * on each jump.
 *
 * Every time you land on the spot you can adjust your speed by 1 before the next jump (S will be S - 1, S or S + 1)
 *
 * You can safely stop anywhere along the runaway (doesn't need to be an end of the array). You stop when speed became
 * 0. But if you land on the spike you ball bursts and the game is over
 *
 * Output the boolean value true if it's possible to stop, false otherwise
 *
 * Example: [true, true, false, true, false, true, true, false], s = 3
 * Output: true
 *
 *             0       X, true, false, true, false, true, true, false
 *
 *             1 - 1   _, true,     B, false, false, true, true, false - s = 2, burst
 *             1 - 2   _, true, false,     X, false, true, true, false - s = 3
 *             1 - 3   _, true, false, false,     B, true, true, false - s = 4, burst
 *
 *             2 - 1   _, true, false,     _, false,    X, true, false - s = 2
 *             2 - 2   _, true, false,     _, false, true,    X, false - s = 3, out on next jump
 *             2 - 3   _, true, false,     _, false, true, true,     X - s = 4, out on next jump
 *
 *
 *             3 - 1   _, true, false,     _, false,    _,    X, false - s = 1
 *
 *             4 - 1   _, true, false,     _, false, true,    _, false - s = 0
 *
 */
public class JumpingBall {

    public boolean canStop(boolean[] runaway, int initSpeed) {
        dp = new HashMap<>();
        return helper(runaway, initSpeed, 0);
    }

    Map<Long, Boolean> dp;

    boolean helper(boolean[] runaway, int speed, int idx) {
        long key = (speed<<31) + idx;
        if (dp.containsKey(key))
            return dp.get(key);

        if ( idx >= runaway.length || !runaway[idx] ||
            speed < 0 ) {
            dp.put(key, false);
            return false;
        }

        if (speed == 0) {
            dp.put(key, true);
            return true;
        }


        for (int nextSpeed = speed - 1; nextSpeed <= speed + 1; nextSpeed++)
            if (helper(runaway, nextSpeed, idx + nextSpeed)) {
                long nextKey = (nextSpeed<<31) + (idx + nextSpeed);
                dp.put(nextKey, true);
                return true;
            }
        dp.put(key, false);
        return false;
    }

    public static void main(String[] args) {
        JumpingBall obj = new JumpingBall();
        Random rand = new Random();

        boolean[] runaway = new boolean[] {
                true, false, true, true, true, false, false, true, false, true, false, true, false
        };


        System.out.println(obj.canStop(runaway, 3));

        runaway = new boolean[] {
                true, true, false, false, false, true
        };
        System.out.println(obj.canStop(runaway, 2));

        /*
            0       X, true, false, false, false, true

            1 - 1   _,    X, false, false, false, true - s = 1
            1 - 2   _,     ,     Z, false, false, true - s = 2, burst
            1 - 3   _,     ,      ,     Z, false, true - s = 3, burst

            2 - 1   _,    _, false, false, false, true - s = 0

         */

        runaway = new boolean[] {
                true, true, false, true, false, true, true, false
        };
        System.out.println(obj.canStop(runaway, 3));//true

        /*
            0       X, true, false, true, false, true, true, false

            1 - 1   _, true,     Z, false, false, true, true, false - s = 2, burst
            1 - 2   _, true, false,     X, false, true, true, false - s = 3
            1 - 3   _, true, false, false,     Z, true, true, false - s = 4, burst

            2 - 1   _, true, false,     _, false,    X, true, false - s = 2
            2 - 2   _, true, false,     _, false, true,    X, false - s = 3, burst on next jump
            2 - 3   _, true, false,     _, false, true, true,     X - s = 4, burst on next jump


            3 - 1   _, true, false,     _, false,    _,    X, false - s = 1

            4 - 1   _, true, false,     _, false, true,    _, false - s = 0

         */

        runaway = new boolean[] {
                true, true, false, false, false, true, true, false
        };
        System.out.println(obj.canStop(runaway, 3));

        int N = 10_000_000;
        int prob = 5;
        //long start = System.nanoTime();
        long start = System.currentTimeMillis();

        for (int nn = 0; nn < 100; nn++) {
            runaway = new boolean[N];
            for (int i = 0; i < N; i++) {
                if (prob - 1 != rand.nextInt(prob))
                    runaway[i] = true;
            }
            obj.canStop(runaway, 30);
            //System.out.print(obj.canStop(runaway, prob));
        }

        //long elapsed = System.nanoTime() - start;
        long elapsed = System.currentTimeMillis() - start;;
        System.out.println(" elapsed time (ns) : " + elapsed );
    }
}
