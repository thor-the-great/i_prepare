import diff_problems.TreeNode;
import linked_list.ListNode;
import linked_list.StringUtils;
import path.google.TrappedRainWater;
import sun.reflect.generics.tree.Tree;
import trees.TreeUtils;
import util.NaryTreeNode;
import utils.ArrayUtil;

import java.util.*;

public class SolutionDailyCodingApril2019 {

    /**
     * This problem was asked by Fitbit.
     *
     * Given a linked list, rearrange the node values such that they appear in alternating low -> high -> low ->
     * high ... form. For example, given 1 -> 2 -> 3 -> 4 -> 5, you should return 1 -> 3 -> 2 -> 5 -> 4.
     * @param node
     * @return
     */
    public ListNode alternatingOrder(ListNode node) {
        /**
         * Idea - do the sorting of the list, use merge sort. Then split list in the middle and merge to halves
         * using elements from half in turns
         */
        ListNode sortedList = sort(node);
        ListNode midOfSorted = getMiddle(sortedList);
        ListNode midNextOfSorted = midOfSorted.next;
        midOfSorted.next = null;
        ListNode head = sortedList;
        while (sortedList != null) {
            ListNode n = sortedList.next;
            if (midNextOfSorted != null) {
                sortedList.next = midNextOfSorted;
                midNextOfSorted = midNextOfSorted.next;
                sortedList.next.next = n;
            }
            sortedList = n;
        }

        return head;
    }

    ListNode sort(ListNode node) {
        if (node == null || node.next == null)
            return node;

        ListNode midNode = getMiddle(node);
        ListNode midNextNode = midNode.next;
        midNode.next = null;

        ListNode leftPart = sort(node);
        ListNode rightPart = sort(midNextNode);

        ListNode sortedList = sortedMerge(leftPart, rightPart);

        return sortedList;
    }

    ListNode sortedMerge(ListNode r, ListNode l) {
        if (r == null)
            return l;
        if (l == null)
            return r;

        ListNode result;

        if (l.val < r.val) {
            result = l;
            result.next = sortedMerge(l.next, r);
        } else {
            result = r;
            result.next = sortedMerge(l, r.next);
        }

        return result;
    }

    ListNode getMiddle(ListNode n) {
        if ( n == null)
            return n;

        ListNode slow = n;
        ListNode fast = n.next;

        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
        }

