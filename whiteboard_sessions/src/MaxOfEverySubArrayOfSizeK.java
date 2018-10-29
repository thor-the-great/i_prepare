import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class MaxOfEverySubArrayOfSizeK {

    List<Integer> getMaxes(int[] nums, int k) {
        //return getMaxesSelfBalancedBST(nums, k);
        return getMaxesDequeue(nums, k);
    }

    List<Integer> getMaxesSelfBalancedBST(int[] nums, int k) {
        TreeSet<Integer> searchTree = new TreeSet();
        IntStream.range(0, k - 1).forEach(i->searchTree.add(nums[i]));
        List<Integer> res = new LinkedList();
        for (int i = k - 1; i < nums.length; i++) {
            searchTree.add(nums[i]);
            res.add(searchTree.last());
            searchTree.remove(nums[i - (k - 1)]);
        }
        return res;
    }

    List<Integer> getMaxesDequeue(int[] nums, int k) {
        List<Integer> res = new LinkedList();
        Deque<Integer> q = new LinkedList<>();
        int i = 0;
        //init queue for first k elements
        for (; i < k; i++) {
            while (!q.isEmpty() && nums[i] > nums[q.peekLast()])
                q.removeLast();
            q.addLast(i);
        }
        res.add(nums[q.peek()]);

        for (i = k; i < nums.length; i++) {
            //evict unnecessary elements (that will be not valid for next window based on index)
            if (!q.isEmpty() && q.peek() <= (i - k ))
                q.removeFirst();
            //now remove elements that are lower than current element - they can't be max
            while (!q.isEmpty() && nums[i] >= nums[q.peekLast()])
                q.removeLast();
            //add current element index
            q.addLast(i);
            //get max and add to result
            res.add(nums[q.peek()]);
        }
        return res;
    }

    public static void main(String[] args) {
        MaxOfEverySubArrayOfSizeK obj = new MaxOfEverySubArrayOfSizeK();
        List<Integer> res;
        res = obj.getMaxes(new int[] {1, 2, 3, 1, 4, 5, 2, 3, 6}, 3);
        System.out.print("Result : ");
        res.forEach(n->System.out.print(n + " "));
        System.out.print("\n");

        res = obj.getMaxes(new int[] {5, 1, 3, 5, 4}, 4);
        System.out.print("Result : ");
        res.forEach(n->System.out.print(n + " "));
        System.out.print("\n");

        res = obj.getMaxes(new int[] {12, 1, 78, 90, 57, 89, 56}, 3);
        System.out.print("Result : ");
        res.forEach(n->System.out.print(n + " "));
        System.out.print("\n");
    }
}
