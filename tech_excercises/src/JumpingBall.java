import java.util.Random;

public class JumpingBall {

    public boolean canStop(boolean[] runaway, int initSpeed) {
        return helper(runaway, initSpeed, 0);

    }

    boolean helper(boolean[] runaway, int speed, int idx) {
        if ( idx >= runaway.length || !runaway[idx] ||
            speed < 0 )
            return false;

        if (speed == 0)
            return true;

        for (int nextSpeed = speed - 1; nextSpeed <= speed + 1; nextSpeed++)
            if (helper(runaway, nextSpeed, idx + nextSpeed))
                return true;

        return false;
    }

    public static void main(String[] args) {
        JumpingBall obj = new JumpingBall();
        Random rand = new Random();

        int N = 20;

        boolean[] runaway = new boolean[] {
                true, false, true, true, true, false, false, true, false, true, false, true, false
        };


        System.out.println(obj.canStop(runaway, 3));
    }
}
