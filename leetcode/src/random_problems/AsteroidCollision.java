package random_problems;

import java.util.Stack;

/**
 * 735. Asteroid Collision
 * Medium
 *
 * We are given an array asteroids of integers representing asteroids in a row.
 *
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning
 * right, negative meaning left). Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If
 * both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 *
 * Example 1:
 * Input:
 * asteroids = [5, 10, -5]
 * Output: [5, 10]
 * Explanation:
 * The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
 * Example 2:
 * Input:
 * asteroids = [8, -8]
 * Output: []
 * Explanation:
 * The 8 and -8 collide exploding each other.
 * Example 3:
 * Input:
 * asteroids = [10, 2, -5]
 * Output: [10]
 * Explanation:
 * The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.
 * Example 4:
 * Input:
 * asteroids = [-2, -1, 1, 2]
 * Output: [-2, -1, 1, 2]
 * Explanation:
 * The -2 and -1 are moving left, while the 1 and 2 are moving right.
 * Asteroids moving the same direction never meet, so no asteroids will meet each other.
 * Note:
 *
 * The length of asteroids will be at most 10000.
 * Each asteroid will be a non-zero integer in the range [-1000, 1000]..
 *
 */
public class AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {

        Stack<Integer> s = new Stack();
        for (int a : asteroids) {
            if (a < 0 ) {
                if (s.isEmpty() || s.peek() < 0) {
                    s.push(a);
                    continue;
                }
                boolean crush = false;
                while (!s.isEmpty() && s.peek() > 0 && s.peek() <= - a) {
                    int t = s.peek();
                    if (t == -a ) {
                        s.pop();
                        break;
                    } else {
                        s.pop();
                        if (s.isEmpty() || s.peek() < 0)
                            crush = true;
                    }
                }
                if (crush)
                    s.push(a);
            } else {
                s.push(a);
            }
        }

        int[] arr = new int[s.size()];
        int i = s.size() - 1;
        while(!s.isEmpty())
            arr[i--] = s.pop();

        return arr;
    }
}
