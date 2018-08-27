package cracking.trees_graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Martin G on 1/12/2017.
 */
public class BSTSequences {

    List<LinkedList<Integer>> getArraysForTree(BinarySearchTree<Integer, Integer> bst) {
        return buildForNode(bst.root);
    }

    List<LinkedList<Integer>> buildForNode(BinarySearchTree<Integer, Integer>.Node node) {
        List<LinkedList<Integer>> result = new ArrayList<>();
        if (node == null) {
            result.add(new LinkedList<>());
            return result;
        }

        LinkedList<Integer> prefix = new LinkedList<>();
        prefix.add(node.value);

        List<LinkedList<Integer>> leftSeq = buildForNode(node.left);
        List<LinkedList<Integer>> rightSeq = buildForNode(node.right);

        for(LinkedList<Integer> leftList : leftSeq) {
            for (LinkedList<Integer> rightList : rightSeq) {
                List<LinkedList<Integer>> exchanged = new  ArrayList<>();
                exchangeList(leftList, rightList, exchanged, prefix);
                result.addAll(exchanged);
            }
        }

        return result;
    }

    void exchangeList(LinkedList<Integer> leftList, LinkedList<Integer> rightList, List<LinkedList<Integer>> exchanged, LinkedList<Integer> prefix) {
        if (leftList.size() == 0 || rightList.size() == 0) {
            LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
            result.addAll(leftList);
            result.addAll(rightList);
            exchanged.add(result);
            return;
        }
        //save head of the left. It will damage the list, so we'll restore is after
        int headLeft = leftList.removeFirst();
        prefix.addLast(headLeft);
        exchangeList(leftList, rightList, exchanged, prefix);
        prefix.removeLast();
        leftList.addFirst(headLeft);

        //same for the right side
        int headRight = rightList.removeFirst();
        prefix.addLast(headRight);
        exchangeList(leftList, rightList, exchanged, prefix);
        prefix.removeLast();
        rightList.addFirst(headRight);
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, Integer> bst = new BinarySearchTree(new Integer[] {7, 2, 8});
        BSTSequences bstSeq = new BSTSequences();
        List<LinkedList<Integer>> sequences = bstSeq.getArraysForTree(bst);
        System.out.print("\n" + bst);
        sequences.forEach(oneSeq -> {
            System.out.print("\nNext sequence : ");
            oneSeq.forEach(element -> System.out.print(element + ", "));
        });

        bst = new BinarySearchTree(new Integer[] {7, 2, 8, 3});
        sequences = bstSeq.getArraysForTree(bst);
        System.out.print("\n" + bst);
        sequences.forEach(oneSeq -> {
            System.out.print("\nNext sequence : ");
            oneSeq.forEach(element -> System.out.print(element + ", "));
        });

        bst = new BinarySearchTree(new Integer[] {7, 2, 8, 1, 3});
        sequences = bstSeq.getArraysForTree(bst);
        System.out.print("\n" + bst);
        sequences.forEach(oneSeq -> {
            System.out.print("\nNext sequence : ");
            oneSeq.forEach(element -> System.out.print(element + ", "));
        });
    }
}