package random_problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1086. High Five
 * Easy
 *
 * Given a list of scores of different students, return the average score of each student's top five scores in the order of each student's id.
 *
 * Each entry items[i] has items[i][0] the student's id, and items[i][1] the student's score.  The average score is calculated using integer division.
 *
 *
 *
 * Example 1:
 *
 * Input: [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
 * Output: [[1,87],[2,88]]
 * Explanation:
 * The average of the student with id = 1 is 87.
 * The average of the student with id = 2 is 88.6. But with integer division their average converts to 88.
 *
 *
 * Note:
 *
 * 1 <= items.length <= 1000
 * items[i].length == 2
 * The IDs of the students is between 1 to 1000
 * The score of the students is between 1 to 100
 * For each student, there are at least 5 scores
 */
public class HighFive {

    public int[][] highFive(int[][] items) {
        //collect all marks for every student
        List<List<Integer>> m = new ArrayList();
        for (int[] item : items) {
            int student = item[0];
            if (m.size() < student) {
                List<Integer> mark = new ArrayList();
                mark.add(item[1]);
                m.add(mark);
            } else {
                m.get(student - 1).add(item[1]);
            }
        }
        //prepare result array
        int[][] res = new int[m.size()][2];
        for (int i = 0; i < m.size(); i++) {
            List<Integer> markList = m.get(i);
            if (markList == null) continue;
            int avg = 0;
            //get the number of marks for one student
            int size = markList.size();
            //if we have more than 5 marks - get the high five, sort and take first 5
            if (size > 5) {
                Collections.sort(markList, Collections.reverseOrder());
                for (int j = 0; j < 5; j++)
                    avg+=markList.get(j);
                avg/=5;
            } else {
                //if it's 5 or less - just take all marks
                for (int j = 0; j < size; j++)
                    avg+=markList.get(j);
                avg/=size;
            }
            res[i] = new int[] {i + 1, avg};
        }
        return res;
    }
}
