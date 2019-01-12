import diff_problems.TreeNode;
import linked_list.ListNode;
import util.Point;
import utils.StringUtils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.IntStream;

public class SolutionDailyCodingJanuary2019 {

    /**
     * This problem was asked by Google.
     *
     * Given an array of integers, return a new array where each element in the new array is the number of smaller
     * elements to the right of that element in the original input array.
     *
     * For example, given the array [3, 4, 9, 6, 1], return [1, 1, 2, 1, 0], since:
     *
     * There is 1 smaller element to the right of 3
     * There is 1 smaller element to the right of 4
     * There are 2 smaller elements to the right of 9
     * There is 1 smaller element to the right of 6
     * There are no smaller elements to the right of 1
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        //idea - we use merge sort algo, those left elements that will have smaller elements from right - +1 for every
        //such element. Rest is optimization. O(nlogn).
        int n = nums.length;
        int[][] arr = new int[n][2];
        int[][] aux = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[] {nums[i], i};
        }
        int[] count = new int[n];
        sort(arr, count, 0, n - 1, aux);
        List<Integer> list = new ArrayList();
        for (int i = 0; i < n; i++){
            list.add(count[i]);
        }
        return list;
    }

    private void sort(int[][] arr, int[] count, int lo, int hi, int[][] aux) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(arr, count, lo, mid, aux);
        sort(arr, count, mid + 1, hi, aux);
        for (int i = lo; i <= hi; i++) {
            aux[i] = arr[i];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i == mid + 1) { arr[k] = aux[j++]; }
            else if (j == hi + 1 || aux[i][0] <= aux[j][0]) {
                count[aux[i][1]] += j - (mid + 1);
                arr[k] = aux[i++];
            } else {
                arr[k] = aux[j++];
            }
        }
    }

    /**
     * You are given a list of data entries that represent entries and exits of groups of people into a building. An
     * entry looks like this:
     *
     * {"timestamp": 1526579928, count: 3, "type": "enter"}
     *
     * This means 3 people entered the building. An exit looks like this:
     *
     * {"timestamp": 1526580382, count: 2, "type": "exit"}
     *
     * This means that 2 people exited the building. timestamp is in Unix time.
     *
     * Find the busiest period in the building, that is, the time with the most people in the building. Return it as a
     * pair of (start, end) timestamps. You can assume the building always starts off and ends up empty, i.e. with 0
     * people inside.
     * @param entries
     * @return
     */
    List<int[]> busiestTime(List<int[]> entries) {
        //each entry is in form [time, numOfPeople, type]
        //type is 1 - enter, 2 - exit
        List<int[]> res = new LinkedList();
        List<int[]> sortedIntervals = new ArrayList<>();
        for (int[] entry : entries) {
            int idx = entry[1];
            if (entry[2] == 2)
                idx = -idx;
            int[] e = new int[] {entry[0], idx};
            sortedIntervals.add(e);
        }
        sortedIntervals.sort(Comparator.comparingInt(e->e[0]));
        int max = 0;
        int numOfPeople = 0;
        int[] oneRes = new int[]{-1, -1};
        for (int[] e : sortedIntervals) {
            if (max == numOfPeople && e[1] < 0) {
                oneRes[1] = e[0];
                res.add(Arrays.copyOf(oneRes, oneRes.length));
            }
            numOfPeople += e[1];
            if (numOfPeople >= max) {
                if (numOfPeople > max)
                    res.clear();
                max = numOfPeople;
                oneRes[0] = e[0];
            }
        }
        return res;
    }

    /**
     * This problem was asked by Google.
     *
     * You are given a starting state start, a list of transition probabilities for a Markov chain, and a number of
     * steps num_steps. Run the Markov chain starting from start for num_steps and compute the number of times we
     * visited each state.
     *
     * For example, given the starting state a, number of steps 5000, and the following transition probabilities:
     *
     * [
     *   ('a', 'a', 0.9),
     *   ('a', 'b', 0.075),
     *   ('a', 'c', 0.025),
     *   ('b', 'a', 0.15),
     *   ('b', 'b', 0.8),
     *   ('b', 'c', 0.05),
     *   ('c', 'a', 0.25),
     *   ('c', 'b', 0.25),
     *   ('c', 'c', 0.5)
     * ]
     * One instance of running this Markov chain might produce { 'a': 3012, 'b': 1656, 'c': 332 }.
     */
    public void calculateMarkovState() {
        int num = 3;
        double[][] stateTrans = new double[][] {
                {0.9,   0.075,  0.025},
                {0.15,  0.8,    0.05},
                {0.25,  0.25,   0.5}
        };
        double[] initState = new double[] {
                1.0, 0.0, 0.0
        };
        Map<Integer, Double> c = new HashMap<>();
        for (int i = 0; i < 5000; i++) {
            initState = nextState(initState, stateTrans);
            for (int j =0; j < initState.length; j++) {
                c.put(j, c.getOrDefault(j, 0.0) + initState[j]);
            }
        }
        for(int i =0; i< initState.length; i++) {
            System.out.println(i + " = " + Math.floor(c.get(i)) +", ");
        }
    }

    private double[] nextState(double[] state, double[][] transition) {
        double[] next = new double[state.length];
        for (int c = 0; c < state.length; c++) {
            double el = 0;
            for (int cc = 0; cc < state.length; cc++) {
                el += transition[cc][c] * state[cc];
            }
            next[c] = el;
        }
        return next;
    }

    public static void main(String[] args) {
        SolutionDailyCodingJanuary2019 obj = new SolutionDailyCodingJanuary2019();

        System.out.println("---- count smaller numbers of self ----");
        int[] arr;
        List<Integer> smallers;
        arr = new int[] {5,2,6,1};
        smallers = obj.countSmaller(arr);
        System.out.print(StringUtils.listToString(smallers));//[2, 1, 1, 0]

        System.out.println("\n---- count busiest time in the building ----");
        //each entry is in form [time, numOfPeople, type], type is 1 - enter, 2 - exit
        List<int[]> entries;
        entries = new LinkedList<>();
        entries.add(new int[] {1, 2, 1});
        entries.add(new int[] {3, 3, 1});
        entries.add(new int[] {4, 1, 2});
        entries.add(new int[] {5, 1, 1});
        entries.add(new int[] {8, 3, 1});
        entries.add(new int[] {20, 8, 2});
        entries.add(new int[] {25, 4, 1});
        entries.add(new int[] {28, 4, 1});
        entries.add(new int[] {40, 5, 2});
        entries.add(new int[] {45, 3, 2});

        List<int[]> time = obj.busiestTime(entries);
        for (int[] t : time) {
            System.out.println("[" + t[0] + ", " + t[1] + "] , ");
        }

        System.out.println("--- calculate markov state ----");
        obj.calculateMarkovState();
    }
}
