package cracking.trees_graphs;

import java.util.*;

/**
 * Created by Martin G on 1/10/2017.
 */
public class ListOfDepths {

    List<List<BinarySearchTree.Node>> getDepthsLists(BinarySearchTree<Integer, Integer> bst) {
        List<List<BinarySearchTree.Node>> depthList = new ArrayList();
        Queue<BinarySearchTree.Node> queue = new LinkedList();
        queue.add(bst.root);
        while(!queue.isEmpty()) {
            List<BinarySearchTree.Node> oneLevelNodes = new ArrayList<>();
            Queue<BinarySearchTree.Node> auxQueue = new LinkedList<>();
            while(!queue.isEmpty()) {
                BinarySearchTree.Node oneLevelNode = queue.poll();
                oneLevelNodes.add(oneLevelNode);
                if (oneLevelNode.left != null) {
                    auxQueue.add(oneLevelNode.left);
                }
                if (oneLevelNode.right != null) {
                    auxQueue.add(oneLevelNode.right);
                }
            }
            depthList.add(oneLevelNodes);
            queue = auxQueue;
        }
        return depthList;
    }

    public void printBSTLevels(List<List<BinarySearchTree.Node>> depthsList) {
        depthsList.forEach(oneDepthList -> {
            System.out.print("\nNext level elements : ");
            oneDepthList.forEach(node -> System.out.print(node.value + ", "));
        });
    }

    public static void main(String[] args) {
        int[] array = new int[] {4, 5, 2, 67, 23, 12, 25, 6, 7, 80, 3, 9, 50, 11, 22, 1, 15};
        Arrays.sort(array);
        MinimalTreeFromSortedArray minimalTreeBuilder = new MinimalTreeFromSortedArray();
        BinarySearchTree<Integer, Integer> bst = minimalTreeBuilder.minimalTreeFromArray(array);
        System.out.println("Original tree " + bst);
        ListOfDepths obj = new ListOfDepths();
        List<List<BinarySearchTree.Node>> depths = obj.getDepthsLists(bst);
        obj.printBSTLevels(depths);
    }
}
