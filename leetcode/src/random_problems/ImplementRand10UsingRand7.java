package random_problems;

import java.util.Random;

/**
 * 470. Implement Rand10() Using Rand7()
Medium

Given a function rand7 which generates a uniform random integer in the range 1 to 7, write a function rand10 which generates a uniform random integer in the range 1 to 10.

Do NOT use system's Math.random().

 

Example 1:

Input: 1
Output: [7]
Example 2:

Input: 2
Output: [8,4]
Example 3:

Input: 3
Output: [8,1,10]
 

Note:

rand7 is predefined.
Each testcase has one argument: n, the number of times that rand10 is called.
 

Follow up:

What is the expected value for the number of calls to rand7() function?
Could you minimize the number of calls to rand7()?

https://leetcode.com/problems/implement-rand10-using-rand7/
 */
public class ImplementRand10UsingRand7 {

    /**
     * Idea - if multiply rand7*rand7 will be unformly distributed 1..49. Out of there we
     * can get 1..10 4 times from 1 to 40. The rest we can skip and regenerate.
     * @return
     */
    public int rand10() {
        int sum = 100;
        while (sum > 40) {
            sum = (rand7() - 1)*7 + rand7();
        }
        return ((sum - 1) % 10) + 1;
    }

    Random r = new Random();
    int rand7() {
        return 1 + r.nextInt(7); 
    }
}