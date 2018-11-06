package mock_sessions.amazon;

import java.util.Random;
import java.util.Stack;
import java.util.stream.IntStream;

public class FindCelebrity {

    /**
     * Idea similar to stack, but we don't actually use stack, just pointer which are just numbers
     *
     * @param n
     * @return
     */
    public int findCelebrity(int n) {
        int a = 0, b = n - 1;
        while (a < b) {
            if (knows(a, b)) {
                a++;
            }
            else
                b--;
        }
        int possibleCel = a;
        for (int i = 0; i < n; i++) {
            if (i != possibleCel) {
                if (!knows(possibleCel, i) && knows(i, possibleCel))
                    continue;
                else
                    return -1;
            }
        }
        return possibleCel;
    }

    /**
     * Idea is following - eliminate those who are not cel for sure. If person know at least
     * one guy - he's not a cel, but other guy may be a cel.
     *
     * @param n
     * @return
     */
    public int findCelebrityStack(int n) {
        Stack<Integer> stack = new Stack();
        IntStream.range(0, n).forEach(i->stack.push(i));

        while(stack.size() != 1) {
            int p1 = stack.pop();
            int p2 = stack.peek();
            if (knows(p1, p2)) {
            } else {
                stack.pop();
                stack.push(p1);
            }
        }

        int possibleCel = stack.pop();
        for (int i = 0; i < n; i++) {
            if (i != possibleCel) {
                if (!knows(possibleCel, i) && knows(i, possibleCel))
                    continue;
                else
                    return -1;
            }
        }
        return possibleCel;
    }

    boolean knows(int a, int b) {
        return new Random().nextBoolean();
    }
}
