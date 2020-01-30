package hashmap;

import static java.util.stream.Collectors.joining;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Frequency Queries
 *
 * Your Frequency Queries submission got 36.67 points.
 * Problem
 * Submissions
 * Leaderboard
 * Discussions
 * Editorial
 * You are given  queries. Each query is of the form two integers described below:
 * -  : Insert x in your data structure.
 * -  : Delete one occurence of y from your data structure, if present.
 * -  : Check if any integer is present whose frequency is exactly . If yes, print 1 else 0.
 *
 * The queries are given in the form of a 2-D array  of size  where  contains the operation, and
 * contains the data element. For example, you are given array . The results of each operation are:
 *
 * Operation   Array   Output
 * (1,1)       [1]
 * (2,2)       [1]
 * (3,2)                   0
 * (1,1)       [1,1]
 * (1,1)       [1,1,1]
 * (2,1)       [1,1]
 * (3,2)                   1
 * Return an array with the output: .
 *
 * Function Description
 *
 * Complete the freqQuery function in the editor below. It must return an array of integers where
 * each element is a  if there is at least one element value with the queried number of
 * occurrences in the current array, or 0 if there is not.
 *
 * freqQuery has the following parameter(s):
 *
 * queries: a 2-d array of integers
 * Input Format
 *
 * The first line contains of an integer , the number of queries.
 * Each of the next  lines contains two integers denoting the 2-d array .
 *
 * Constraints
 *
 * All
 * Output Format
 *
 * Return an integer array consisting of all the outputs of queries of type .
 *
 * Sample Input 0
 *
 * 8
 * 1 5
 * 1 6
 * 3 2
 * 1 10
 * 1 10
 * 1 6
 * 2 5
 * 3 2
 * Sample Output 0
 *
 * 0
 * 1
 * Explanation 0
 *
 * For the first query of type , there is no integer whose frequency is  (). So answer is .
 * For the second query of type , there are two integers in  whose frequency is  (integers =  and
 * ). So, the answer is .
 *
 * Sample Input 1
 *
 * 4
 * 3 4
 * 2 1003
 * 1 16
 * 3 1
 * Sample Output 1
 *
 * 0
 * 1
 * Explanation 1
 *
 * For the first query of type , there is no integer of frequency . The answer is .
 * For the second query of type , there is one integer,  of frequency  so the answer is .
 *
 * Sample Input 2
 *
 * 10
 * 1 3
 * 2 3
 * 3 2
 * 1 4
 * 1 5
 * 1 5
 * 1 4
 * 3 2
 * 2 4
 * 3 2
 * Sample Output 2
 *
 * 0
 * 1
 * 1
 * Explanation 2
 *
 * When the first output query is run, the array is empty. We insert two 's and two 's before the
 * second output query,  so there are two instances of elements occurring twice. We delete a  and
 * run the same query. Now only the instances of  satisfy the query.
 */
public class FrequencyQueries {

    /**
     * use 2 maps one number-freq and another one freq-number_of_elements_with_this_freq
     * @param queries
     * @return
     */
    static List<Integer> freqQuery(int[][] queries) {
        final Map<Integer, Integer> valueToFreq = new HashMap<>();
        final Map<Integer, Integer> freqToOccurrence = new HashMap<>();
        final List<Integer> frequencies = new ArrayList<>();

        int key;
        int value;
        Integer oldFreq;
        Integer newFreq;
        Integer oldOccurrence;
        Integer newOccurrence;
        for (int[] query : queries) {
            key = query[0];
            value = query[1];
            if (key == 3) {
                if (value == 0) {
                    frequencies.add(1);
                }
                frequencies.add(freqToOccurrence.get(value) == null ? 0 : 1);
            } else {
                oldFreq = valueToFreq.get(value);
                oldFreq = oldFreq == null ? 0 : oldFreq;
                oldOccurrence = freqToOccurrence.get(oldFreq);
                oldOccurrence = oldOccurrence == null ? 0 : oldOccurrence;

                if (key == 1) {
                    newFreq = oldFreq + 1;
                } else {
                    newFreq = oldFreq - 1;
                }
                newOccurrence = freqToOccurrence.get(newFreq);
                newOccurrence = newOccurrence == null ? 0 : newOccurrence;

                if (newFreq < 1) {
                    valueToFreq.remove(value);
                } else {
                    valueToFreq.put(value, newFreq);
                }

                if ((oldOccurrence - 1) < 1) {
                    freqToOccurrence.remove(oldFreq);
                } else {
                    freqToOccurrence.put(oldFreq, oldOccurrence - 1);
                }
                freqToOccurrence.put(newFreq, newOccurrence + 1);
            }
        }
        return frequencies;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(System.in))) {

            int q = Integer.parseInt(bufferedReader.readLine().trim());
            int[][] queries = new int[q][2];

            for (int i = 0; i < q; i++) {
                String[] query = bufferedReader.readLine().split(" ");
                queries[i][0] = Integer.parseInt(query[0]);
                queries[i][1] = Integer.parseInt(query[1]);
            }

            List<Integer> ans = freqQuery(queries);

            try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(System.getenv("OUTPUT_PATH")))) {

                bufferedWriter.write(ans.stream().map(Object::toString)
                    .collect(joining("\n")) + "\n");
            }
        }
    }
}