        return slow;
    }

    /**
     * This problem was asked by WhatsApp.
     *
     * Given an array of integers out of order, determine the bounds of the smallest window that must be sorted in
     * order for the entire array to be sorted. For example, given [3, 7, 5, 6, 9], you should return (1, 3).
     * @param arr
     * @return
     */
    int[] shortestSubarrayToKeepSorted(int[] arr) {
        int[] res = new int[] {-1, -1};

        int N = arr.length;
        int l = 0;
        //finding first non-sorted element
        while (l < N - 1) {
            if (arr[l] > arr[l + 1])
                break;
            l++;
        }

        //it can be sorted already
        if (l == N - 1)
            return res;

        int r = N - 1;
        //searching for right one - first unsorted starting from the end
        while (r > 0) {
            if (arr[r] < arr[r - 1])
                break;
            r--;
        }
        //finding min and max in the left-right fragment
        int max = arr[l], min = arr[l];
        for (int i = l + 1; i <=r; i++ ) {
            if (arr[i] > max)
                max = arr[i];

            if (arr[i] < min)
                min = arr[i];
        }
        //checking if there are elements to the left of the left
        for (int i = 0; i < l; i++ ) {
            if (arr[i] > min) {
                l = i;
                break;
            }
        }
        //checking if there are elements to the right of the right
        for (int i = N - 1; i >= r; i--) {
            if (arr[i] < max) {
                r = i;
                break;
            }
        }
        res[0] = l;
        res[1] = r;

        return res;
    }

    /**
     * This problem was asked by Morgan Stanley.
     *
     * In Ancient Greece, it was common to write text with the first line going left to right, the second line going
     * right to left, and continuing to go back and forth. This style was called "boustrophedon".
     *
     * Given a binary tree, write an algorithm to print the nodes in boustrophedon order.
     *
     * For example, given the following tree:
     *
     *        1
     *     /     \
     *   2         3
     *  / \       / \
     * 4   5     6   7
     * You should return [1, 3, 2, 4, 5, 6, 7].
     *
     * @param root
     * @return
     */
    List<Integer> boustrophedonTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean direct = true;

        LinkedList<Integer> level = new LinkedList<>();
        while (!q.isEmpty()) {
            int levelNodeSize = q.size();
            for (int i = 0; i < levelNodeSize; i++)
            {
                TreeNode n = q.poll();
                if (direct)
                    level.addLast(n.val);
                else
                    level.addFirst(n.val);

                if (n.left != null) q.add(n.left);
                if (n.right != null) q.add(n.right);
            }
            res.addAll(level);
            level.clear();
            direct = !direct;
        }

        return res;
    }

    /**
     * This problem was asked by Two Sigma.
     *
     * Ghost is a two-person word game where players alternate appending letters to a word. The first person who
     * spells out a word, or creates a prefix for which there is no possible continuation, loses. Here is a sample
     * game:
     *
     * Player 1: g
     * Player 2: h
     * Player 1: o
     * Player 2: s
     * Player 1: t [loses]
     * Given a dictionary of words, determine the letters the first player should start with, such that with optimal
     * play they cannot lose.
     *
     * For example, if the dictionary is ["cat", "calf", "dog", "bear"], the only winning start letter would be b.
     * @param dict
     * @return
     */
    public List<Character> optimalGhostGame(String[] dict) {
        List<Character> res = new ArrayList<>();
        if (dict == null || dict.length == 0)
            return  res;

        Map<Character, List<Integer>> m = new HashMap<>();
        for (String w : dict) {
            char fc = w.charAt(0);
            int len = w.length();
            if (!m.containsKey(fc)) {
                m.put(fc, new ArrayList<>());
            }
            m.get(fc).add(len);
        }

        for(Character ch : m.keySet()) {
            List<Integer> lens = m.get(ch);
            boolean isOk = true;
            for (int len : lens) {
                if (len % 2 == 1) {
                    isOk = false;
                    break;
                }
            }
            if (isOk)
                res.add(ch);
        }

        return res;
    }

    /**
     * This problem was asked by Pinterest.
     *
     * The sequence [0, 1, ..., N] has been jumbled, and the only clue you have for its order is an array
     * representing whether each number is larger or smaller than the last. Given this information, reconstruct an
     * array that is consistent with it. For example, given [None, +, +, -, +], you could return [1, 2, 3, 0, 4].
     *
     * @param relations
     * @return
     */
    public int[] restoreArray(char[] relations) {
        //Idea: scan and count number of '-'. Every '-' means we need to start + number from +1.
        //then for + increment and for '-' because it could be '+', '+' and '-', '-'
        int N = relations.length;
        int[] res = new int[N];
        int minCount = 0;
        for (int i = 0; i < N; i++) {
            if (relations[i] == '-') {
                minCount++;
            }
        }
        int min = minCount - 1;
        int plus = minCount;

        for (int i = 0; i < N; i++) {
            if (relations[i] == '-') {
                res[i] = min--;
            } else
                res[i] = plus++;
        }

        return res;
    }

    Map<Character, String> huffmanCoding(String sentence) {
        Map<Character, String> res = new HashMap<>();

        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : sentence.toCharArray()) {
            if (Character.isLetter(ch)) {
                ch = Character.toLowerCase(ch);
                freq.put(ch, freq.getOrDefault(ch, 0) + 1);
            }
        }
        Comparator<HTNode> comp = (n1, n2)->{
            return n1.freq - n2.freq;
        };
        PriorityQueue<HTNode> pq = new PriorityQueue(comp);
        for (char ch : freq.keySet()) {
            HTNode node = new HTNode();
            node.ch = ch;
            node.freq = freq.get(ch);
            pq.add(node);
        }
        HTNode root = null;
        while(pq.size() > 1) {
            HTNode min = pq.poll();
            HTNode nextMin = pq.poll();
            HTNode compaund = new HTNode();
            compaund.freq = min.freq + nextMin.freq;
            compaund.left = min;
            compaund.right = nextMin;
            compaund.ch = '-';
            root = compaund;
            pq.add(compaund);
        }

        traverseTree(root, "", res);

        return res;
    }

    void traverseTree(HTNode n, String code, Map<Character, String> res) {
        if (n.left == null && n.right == null)
            res.put(n.ch, code);

        if (n.left != null)
            traverseTree(n.left,  code + "0", res);

        if (n.right != null)
            traverseTree(n.right,  code + "1", res);
    }

    class HTNode {
        char ch;
        int freq;
        HTNode left;
        HTNode right;
    }

    char[] SEPARATORS = new char[] {',',';',':'};
    char[] TERMINALS = new char[] {'.','?','!','‽'};

    String sentence;
    boolean isValid = false;

    public String validSentence(String s) {
        sentence = s;

        Set<Character> validChars = new HashSet<>();
        for (char ch : SEPARATORS)
            validChars.add(ch);
        for (char ch : TERMINALS)
            validChars.add(ch);

        boolean isValid = true;
        //int p = 0; p < s.length(); p++
        int p = 0;
        while(p < s.length() && isValid) {
            char ch = sentence.charAt(p);
            //first char in a sentence
            if (p == 0) {
                if (!(Character.isLetter(ch) && Character.isUpperCase(ch))) {
                    isValid = false;
                }
            } else if (p == 1) {
                if (!((Character.isLetter(ch) && Character.isLowerCase(ch)) || ch == ' ')) {
                    isValid = false;
                }
            } else if (p == s.length() - 1) {
                if (ch != '.') {
                    isValid = false;
                }
            } else if (ch == ' ') {
                //check if previous character was not a space
                if (s.charAt(p - 1) == ' ') {
                    isValid = false;
                }
            } else {
                if (((Character.isLetter(ch) && Character.isLowerCase(ch)) ||
                    validChars.contains(ch))) {
                    isValid = true;
                } else
                    isValid = false;
            }
            p++;
        }

        return isValid ? sentence : "-";
    }

    public static void main(String[] args) {
        SolutionDailyCodingApril2019 obj = new SolutionDailyCodingApril2019();

        System.out.println("---- smallest int that is not a sum of subset, array is sorted ----");
        ListNode node = new ListNode(4, new ListNode(7, new ListNode(2, new ListNode(6, new ListNode(9)))));
        System.out.println(StringUtils.singlyListNodeToString(node));

        System.out.println(StringUtils.singlyListNodeToString(obj.alternatingOrder(node)));

        node = new ListNode(12, new ListNode(5, new ListNode(2, new ListNode(7))));
        System.out.println(StringUtils.singlyListNodeToString(node));

        System.out.println(StringUtils.singlyListNodeToString(obj.alternatingOrder(node)));

        node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(StringUtils.singlyListNodeToString(node));

        System.out.println(StringUtils.singlyListNodeToString(obj.alternatingOrder(node)));

        System.out.println("--- find the shortest sub-array that will make array sorted ---");
        int[] subArrSorted;
        subArrSorted = obj.shortestSubarrayToKeepSorted(new int[] {2, 8, 7, 3, 5, 10, 9, 12});
        System.out.println(Arrays.toString(subArrSorted));

        System.out.println(Arrays.toString(obj.shortestSubarrayToKeepSorted(new int[] {3, 7, 5, 6, 9})));

        System.out.println(Arrays.toString(obj.shortestSubarrayToKeepSorted(new int[] {
                10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60
        })));

        System.out.println(Arrays.toString(obj.shortestSubarrayToKeepSorted(new int[] {
                0, 1, 15, 25, 6, 7, 30, 40, 50
        })));

        System.out.println("--- algorithm to traverse the nodes in boustrophedon order ---");
        List<Integer> zigZagTraversal;
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(5)),
                new TreeNode(3,
                    new TreeNode(6),
                    new TreeNode(7)));
        zigZagTraversal = obj.boustrophedonTraversal(root1);

        zigZagTraversal.forEach(i-> System.out.print(i + ", "));
        System.out.print("\n");

        root1 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4,
                                new TreeNode(7),
                                new TreeNode(8)),
                        null),
                new TreeNode(3,
                        new TreeNode(5,
                                new TreeNode(9),
                                null),
                        new TreeNode(6)));
        zigZagTraversal = obj.boustrophedonTraversal(root1);

        zigZagTraversal.forEach(i-> System.out.print(i + ", "));
        System.out.print("\n");

        System.out.println("--- optimal strategy for ghost game ---");
        List<Character> optimal;
        String[] dict;
        dict = new String[] {
                "cat", "calf", "dog", "bear"
        };
        optimal = obj.optimalGhostGame(dict);
        System.out.println("Words : " + Arrays.toString(dict));
        System.out.print("Optimal letters : " ); optimal.forEach(l->System.out.print(l + ", "));
        System.out.print("\n");

        dict = new String[] {
                "cat", "calf", "dog", "bear", "am", "arm", "fade"
        };
        optimal = obj.optimalGhostGame(dict);
        System.out.println("Words : " + Arrays.toString(dict));
        System.out.print("Optimal letters : " ); optimal.forEach(l->System.out.print(l + ", "));
        System.out.print("\n");

        System.out.println("--- restore array by the set of relations between every two elements ---");
        char[] relation;
        int[] restored;
        relation = new char[] {
                ' ', '+', '+', '-', '+'
        };
        restored = obj.restoreArray(relation);
        System.out.println("Relations : " + Arrays.toString(relation) + "; Restored : " + Arrays.toString(restored));

        relation = new char[] {
                ' ', '-', '+', '+', '+', '-', '-', '+'
        };
        restored = obj.restoreArray(relation);
        System.out.println("Relations : " + Arrays.toString(relation) + "; Restored : " + Arrays.toString(restored));

        System.out.println("--- create huffman coding tree ---");
        String sentence;
        Map<Character, String> huffmanCode;

        sentence = "catscca";
        huffmanCode = obj.huffmanCoding(sentence);
        System.out.println("Encoding sentence : " + sentence);
        for(Character ch : huffmanCode.keySet()) {
            System.out.println("" + ch +" - " + huffmanCode.get(ch));
        }

        sentence = "aaaabbbcaa";
        System.out.println("Encoding sentence : " + sentence);
        huffmanCode = obj.huffmanCoding(sentence);
        for(Character ch : huffmanCode.keySet()) {
            System.out.println("" + ch +" - " + huffmanCode.get(ch));
        }

        System.out.println("--- Valid sentence checker ---");
        System.out.println(obj.validSentence("This is valid."));
        System.out.println(obj.validSentence("This is not valid"));
        System.out.println(obj.validSentence("This is Not valid."));
        System.out.println(obj.validSentence("THis is not valid."));
        System.out.println(obj.validSentence("Valid."));
    }
}
