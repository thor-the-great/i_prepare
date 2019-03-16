package random_problems;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * 353. Design Snake Game
 * Medium
 *
 * Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are
 * not familiar with the game.
 *
 * The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
 *
 * You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's
 * score both increase by 1.
 *
 * Each food appears one by one on the screen. For example, the second food will not appear until the first food was
 * eaten by the snake.
 *
 * When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
 *
 * Example:
 *
 * Given width = 3, height = 2, and food = [[1,2],[0,1]].
 *
 * Snake snake = new Snake(width, height, food);
 *
 * Initially the snake appears at position (0,0) and the food at (1,2).
 *
 * |S| | |
 * | | |F|
 *
 * snake.move("R"); -> Returns 0
 *
 * | |S| |
 * | | |F|
 *
 * snake.move("D"); -> Returns 0
 *
 * | | | |
 * | |S|F|
 *
 * snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )
 *
 * | |F| |
 * | |S|S|
 *
 * snake.move("U"); -> Returns 1
 *
 * | |F|S|
 * | | |S|
 *
 * snake.move("L"); -> Returns 2 (Snake eats the second food)
 *
 * | |S|S|
 * | | |S|
 *
 * snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 */
public class SnakeGame {

    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */

    int[][] f;
    int score = 0;
    int rows;
    int cols;
    LinkedList<int[]> snakeList;

    static Map<String, int[]> dirMap = new HashMap();

    static {
        dirMap.put("R", new int[]{ 0,  1});
        dirMap.put("L", new int[]{ 0, -1});
        dirMap.put("U", new int[]{-1,  0});
        dirMap.put("D", new int[]{ 1,  0});
    }

    public SnakeGame(int width, int height, int[][] food) {
        f = food;
        rows = height;
        cols = width;
        score = 0;
        snakeList = new LinkedList();
        snakeList.addFirst(new int[] {0, 0});
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int[] dir = dirMap.get(direction);
        //update head position
        int[] head = snakeList.getFirst();
        int[] headPosition = new int[] {head[0] + dir[0], head[1] + dir[1]};

        if (headPosition[0] < 0 || headPosition[0] >= rows ||
                headPosition[1] < 0 || headPosition[1] >= cols) {
            return -1;
        }
        //check if we eat the food
        if (f.length <= score || !(headPosition[0] == f[score][0] && headPosition[1] == f[score][1])) {
            snakeList.removeLast();
            //check if haven't eat ourselves
            Iterator<int[]> it = snakeList.iterator();
            while (it.hasNext()) {
                int[] curr = it.next();
                if (headPosition[0] == curr[0] && headPosition[1] == curr[1])
                    return -1;
            }
            snakeList.addFirst(headPosition);
        }
        else {
            score++;
            snakeList.addFirst(headPosition);
        }

        return score;
    }

    public static void main(String[] args) {
        /**
         * ["SnakeGame","move","move","move","move","move","move","move","move","move","move","move","move"]
         * [[3,3,[[2,0],[0,0],[0,2],[2,2]]],["D"],["D"],["R"],["U"],["U"],["L"],["D"],["R"],["R"],["U"],["L"],["D"]]
         */
        SnakeGame obj = new SnakeGame(3, 3, new int[][]{
                {2,0},
                {0,0},
                {0,2},
                {2,2}
        });

        System.out.println(obj.move("D"));
        System.out.println(obj.move("D"));
        System.out.println(obj.move("R"));
        System.out.println(obj.move("U"));
        System.out.println(obj.move("U"));
        System.out.println(obj.move("L"));

        System.out.println(obj.move("D"));
        System.out.println(obj.move("R"));
        System.out.println(obj.move("R"));
        System.out.println(obj.move("U"));
        System.out.println(obj.move("L"));
        System.out.println(obj.move("D"));

    }
}
